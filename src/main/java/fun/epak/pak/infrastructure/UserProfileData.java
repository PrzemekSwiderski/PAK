package fun.epak.pak.infrastructure;

import fun.epak.pak.model.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileData {
    long id;
    String email;
    String username;
    String imageNameAddress;
    LocalDate registerDate;

    public static UserProfileData of(User user, String path) {
        return new UserProfileData(user.getId(), user.getEmail(), user.getUsername(), path, user.getRegisterDate());
    }
}
