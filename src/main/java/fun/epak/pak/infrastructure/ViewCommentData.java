package fun.epak.pak.infrastructure;

import fun.epak.pak.model.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ViewCommentData {
    Long userId;
    String username;
    String userImageAddress;
    long commentId;
    String content;
    LocalDate createDate;

    public static ViewCommentData of(Comment comment, String userImagePath) {
        return new ViewCommentData(comment.getUser().getId(),
                comment.getUser().getUsername(),
                userImagePath,
                comment.getId(),
                comment.getContent(),
                comment.getCreateDate());
    }
}
