package demo.eternalreturn.domain.repository.comment;

import demo.eternalreturn.domain.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndIsDeleteFalse(Long commentId);

}
