package demo.eternalreturn.application;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.domain.model.UserStats;
import demo.eternalreturn.domain.repository.UserStatsRepository;
import demo.eternalreturn.domain.repository.experiment.custom.ExperimentCustomRepository;
import demo.eternalreturn.infrastructure.proxy.dto.request.ReqUserNicknameDto;
import demo.eternalreturn.infrastructure.proxy.dto.response.ResponseDto;
import demo.eternalreturn.infrastructure.proxy.service.util.JsonNodeService;
import demo.eternalreturn.infrastructure.proxy.service.experiment.ExperimentTableSaveService;
import demo.eternalreturn.presentation.controller.dto.request.ReqExperimentCodeDto;
import demo.eternalreturn.presentation.exception.ResultMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static demo.eternalreturn.infrastructure.proxy.constant.UrlConst.USER_NICKNAME;
import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomServiceImpl implements CustomService {

    @Autowired
    private final JsonNodeService jsonNodeService;
    @Autowired
    private final ExperimentTableSaveService tableSaveService;
    @Autowired
    private final UserStatsRepository userStatsRepository;
    @Autowired
    private final ExperimentCustomRepository experimentCustomRepository;


    /**
     * Username 을 기반으로 UserStats 정보를 새로 생성 및 업데이트하는 함수
     */
    @Override
    @Transactional
    public ResponseEntity<?> searchUserInfoByUsername(ReqUserNicknameDto userNicknameDto) {
        UserStats userStats = userStatsRepository.findByNickname(userNicknameDto.getQuery()).orElse(null);
        if (userStats == null) {
            JsonNode userNode = jsonNodeService.getJsonNodeByQueryParams(USER_NICKNAME, userNicknameDto, GET, "user");
            tableSaveService.saveUserStats(userNode.get("userNum").asInt(), null);
            return ResponseEntity.ok("success");
        }
        tableSaveService.saveUserStats(userStats.getUserNum(), userStats);
        return ResponseEntity.ok("success");
    }

    /**
     * ExperimentCode 를 기반으로 Experiment Stats 및 Level Up Stat 을 조회
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> searchExperimentStatByExperimentCode(ReqExperimentCodeDto experimentCodeDto) {
        return ResponseEntity.ok(
                new ResponseDto<>(
                        HttpStatus.OK,
                        ResultMessage.Success,
                        experimentCustomRepository.searchExperimentStatByExperimentCode(experimentCodeDto.getCode())
                )
        );
    }
}
