package fun.epak.pak.utility;

import fun.epak.pak.model.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImageAddressUtilTest {

    @Test
    void userImage() {
        //given
        User user = User.builder()
                .id(99)
                .imageName("testboy1337.jpeg")
                .build();
        //when
        String result = ImageAddressUtil.userimage("/data/images/profiles/", user);

        //then
        assertEquals("/data/images/profiles/99/testboy1337.jpeg", result);
    }
}
