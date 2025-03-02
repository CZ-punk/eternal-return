package demo.eternalreturn.presentation.dto.response.member;

import demo.eternalreturn.domain.constant.Role;
import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.model.Member.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResMemberSearchDto {

    private Long memberId;
    private String loginId;
    private String username;
    private Set<Role> roles;
    private List<BoardDto> boardDtoList;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;
    private boolean isDelete;

    public static ResMemberSearchDto toDto(Member member) {
        return ResMemberSearchDto.builder()
                .memberId(member.getId())
                .loginId(member.getLoginId())
                .username(member.getUsername())
                .roles(member.getRoles().stream().map(MemberRole::getRole).collect(Collectors.toSet()))
                .boardDtoList(member.getBoardList().stream().map(board ->
                                BoardDto.builder()
                                        .id(board.getId())
                                        .title(board.getTitle())
                                        .build())
                        .collect(Collectors.toList()))
                .createdAt(member.getCreatedAt())
                .createdBy(member.getCreatedBy())
                .updatedAt(member.getUpdatedAt())
                .updatedBy(member.getUpdatedBy())
                .deletedAt(member.getDeletedAt())
                .deletedBy(member.getDeletedBy())
                .isDelete(member.isDelete())
                .build();
    }

    @Builder
    public static class BoardDto {
        private Long id;
        private String title;
    }

}
