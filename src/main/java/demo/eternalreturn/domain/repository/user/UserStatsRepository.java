package demo.eternalreturn.domain.repository.user;

import demo.eternalreturn.domain.model.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatsRepository extends JpaRepository<UserStats, Integer> {

    Optional<UserStats> findByNickname(String nickname);

}
