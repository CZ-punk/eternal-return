package demo.eternalreturn.application.service.board;

import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.board.ReqBoardSearchCond;
import demo.eternalreturn.presentation.dto.request.board.ReqPostBoardDto;
import demo.eternalreturn.presentation.dto.response.board.ResBoardSearchDto;
import demo.eternalreturn.presentation.dto.response.board.ResGetBoardDto;
import demo.eternalreturn.presentation.dto.response.board.ResPostBoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

    ResPostBoardDto postBoard(ReqPostBoardDto reqPostBoardDto, CustomUserDetails userDetails);

    ResGetBoardDto getBoardById(Long boardId);

    Page<ResBoardSearchDto> getBoardPageListForUser(ReqBoardSearchCond reqBoardSearchCond, Pageable pageable);

    Page<ResBoardSearchDto> getBoardPageListForAdmin(ReqBoardSearchCond reqBoardSearchCond, Pageable pageable);
}
