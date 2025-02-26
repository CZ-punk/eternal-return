package demo.eternalreturn.application.service.proxy;

import demo.eternalreturn.domain.model.eternal_return.user.UserStats;
import demo.eternalreturn.domain.repository.experiment.custom.ExperimentCustomRepository;
import demo.eternalreturn.domain.repository.item.custom.ItemCustomRepository;
import demo.eternalreturn.domain.repository.player.jpa.UserStatsRepository;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqExperimentCodeDto;
import demo.eternalreturn.presentation.dto.response.ResponseDto;
import demo.eternalreturn.infrastructure.proxy.service.experiment.ExperimentTableSaveService;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqUserNicknameDto;
import demo.eternalreturn.presentation.exception.ResultMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProxyServiceImpl implements ProxyService {

    @Autowired
    private final ExperimentTableSaveService tableSaveService;
    @Autowired
    private final UserStatsRepository userStatsRepository;
    @Autowired
    private final ExperimentCustomRepository experimentCustomRepository;
    @Autowired
    private final ItemCustomRepository itemCustomRepository;


    /**
     * Username 을 기반으로 UserStats 정보를 새로 생성 및 업데이트하는 함수
     */
    @Override
    @Transactional
    public ResponseDto<?> searchUserInfoByUsername(ReqUserNicknameDto userNicknameDto) {
        UserStats userStats = userStatsRepository.findByNickname(userNicknameDto.getQuery()).orElse(null);

        if (userStats != null) return tableSaveService.updateUserStats(userStats);
        return tableSaveService.registerUserStats(userNicknameDto);
    }

    /**
     * ExperimentCode 를 기반으로 Experiment Stats 및 Level Up Stat 을 조회
     */
    @Override
    @Transactional(readOnly = true)
    public ResponseDto<?> searchExperimentStatByExperimentCode(ReqExperimentCodeDto experimentCodeDto) {
        return new ResponseDto<>(
                HttpStatus.OK,
                ResultMessage.Success,
                experimentCustomRepository.searchExperimentStatByExperimentCode(experimentCodeDto.getCode()));
    }
}
