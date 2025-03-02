package demo.eternalreturn.application.service.post;

import demo.eternalreturn.application.service.s3.S3Service;
import demo.eternalreturn.domain.model.Board.Board;
import demo.eternalreturn.domain.repository.board.BoardRepository;
import demo.eternalreturn.presentation.dto.request.board.ReqGetBoardDto;
import demo.eternalreturn.presentation.dto.request.board.ReqPostBoardDto;
import demo.eternalreturn.presentation.dto.response.board.ResGetBoardDto;
import demo.eternalreturn.presentation.dto.response.board.ResPostBoardDto;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final S3Service s3Service;


    @Override
    @Transactional
    public ResPostBoardDto postBoard(ReqPostBoardDto reqPostBoardDto) {
        List<String> imageUrlList = new ArrayList<>();
        List<MultipartFile> imageFiles = reqPostBoardDto.getImageFiles().stream()
                .filter(file -> !file.isEmpty())
                .toList();

        if (!imageFiles.isEmpty())
            imageUrlList = imageFiles.stream().map(s3Service::uploadFile).toList();

        Board board = Board.builder()
                .title(reqPostBoardDto.getTitle())
                .contents(reqPostBoardDto.getContents())
                .imageUrlList(imageUrlList)
                .build();
        boardRepository.save(board);

        return ResPostBoardDto.builder()
                .id(board.getId())
                .title(reqPostBoardDto.getTitle())
                .content(reqPostBoardDto.getContents())
                .imageUrlList(imageUrlList)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ResGetBoardDto getBoardById(ReqGetBoardDto reqGetBoardDto) {

        Board board = boardRepository.findByIdAndIsDeleteFalse(reqGetBoardDto.getBoardId()).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found and deleted board"));

        return ResGetBoardDto.builder()
                .title(board.getTitle())
                .contents(board.getContents())
                .imageUrlList(board.getImageUrlList())
                .build();
    }
}
