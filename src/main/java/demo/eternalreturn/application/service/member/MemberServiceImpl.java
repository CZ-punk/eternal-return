package demo.eternalreturn.application.service.member;

import demo.eternalreturn.domain.model.Member.Member;
import demo.eternalreturn.domain.repository.member.MemberCustomRepository;
import demo.eternalreturn.domain.repository.member.MemberRepository;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.member.ReqMemberSearchCond;
import demo.eternalreturn.presentation.dto.request.member.ReqUpdateMemberDto;
import demo.eternalreturn.presentation.dto.response.member.ResGetMemberDto;
import demo.eternalreturn.presentation.dto.response.member.ResMemberSearchDto;
import demo.eternalreturn.presentation.dto.response.member.ResUpdateMemberDto;
import demo.eternalreturn.presentation.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberCustomRepository memberCustomRepository;


    @Override
    @Transactional(readOnly = true)
    public Page<ResMemberSearchDto> searchMember(ReqMemberSearchCond reqMemberSearchCond, Pageable pageable) {
        return memberCustomRepository.searchMember(reqMemberSearchCond, pageable);

    }

    @Override
    @Transactional(readOnly = true)
    public ResGetMemberDto getMemberById(Long memberId) {
        Member member = memberRepository.findByIdAndDeleteFalseJoinBoardList(memberId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found member by id: " + memberId));

        return ResGetMemberDto.toDto(member);
    }

    @Override
    @Transactional
    public ResUpdateMemberDto updateUsernameById(Long memberId, ReqUpdateMemberDto reqUpdateMemberDto, CustomUserDetails userDetails) {
        if (!userDetails.getMemberId().equals(memberId))
            throw new CustomException(HttpStatus.FORBIDDEN, "not allowed to update member with id: " + memberId);

        Member member = memberRepository.findByIdAndIsDeleteFalse(memberId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found member by id: " + memberId));

        if (member.getUsername().equals(reqUpdateMemberDto.getUsername()))
            throw new CustomException(HttpStatus.ALREADY_REPORTED, "username already reported");

        Member duplicatedMember = memberRepository.findByUsername(reqUpdateMemberDto.getUsername()).orElse(null);
        if (duplicatedMember != null) throw new CustomException(HttpStatus.CONFLICT, "duplicated member");

        member.updateUsername(reqUpdateMemberDto.getUsername());
        memberRepository.save(member);

        return ResUpdateMemberDto.builder()
                .id(member.getId())
                .username(reqUpdateMemberDto.getUsername())
                .build();
    }

    @Override
    @Transactional
    public void deleteMemberById(Long memberId, CustomUserDetails userDetails) {
        if (!userDetails.getMemberId().equals(memberId))
            throw new CustomException(HttpStatus.FORBIDDEN, "not allowed to delete member with id: " + memberId);
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "not found member by id: " + memberId));

        member.softDelete(userDetails.getName());
        memberRepository.save(member);
    }
}
