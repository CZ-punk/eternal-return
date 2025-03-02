package demo.eternalreturn.presentation.dto.response.member;

import demo.eternalreturn.domain.model.Member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResGetMemberDto {

    private Long id;
    private String username;
    private List<BoardDto> boardDtoList;

    @Data
    @Builder
    public static class BoardDto {
        private Long boardId;
        private String title;
        private String titleImageUrl;
    }
    public static ResGetMemberDto toDto(Member member) {

        return ResGetMemberDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .boardDtoList(
                        member.getBoardList().stream().map(board ->
                                ResGetMemberDto.BoardDto.builder()
                                        .boardId(board.getId())
                                        .title(board.getTitle())
                                        .titleImageUrl(board.getImageUrlList().getFirst())
                                        .build()
                        ).collect(Collectors.toList())
                ).build();
    }

}
