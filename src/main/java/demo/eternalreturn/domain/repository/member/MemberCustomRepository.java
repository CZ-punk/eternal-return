package demo.eternalreturn.domain.repository.member;

import demo.eternalreturn.presentation.dto.request.member.ReqMemberSearchCond;
import demo.eternalreturn.presentation.dto.response.member.ResMemberSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberCustomRepository {

    Page<ResMemberSearchDto> searchMember(ReqMemberSearchCond reqMemberSearchCond, Pageable pageable);
}
