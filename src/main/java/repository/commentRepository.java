package repository;

import model.Comment;
import model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@Service
@Repository
public class commentRepository {
    public interface CommentRepository extends JpaRepository<Comment, Long> {
        List<Comment> findAllByCreatedDate(LocalDate createdDate, Pageable pageable);
    }

}
