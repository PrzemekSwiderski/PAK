package repository;

import model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@Repository
@Service
public class postRepository {
    public interface PostRepository extends JpaRepository<Post, Long> {
        List<Post> findAllByCreatedDate(LocalDate createdDate, Pageable pageable);
    }
}
