package demo.eternalreturn.presentation.controller;

import demo.eternalreturn.application.service.eternalreturn.EternalReturnService;
import demo.eternalreturn.presentation.dto.request.eternal_return.Req20GamesDto;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqExperimentCodeDto;
import demo.eternalreturn.presentation.dto.request.eternal_return.ReqUserNicknameDto;
import demo.eternalreturn.presentation.dto.response.PageResponseDto;
import demo.eternalreturn.presentation.dto.response.ResponseDto;
import demo.eternalreturn.presentation.dto.response.eternalreturn.season.ResSeasonDto;
import demo.eternalreturn.presentation.dto.response.eternalreturn.user.ResTopRankDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

import static demo.eternalreturn.presentation.dto.response.eternalreturn.user.ResTopRankDto.UserRankDto;
import static demo.eternalreturn.presentation.exception.ResultMessage.Success;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/eternal-return")
public class EternalReturnController {

    @Autowired
    private final EternalReturnService eternalReturnService;

    @GetMapping("/username")
    public ResponseEntity<?> searchUserInfoByUsername(@ModelAttribute ReqUserNicknameDto userNicknameDto) {
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, eternalReturnService.searchUserInfoByUsername(userNicknameDto)));
    }

    @GetMapping("/experiment")
    public ResponseEntity<?> searchExperimentByExperimentCode(@ModelAttribute ReqExperimentCodeDto experimentCode) {
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, eternalReturnService.searchExperimentStatByExperimentCode(experimentCode)));
    }

    /**
     * userNum 을 받아 최근 20개의 랭크 + @ 개의 일반게임 조회하는 API
     */
    @GetMapping("/games_20")
    public Mono<?> getGames(@ModelAttribute Req20GamesDto req20GamesDto) {
//        return ResponseEntity.ok(new ResponseDto<>(OK, Success, eternalReturnService.get20Games(req20GamesDto)));
        return eternalReturnService.getGames(req20GamesDto);
        //2268650
        //4322619

        //        return Flux.just("Data 1", "Data 2", "Data 3")
        //                .doOnNext(item -> System.out.println("Received: " + item))
        //                .doOnComplete(() -> System.out.println("Completed"))
        //                .doOnError(error -> System.err.println("Error: " + error.getMessage()));


//        Mono<String> stringMono = Mono.just("Single Data")
//                .doOnNext(data -> System.out.println("Received: " + data))
//                .doOnSuccess(data -> System.out.println("Completed"));

    }

    /**
     * 랭크 게임 20개의 간단한 정보
     * { }
     */


    /**
     * 랭커 정보 100개씩 조회 ( seasonId 마다 )
     */
    @GetMapping("/top/rank/{seasonId}")
    public ResponseEntity<?> searchTopRankPage(
            @PathVariable Integer seasonId,
            @PageableDefault Pageable pageable
    ) {
        // TODO: seasonId 에 따른 다른 Page 조회 추가
        PageResponseDto<UserRankDto> page = new PageResponseDto<>(eternalReturnService.searchTopRankPage(pageable));
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, new ResTopRankDto(seasonId, page)));
    }

    @GetMapping("/season")
    public ResponseEntity<?> getSeasonList() {
        List<ResSeasonDto> resSeasonDto = eternalReturnService.getSeasonList();
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resSeasonDto));
    }


}
