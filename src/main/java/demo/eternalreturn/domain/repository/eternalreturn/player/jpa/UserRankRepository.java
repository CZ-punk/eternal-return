package demo.eternalreturn.domain.repository.eternalreturn.player.jpa;

import demo.eternalreturn.domain.model.eternal_return.user.UserRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRankRepository extends JpaRepository<UserRank, Integer> {
}
