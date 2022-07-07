package fun.epak.pak.service;

import fun.epak.pak.exceptions.NoPostException;
import fun.epak.pak.exceptions.NoUserException;
import fun.epak.pak.infrastructure.NewCommentRequest;
import fun.epak.pak.model.Comment;
import fun.epak.pak.model.Post;
import fun.epak.pak.model.user.User;
import fun.epak.pak.repository.CommentRepository;
import fun.epak.pak.repository.PostRepository;
import fun.epak.pak.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void addNewComment(NewCommentRequest newCommentRequest, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NoUserException("There is no user with such an email:" + email));
        Post post = postRepository.findById(newCommentRequest.getPostId())
                .orElseThrow(() -> new NoPostException("There is no post"));
        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(newCommentRequest.getContent())
                .createDate(LocalDate.now())
                .build();
        commentRepository.save(comment);
    }
}






