package fun.epak.pak.infrastructure;

import fun.epak.pak.model.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ViewCommentData {
    private Long userId;
    private String username;
    private String userImageAddress;
    private long commentId;
    private String content;
    private LocalDate createDate;

    public static ViewCommentData of(Comment comment, String userImagePath) {
        return new ViewCommentData(comment.getUser().getId(),
                comment.getUser().getUsername(),
                userImagePath,
                comment.getId(),
                comment.getContent(),
                comment.getCreateDate());
    }
}
