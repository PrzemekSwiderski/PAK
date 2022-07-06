package fun.epak.pak.utility;

import fun.epak.pak.model.user.User;

public class ImageAddressUtil {

    public static String userimage(String prefixOfAddress, User user) {
        return prefixOfAddress + user.getId() + "/" + user.getImageName();
    }
}
