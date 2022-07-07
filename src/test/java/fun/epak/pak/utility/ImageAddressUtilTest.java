package fun.epak.pak.utility;

import fun.epak.pak.model.Post;
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
        String result = ImageAddressUtil.userImage("/data/images/profiles/", user);

        //then
        assertEquals("/data/images/profiles/99/testboy1337.jpeg", result);
    }

    @Test
    void postImage() {
        //given
        Post post = Post.builder()
                .id(23)
                .imageName("testpost.jpg")
                .build();
        //when
        String result = ImageAddressUtil.postImage("/data/images/post/", post);

        //then
        assertEquals("/data/images/post/23/testpost.jpg", result);
    }
}
