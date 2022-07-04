package fun.epak.pak.service;

import fun.epak.pak.exceptions.SaveFileException;
import fun.epak.pak.infrastructure.ChangeUserDataRequest;
import fun.epak.pak.infrastructure.OtherUserProfileData;
import fun.epak.pak.infrastructure.UserProfileData;
import fun.epak.pak.infrastructure.UserRegistrationRequest;
import fun.epak.pak.model.user.User;
import fun.epak.pak.model.user.UserDetails;
import fun.epak.pak.model.user.UserRole;
import fun.epak.pak.repository.UserRepository;
import fun.epak.pak.repository.UserRoleRepository;
import fun.epak.pak.utility.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String ERROR_MESSAGE = "User not found";
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(ERROR_MESSAGE));
        return new UserDetails(user);
    }

    public void registerUser(UserRegistrationRequest userRegistrationRequest, MultipartFile multipartFile) {
        String fileExtension = getFileExtension(multipartFile);
        User user = createUser(userRegistrationRequest, fileExtension);
        user = userRepository.save(user);
        userRoleRepository.save(new UserRole(user));
        saveFile(multipartFile, user);
    }

    private void saveFile(MultipartFile multipartFile, User user) {
        String uploadDir = "data/images/profiles/" + user.getId();
        try {
            FileUploadUtil.saveFile(uploadDir, user.getImageName(), multipartFile);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            throw new SaveFileException("Error at file upload at: " + uploadDir + "with file: " + user.getImageName());
        }
    }

    private User createUser(UserRegistrationRequest userRegistrationRequest, String fileExtension) {
        return User.builder()
                .email(userRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .username(userRegistrationRequest.getUsername())
                .imageName(userRegistrationRequest.getUsername().toLowerCase() + "." + fileExtension)
                .isActive(true)
                .registerDate(LocalDate.now())
                .build();
    }

    private String getFileExtension(MultipartFile multipartFile) {
        return Objects.requireNonNull(multipartFile.getContentType())
                .replaceFirst("image/", "");
    }

    public UserProfileData loadUserProfileData(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        String userImagePath = "/data/images/profiles/" + user.getId() + "/" + user.getImageName();
        return UserProfileData.of(user, userImagePath);
    }

    public void saveChangedUser(ChangeUserDataRequest userData, MultipartFile multipartFile, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        if (userData.getUsername() != null && !"".equals(userData.getUsername())) {
            user.setUsername(userData.getUsername());
        }
        if (userData.getPassword() != null && !"".equals(userData.getPassword())) {
            user.setPassword(passwordEncoder.encode(userData.getPassword()));
        }
        if(!multipartFile.isEmpty()){
            user.setImageName(user.getUsername().toLowerCase() + "." +  getFileExtension(multipartFile));
            saveFile(multipartFile, user);
        }
        userRepository.save(user);
    }

    public OtherUserProfileData loadOtherUserProfileData(long id){
        User user = userRepository.findById(id).orElseThrow();
        String userImagePath = "/data/images/profiles/" + user.getId() + "/" + user.getImageName();
        return OtherUserProfileData.of(user, userImagePath);
    }
}
