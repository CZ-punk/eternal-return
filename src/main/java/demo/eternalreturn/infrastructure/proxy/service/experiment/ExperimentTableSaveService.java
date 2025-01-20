package demo.eternalreturn.infrastructure.proxy.service.experiment;

import demo.eternalreturn.domain.model.UserStats;
import demo.eternalreturn.infrastructure.proxy.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface ExperimentTableSaveService {

    ResponseEntity<?> callWeaponTypeInfo();

    ResponseEntity<?> callExperiment();

    ResponseEntity<?> callExperimentAttribute();

    ResponseEntity<?> callExperimentExp();

    ResponseEntity<?> callExperimentMastery();

    ResponseEntity<?> callExperimentLevelUpStat();

    ResponseDto<?> saveUserStats(Integer userNum, UserStats userStats);

}
