package fun.epak.pak.repository;

import fun.epak.pak.model.Post;
import fun.epak.pak.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser_IdIn(Set<Long> ids);
}
