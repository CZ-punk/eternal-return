package demo.eternalreturn.domain.repository.player.jpa;

import demo.eternalreturn.domain.model.eternal_return.user.CharacterStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterStatsRepository extends JpaRepository<CharacterStats, Integer> {
}
