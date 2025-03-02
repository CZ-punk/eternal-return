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
public class ResPostBoardDto {

    private Long id;
    private String title;
    private String content;
    private List<String> imageUrlList;
}
