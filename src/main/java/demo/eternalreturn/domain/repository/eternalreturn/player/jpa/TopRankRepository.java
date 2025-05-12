package demo.eternalreturn.domain.repository.eternalreturn.player.jpa;

import demo.eternalreturn.domain.model.eternal_return.user.TopRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TopRankRepository extends JpaRepository<TopRank, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("delete from TopRank")
    void deleteAll();
}
