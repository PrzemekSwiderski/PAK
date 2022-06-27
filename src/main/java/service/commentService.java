package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class commentService {
    private repository.commentRepository.CommentRepository commentRepository;

    public void CommentService(repository.commentRepository.CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
