package demo.eternalreturn.presentation.controller;

import demo.eternalreturn.application.service.post.BoardService;
import demo.eternalreturn.presentation.dto.request.board.ReqBoardSearchCond;
import demo.eternalreturn.presentation.dto.request.board.ReqGetBoardDto;
import demo.eternalreturn.presentation.dto.request.board.ReqPostBoardDto;
import demo.eternalreturn.presentation.dto.response.ResponseDto;
import demo.eternalreturn.presentation.dto.response.board.ResGetBoardDto;
import demo.eternalreturn.presentation.dto.response.board.ResPostBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static demo.eternalreturn.presentation.exception.ResultMessage.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;


    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> postBoard(@ModelAttribute ReqPostBoardDto reqPostBoardDto) {
        ResPostBoardDto resPostBoardDto = boardService.postBoard(reqPostBoardDto);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resPostBoardDto));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getBoardById(@ModelAttribute ReqGetBoardDto reqGetBoardDto) {
        ResGetBoardDto resGetBoardDto = boardService.getBoardById(reqGetBoardDto);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resGetBoardDto));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getBoardPageList(
            @ModelAttribute ReqBoardSearchCond reqBoardSearchCond,
            @PageableDefault Pageable pageable
    ) {
        return null;
    }


}
