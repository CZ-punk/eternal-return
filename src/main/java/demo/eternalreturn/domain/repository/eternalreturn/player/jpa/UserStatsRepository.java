package demo.eternalreturn.domain.repository.eternalreturn.player.jpa;

import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserStatsRepository extends JpaRepository<UserStats, Integer> {

//    Optional<UserStats> findByNickname(String nickname);

    Optional<UserStats> findByNickname(String nickname);

    List<UserStats> findByNicknameIn(List<String> queryList);

    @Query("select u from UserStats as u where u.userNum in :idList")
    List<UserStats> findAll(List<Integer> idList);



}
