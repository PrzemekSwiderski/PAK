package fun.epak.pak.infrastructure;

import lombok.Value;

@Value
public class NewCommentRequest {
    long postId;
    String content;
}
