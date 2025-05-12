package demo.eternalreturn.application.service.eternalreturn;

import demo.eternalreturn.infrastructure.proxy.dto.response.ResUserNicknameDto;
import demo.eternalreturn.presentation.dto.request.eternal_return.Req20GamesDto;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqExperimentCodeDto;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqUserNicknameDto;
import demo.eternalreturn.presentation.dto.response.eternalreturn.experiment.ResExperimentStatDto;
import demo.eternalreturn.presentation.dto.response.eternalreturn.season.ResSeasonDto;
import demo.eternalreturn.presentation.dto.response.eternalreturn.user.ResTopRankDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

import java.util.List;


public interface EternalReturnService {

    ResUserNicknameDto searchUserInfoByUsername(ReqUserNicknameDto userNicknameDto);

    ResExperimentStatDto searchExperimentStatByExperimentCode(ReqExperimentCodeDto experimentCodeDto);

    Page<ResTopRankDto.UserRankDto> searchTopRankPage(Pageable pageable);

    List<ResSeasonDto> getSeasonList();

    Mono<?> getGames(Req20GamesDto req20GamesDto);

}
