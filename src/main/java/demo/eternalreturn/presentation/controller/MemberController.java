package demo.eternalreturn.presentation.controller;

import demo.eternalreturn.application.service.member.MemberService;
import demo.eternalreturn.infrastructure.security.user.custom.CustomUserDetails;
import demo.eternalreturn.presentation.dto.request.member.ReqMemberSearchCond;
import demo.eternalreturn.presentation.dto.request.member.ReqUpdateMemberDto;
import demo.eternalreturn.presentation.dto.response.PageResponseDto;
import demo.eternalreturn.presentation.dto.response.ResponseDto;
import demo.eternalreturn.presentation.dto.response.member.ResGetMemberDto;
import demo.eternalreturn.presentation.dto.response.member.ResMemberSearchDto;
import demo.eternalreturn.presentation.dto.response.member.ResUpdateMemberDto;
import demo.eternalreturn.presentation.exception.ResultMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static demo.eternalreturn.presentation.exception.ResultMessage.*;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;


    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> searchMember(
            @ModelAttribute ReqMemberSearchCond reqMemberSearchCond,
            @PageableDefault Pageable pageable
    ) {
        Page<ResMemberSearchDto> resMemberSearchDtoList = memberService.searchMember(reqMemberSearchCond, pageable);
        PageResponseDto<ResMemberSearchDto> page = new PageResponseDto<>(resMemberSearchDtoList);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, page));
    }

    @GetMapping("/{memberId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getMemberById(@PathVariable Long memberId) {
        ResGetMemberDto resGetMemberDto = memberService.getMemberById(memberId);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resGetMemberDto));
    }

    @PatchMapping("/{memberId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> updateUsernameById(@PathVariable Long memberId,
                                                @RequestBody ReqUpdateMemberDto reqUpdateMemberDto,
                                                @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        ResUpdateMemberDto resUpdateMemberDto = memberService.updateUsernameById(memberId, reqUpdateMemberDto, userDetails);
        return ResponseEntity.ok(new ResponseDto<>(OK, Success, resUpdateMemberDto));
    }
}
