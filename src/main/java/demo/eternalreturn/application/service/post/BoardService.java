package demo.eternalreturn.application.service.post;

import demo.eternalreturn.presentation.dto.request.board.ReqGetBoardDto;
import demo.eternalreturn.presentation.dto.request.board.ReqPostBoardDto;
import demo.eternalreturn.presentation.dto.response.board.ResGetBoardDto;
import demo.eternalreturn.presentation.dto.response.board.ResPostBoardDto;

public interface BoardService {

    ResPostBoardDto postBoard(ReqPostBoardDto reqPostBoardDto);

    ResGetBoardDto getBoardById(ReqGetBoardDto reqGetBoardDto);

}
