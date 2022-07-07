package fun.epak.pak.utility;

import fun.epak.pak.model.Post;
import fun.epak.pak.model.user.User;

public class ImageAddressUtil {

    public static String userImage(String prefixOfAddress, User user) {
        return prefixOfAddress + user.getId() + "/" + user.getImageName();
    }

    public static String postImage(String prefixOfAddress, Post post) {
        return prefixOfAddress + post.getId() + "/" + post.getImageName();
    }
}
