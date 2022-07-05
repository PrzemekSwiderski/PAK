package fun.epak.pak.infrastructure;

import fun.epak.pak.model.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OtherUserProfileData {
    private long id;
    private String username;
    private String imageNameAddress;

    public static OtherUserProfileData of(User user, String path) {
        return new OtherUserProfileData(user.getId(), user.getUsername(), path);
    }
}
