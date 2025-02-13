package demo.eternalreturn.domain.repository.user.jpa;

import demo.eternalreturn.domain.model.user.TopRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopRankRepository extends JpaRepository<TopRank, Integer> {
}
