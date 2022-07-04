package fun.epak.pak.infrastructure;

import fun.epak.pak.model.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileData {
    private long id;
    private String email;
    private String username;
    private String imageNameAddress;
    private LocalDate registerDate;

    public static UserProfileData of(User user, String path) {
        return new UserProfileData(user.getId(), user.getEmail(), user.getUsername(), path, user.getRegisterDate());
    }
}
