package demo.eternalreturn.presentation.dto.response.board;

import demo.eternalreturn.domain.model.Board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResBoardSearchDto {

    private Long memberId;
    private Long boardId;
    private String title;
    private String contents;
    private String author;

    @Builder.Default
    private List<String> imageUrlList = new ArrayList<>();
    private LocalDateTime createdAt;

    public static ResBoardSearchDto toDto(Board board) {
        return ResBoardSearchDto.builder()
                .memberId(board.getMember().getId())
                .boardId(board.getId())
                .title(board.getTitle())
                .contents(board.getContents())
                .author(board.getAuthor())
                .imageUrlList(board.getImageUrlList())
                .createdAt(board.getCreatedAt())
                .build();
    }
}
