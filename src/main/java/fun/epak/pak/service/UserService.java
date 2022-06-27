package fun.epak.pak.service;

import fun.epak.pak.infrastructure.UserRegistrationRequest;
import fun.epak.pak.model.user.User;
import fun.epak.pak.model.user.UserDetails;
import fun.epak.pak.model.user.UserRole;
import fun.epak.pak.repository.UserRepository;
import fun.epak.pak.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String ERROR_MESSAGE = "User not found";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(ERROR_MESSAGE));
        return new UserDetails(user);
    }

    public void registerUser(UserRegistrationRequest userRegistrationRequest) {
        User user = User.builder()
                .email(userRegistrationRequest.getEmail())
                .password(passwordEncoder.encode(userRegistrationRequest.getPassword()))
                .username(userRegistrationRequest.getUsername())
                .isActive(true)
                .registerDate(LocalDate.now())
                .build();
        userRepository.save(user);
        userRoleRepository.save(new UserRole(user));
    }
}
