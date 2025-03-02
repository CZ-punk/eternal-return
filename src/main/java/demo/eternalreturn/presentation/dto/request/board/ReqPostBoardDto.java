package demo.eternalreturn.presentation.dto.request.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqPostBoardDto {

    private String title;
    private String contents;
    private List<MultipartFile> imageFiles;

}
