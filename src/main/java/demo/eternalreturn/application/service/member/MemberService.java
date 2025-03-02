package demo.eternalreturn.application.service.member;

import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.member.ReqMemberSearchCond;
import demo.eternalreturn.presentation.dto.request.member.ReqUpdateMemberDto;
import demo.eternalreturn.presentation.dto.response.member.ResGetMemberDto;
import demo.eternalreturn.presentation.dto.response.member.ResMemberSearchDto;
import demo.eternalreturn.presentation.dto.response.member.ResUpdateMemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {

    Page<ResMemberSearchDto> searchMember(ReqMemberSearchCond reqMemberSearchCond, Pageable pageable);

    ResGetMemberDto getMemberById(Long memberId);

    ResUpdateMemberDto updateUsernameById(Long memberId, ReqUpdateMemberDto reqUpdateMemberDto, CustomUserDetails userDetails);
}
