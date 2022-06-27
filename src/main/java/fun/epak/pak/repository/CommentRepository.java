package fun.epak.pak.repository;

import fun.epak.pak.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
