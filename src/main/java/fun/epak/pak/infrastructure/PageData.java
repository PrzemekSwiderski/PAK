package fun.epak.pak.infrastructure;

import fun.epak.pak.model.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageData {
    private Long userId;
    private String username;
    private String userImageAddress;
    private long postId;
    private String content;
    private LocalDate createDate;

    public static PageData of(Post post, String path) {
        return new PageData(post.getUser().getId(),
                post.getUser().getUsername(),
                path,
                post.getId(),
                post.getContent(),
                post.getCreateDate());
    }

}
