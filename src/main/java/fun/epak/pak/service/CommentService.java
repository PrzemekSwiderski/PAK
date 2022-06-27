package fun.epak.pak.service;

import fun.epak.pak.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;

    public void CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}




