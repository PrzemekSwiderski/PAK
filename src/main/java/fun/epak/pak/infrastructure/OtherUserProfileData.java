package fun.epak.pak.infrastructure;

import fun.epak.pak.model.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OtherUserProfileData {
    long id;
    String username;
    String imageNameAddress;
    Boolean isSubscribed;

    public static OtherUserProfileData of(User user, String path, Boolean isSubscribed) {
        return new OtherUserProfileData(user.getId(), user.getUsername(), path, isSubscribed);
    }
}
