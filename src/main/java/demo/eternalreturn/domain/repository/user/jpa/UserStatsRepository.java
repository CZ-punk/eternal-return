package demo.eternalreturn.domain.repository.user.jpa;

import demo.eternalreturn.domain.model.user.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatsRepository extends JpaRepository<UserStats, Integer> {

    Optional<UserStats> findByNickname(String nickname);

}
