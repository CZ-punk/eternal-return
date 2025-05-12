package demo.eternalreturn.presentation.controller;

import demo.eternalreturn.application.service.board.BoardService;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.board.ReqBoardSearchCond;
import demo.eternalreturn.presentation.dto.request.board.ReqPostBoardDto;
import demo.eternalreturn.presentation.dto.response.PageResponseDto;
import demo.eternalreturn.presentation.dto.response.ResponseDto;
import demo.eternalreturn.presentation.dto.response.board.ResBoardSearchDto;
import demo.eternalreturn.presentation.dto.response.board.ResGetBoardDto;
import demo.eternalreturn.presentation.dto.response.board.ResPostBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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
    public ResponseEntity<?> postBoard(
            @ModelAttribute ReqPostBoardDto reqPostBoardDto,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        ResPostBoardDto resPostBoardDto = boardService.postBoard(reqPostBoardDto, userDetails);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resPostBoardDto));
    }

    @GetMapping("/{boardId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getBoardById(@PathVariable Long boardId) {
        ResGetBoardDto resGetBoardDto = boardService.getBoardById(boardId);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resGetBoardDto));
    }

    @GetMapping("/search-user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getBoardPageListForUser(
            @ModelAttribute ReqBoardSearchCond reqBoardSearchCond,
            @PageableDefault Pageable pageable
    ) {
        Page<ResBoardSearchDto> resBoardSearchDto = boardService.getBoardPageListForUser(reqBoardSearchCond, pageable);
        PageResponseDto<ResBoardSearchDto> page = new PageResponseDto<>(resBoardSearchDto);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, page));
    }

    @GetMapping("/search-admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getBoardPageListForAdmin(
            @ModelAttribute ReqBoardSearchCond reqBoardSearchCond,
            @PageableDefault Pageable pageable
    ) {
        Page<ResBoardSearchDto> resBoardSearchDto = boardService.getBoardPageListForAdmin(reqBoardSearchCond, pageable);
        PageResponseDto<ResBoardSearchDto> page = new PageResponseDto<>(resBoardSearchDto);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, page));
    }
}
