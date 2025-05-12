package demo.eternalreturn.domain.repository.board;

import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.board.ReqBoardSearchCond;
import demo.eternalreturn.presentation.dto.response.board.ResBoardSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCustomRepository {

    Page<ResBoardSearchDto> getBoardPageListForUser(ReqBoardSearchCond reqBoardSearchCond, Pageable pageable);
    Page<ResBoardSearchDto> getBoardPageListForAdmin(ReqBoardSearchCond reqBoardSearchCond, Pageable pageable);
}
