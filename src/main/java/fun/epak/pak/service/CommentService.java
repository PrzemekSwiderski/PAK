package fun.epak.pak.service;

import fun.epak.pak.model.Comment;
import fun.epak.pak.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment loadCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }

}
    /* loadAllCommentsByPostId
    * */






