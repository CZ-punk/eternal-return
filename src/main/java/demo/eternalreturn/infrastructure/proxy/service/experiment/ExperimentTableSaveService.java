package demo.eternalreturn.infrastructure.proxy.service.experiment;

import demo.eternalreturn.domain.model.user.UserStats;
import demo.eternalreturn.presentation.controller.dto.response.ResponseDto;
import demo.eternalreturn.presentation.controller.dto.request.ReqUserNicknameDto;
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
