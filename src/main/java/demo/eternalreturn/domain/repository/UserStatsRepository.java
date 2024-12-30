package demo.eternalreturn.domain.repository;

import demo.eternalreturn.domain.model.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStatsRepository extends JpaRepository<UserStats, Integer> {

    boolean existsByUserNumIn(List<Integer> userNums);
    Optional<UserStats> findByNickname(String nickname);

}
