package demo.eternalreturn.domain.repository.eternalreturn.player.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static demo.eternalreturn.presentation.dto.response.eternalreturn.user.ResTopRankDto.*;


public interface TopRankCustomRepository {

    Page<UserRankDto> searchTopRankPage(Pageable pageable);

}
