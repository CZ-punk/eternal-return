package demo.eternalreturn.domain.repository.eternalreturn.player.jpa;

import demo.eternalreturn.domain.model.eternal_return.user.CharacterStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CharacterStatsRepository extends JpaRepository<CharacterStats, Integer> {

    @Query("select c from CharacterStats as c where c.userNum in :userNumList")
    List<CharacterStats> findAllByUserNum(List<Integer> userNumList);
}
