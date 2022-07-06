package fun.epak.pak.infrastructure;

import fun.epak.pak.model.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SubscribersData {
    long id;
    String username;
    String imageNameAddress;

    public static SubscribersData of(User user, String path) {
        return new SubscribersData(user.getId(), user.getUsername(), path);
    }
}
