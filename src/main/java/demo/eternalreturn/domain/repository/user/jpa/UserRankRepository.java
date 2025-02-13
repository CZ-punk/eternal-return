package demo.eternalreturn.domain.repository.user.jpa;

import demo.eternalreturn.domain.model.user.UserRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRankRepository extends JpaRepository<UserRank, Integer> {
}
