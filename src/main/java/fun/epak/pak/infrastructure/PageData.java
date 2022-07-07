package fun.epak.pak.infrastructure;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageData {
    Long userId;
    String username;
    String userImageAddress;
    long postId;
    String content;
    String postImageAddress;
    LocalDate createDate;
    List<ViewCommentData> comments;
}
