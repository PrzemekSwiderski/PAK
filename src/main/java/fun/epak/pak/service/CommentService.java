package fun.epak.pak.service;

import fun.epak.pak.infrastructure.NewCommentRequest;
import fun.epak.pak.model.Comment;
import fun.epak.pak.model.Post;
import fun.epak.pak.model.user.User;
import fun.epak.pak.repository.CommentRepository;
import fun.epak.pak.repository.PostRepository;
import fun.epak.pak.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void addNewComment(NewCommentRequest newCommentRequest, String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        Post post = postRepository.findById(newCommentRequest.getPostId()).orElseThrow();
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(newCommentRequest.getContent());
        commentRepository.save(comment);
    }
}






