package demo.eternalreturn.infrastructure.proxy.service.experiment;

import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import demo.eternalreturn.presentation.dto.response.ResponseDto;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqUserNicknameDto;
import reactor.core.publisher.Mono;

public interface ExperimentTableSaveService {

    Mono<?> callWeaponTypeInfo();

    Mono<?> callExperiment();

    Mono<?> callExperimentAttribute();

    Mono<?> callExperimentExp();

    Mono<?> callExperimentMastery();

    Mono<?> callExperimentLevelUpStat();

    ResponseDto<?> registerUserStats(ReqUserNicknameDto userNicknameDto);

    ResponseDto<?> updateUserStats(UserStats userStats);

}
