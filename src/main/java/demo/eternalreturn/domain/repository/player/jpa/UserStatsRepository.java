package demo.eternalreturn.domain.repository.player.jpa;

import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserStatsRepository extends JpaRepository<UserStats, Integer> {

    Optional<UserStats> findByNickname(String nickname);

}
