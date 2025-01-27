package demo.eternalreturn.domain.repository.user;

import demo.eternalreturn.domain.model.CharacterStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterStatsRepository extends JpaRepository<CharacterStats, Integer> {
}
