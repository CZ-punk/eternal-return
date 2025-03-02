package demo.eternalreturn.domain.repository.board;

import demo.eternalreturn.domain.model.Board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByIdAndIsDeleteFalse(Long id);
}
