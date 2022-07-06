package fun.epak.pak.infrastructure;

import fun.epak.pak.model.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageData {
    Long userId;
    String username;
    String userImageAddress;
    long postId;
    String content;
    LocalDate createDate;

    public static PageData of(Post post, String path) {
        return new PageData(post.getUser().getId(),
                post.getUser().getUsername(),
                path,
                post.getId(),
                post.getContent(),
                post.getCreateDate());
    }

}
