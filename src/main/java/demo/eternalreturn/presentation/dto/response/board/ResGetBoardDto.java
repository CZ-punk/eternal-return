package demo.eternalreturn.presentation.dto.response.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResGetBoardDto {

    private String title;
    private String contents;
    private List<String> imageUrlList;

    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
}
