package demo.eternalreturn.application.service.board;

import demo.eternalreturn.application.service.s3.S3Service;
import demo.eternalreturn.domain.model.Board.Board;
import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.repository.board.BoardCustomRepository;
import demo.eternalreturn.domain.repository.board.BoardRepository;
import demo.eternalreturn.domain.repository.member.MemberRepository;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.board.ReqBoardSearchCond;
import demo.eternalreturn.presentation.dto.request.board.ReqPostBoardDto;
import demo.eternalreturn.presentation.dto.response.board.ResBoardSearchDto;
import demo.eternalreturn.presentation.dto.response.board.ResGetBoardDto;
import demo.eternalreturn.presentation.dto.response.board.ResPostBoardDto;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardCustomRepository boardCustomRepository;
    private final MemberRepository memberRepository;
    private final S3Service s3Service;


    @Override
    @Transactional
    public ResPostBoardDto postBoard(ReqPostBoardDto reqPostBoardDto, CustomUserDetails userDetails) {
        Member member = memberRepository.findById(userDetails.getMemberId()).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found member"));

        List<String> imageUrlList = new ArrayList<>();
        List<MultipartFile> imageFiles = reqPostBoardDto.getImageFiles().stream()
                .filter(file -> !file.isEmpty())
                .toList();
        if (!imageFiles.isEmpty())
            imageUrlList = imageFiles.stream().map(file ->
                    s3Service.uploadFile(file, member.getId())).toList();

        Board board = Board.createBoard(reqPostBoardDto, userDetails.getName(), imageUrlList);
        member.connectionBoard(board);
        boardRepository.save(board);

        return ResPostBoardDto.builder()
                .id(board.getId())
                .title(reqPostBoardDto.getTitle())
                .contents(reqPostBoardDto.getContents())
                .imageUrlList(imageUrlList)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ResGetBoardDto getBoardById(Long boardId) {
        Board board = boardRepository.findByIdAndIsDeleteFalse(boardId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found and deleted board"));
        board.addView();

        return ResGetBoardDto.builder()
                .title(board.getTitle())
                .contents(board.getContents())
                .imageUrlList(board.getImageUrlList())
                .likeCount(board.getLikeCount())
                .commentCount(board.getCommentCount())
                .viewCount(board.getViewCount())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResBoardSearchDto> getBoardPageListForUser(ReqBoardSearchCond reqBoardSearchCond, Pageable pageable) {
        return boardCustomRepository.getBoardPageListForUser(reqBoardSearchCond, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResBoardSearchDto> getBoardPageListForAdmin(ReqBoardSearchCond reqBoardSearchCond, Pageable pageable) {
        return boardCustomRepository.getBoardPageListForAdmin(reqBoardSearchCond, pageable);
    }
}
