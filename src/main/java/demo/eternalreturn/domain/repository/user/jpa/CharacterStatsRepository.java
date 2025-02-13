package demo.eternalreturn.domain.repository.user.jpa;

import demo.eternalreturn.domain.model.CharacterStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterStatsRepository extends JpaRepository<CharacterStats, Integer> {
}
