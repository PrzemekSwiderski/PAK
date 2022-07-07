package fun.epak.pak.service;

import fun.epak.pak.exceptions.NoUserException;
import fun.epak.pak.exceptions.SaveFileException;
import fun.epak.pak.exceptions.SubscribeYourselfException;
import fun.epak.pak.infrastructure.ChangeUserDataRequest;
import fun.epak.pak.infrastructure.OtherUserProfileData;
import fun.epak.pak.infrastructure.SubscribersData;
import fun.epak.pak.infrastructure.UserProfileData;
import fun.epak.pak.infrastructure.UserRegistrationRequest;
import fun.epak.pak.infrastructure.UserWritingPostData;
import fun.epak.pak.model.user.User;
import fun.epak.pak.model.user.UserDetails;
import fun.epak.pak.model.user.UserRole;
import fun.epak.pak.repository.UserRepository;
import fun.epak.pak.repository.UserRoleRepository;
import fun.epak.pak.utility.FileUploadUtil;
import fun.epak.pak.utility.ImageAddressUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @Value("${image.address}")
    private String imageBaseAddress;

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
                .subscriptions(new HashSet<>())
                .build();
    }

    private String getFileExtension(MultipartFile multipartFile) {
        return Objects.requireNonNull(multipartFile.getContentType())
                .replaceFirst("image/", "");
    }

    public UserProfileData loadUserProfileData(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoUserException("There is no user with such an email:" + email));
        String userImagePath = ImageAddressUtil.userImage(imageBaseAddress, user);
        return UserProfileData.of(user, userImagePath);
    }

    public void saveChangedUser(ChangeUserDataRequest userData, MultipartFile multipartFile, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoUserException("There is no user with such an email:" + email));
        if (userData.getUsername() != null && !"".equals(userData.getUsername())) {
            user.setUsername(userData.getUsername());
        }
        if (userData.getPassword() != null && !"".equals(userData.getPassword())) {
            user.setPassword(passwordEncoder.encode(userData.getPassword()));
        }
        if (!multipartFile.isEmpty()) {
            user.setImageName(user.getUsername().toLowerCase() + "." + getFileExtension(multipartFile));
            saveFile(multipartFile, user);
        }
        userRepository.save(user);
    }

    public OtherUserProfileData loadOtherUserProfileData(String email, long id) {
        User user = userRepository.findById(id).orElseThrow();
        User viewer = userRepository.findByEmail(email).orElseThrow();
        boolean contains = viewer.getSubscriptions().contains(user);
        String userImagePath = ImageAddressUtil.userImage(imageBaseAddress, user);
        return OtherUserProfileData.of(user, userImagePath, contains);
    }

    public void subscribeUser(String subscribingEmail, long subscribedId) {
        User user = userRepository.findByEmail(subscribingEmail).orElseThrow();
        if (user.getId() == subscribedId) {
            throw new SubscribeYourselfException("You cant subscribe to yourself");
        }
        User subscribedUser = userRepository.findById(subscribedId).orElseThrow();
        user.getSubscriptions().add(subscribedUser);
        userRepository.save(user);
    }

    public void unsubscribeUser(String unsubscribingEmail, long unsubscribedId) {
        User user = userRepository.findByEmail(unsubscribingEmail).orElseThrow();
        if (user.getId() == unsubscribedId) {
            throw new SubscribeYourselfException("You cant subscribe to yourself");
        }
        User subscribedUser = userRepository.findById(unsubscribedId).orElseThrow();

        user.getSubscriptions().remove(subscribedUser);
        userRepository.save(user);
    }

    public List<SubscribersData> loadAllSubscriptions(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoUserException("There is no user with such an email:" + email));
        Set<User> subscriptions = user.getSubscriptions();
        return subscriptions.stream()
                .map(this::mapToSubscribersData)
                .sorted(Comparator.comparing(SubscribersData::getId))
                .collect(Collectors.toList());
    }

    private SubscribersData mapToSubscribersData(User user) {
        String userImagePath = ImageAddressUtil.userImage(imageBaseAddress, user);
        return SubscribersData.of(user, userImagePath);
    }

    public UserWritingPostData loadUserWritingPostData(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoUserException("There is no user with such an email:" + email));
        String userImagePath = ImageAddressUtil.userImage(imageBaseAddress, user);
        return UserWritingPostData.of(user, userImagePath);
    }


}
