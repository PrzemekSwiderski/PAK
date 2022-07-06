package fun.epak.pak.infrastructure;

import fun.epak.pak.model.Post;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageData {
    Long userId;
    String username;
    String userImageAddress;
    long postId;
    String content;
    LocalDate createDate;
    List<ViewCommentData> comments;

    public static PageData of(Post post, String path, List<ViewCommentData> comments) {
        return new PageData(post.getUser().getId(),
                post.getUser().getUsername(),
                path,
                post.getId(),
                post.getContent(),
                post.getCreateDate(),
                comments);
    }

}
