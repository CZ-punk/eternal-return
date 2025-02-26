package demo.eternalreturn.domain.repository.player.jpa;

import demo.eternalreturn.domain.model.eternal_return.user.TopRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopRankRepository extends JpaRepository<TopRank, Integer> {
}
