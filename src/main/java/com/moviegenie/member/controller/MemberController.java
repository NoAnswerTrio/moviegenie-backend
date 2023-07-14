package com.moviegenie.member.controller;

import com.moviegenie.common.response.Response;
import com.moviegenie.member.controller.dto.MemberLoginRequestDto;
import com.moviegenie.member.controller.dto.MemberLoginResponseDto;
import com.moviegenie.member.controller.dto.MemberSignUpRequestDto;
import com.moviegenie.member.controller.dto.MemberSignUpResponseDto;
import com.moviegenie.member.domain.MemberRepository;
import com.moviegenie.member.domain.entity.Member;
import com.moviegenie.member.service.SessionLoginService;
import com.moviegenie.member.service.GeneralMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Iterator;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@CrossOrigin(origins="*", allowedHeaders = "*")
@Slf4j
@RestController
public class MemberController {

    private final GeneralMemberService memberService;
    private final MemberRepository memberRepository;
    private final SessionLoginService loginService;

    @PostMapping("sign-up")
    public Response<MemberSignUpResponseDto> signUp(HttpServletRequest request, @RequestBody @Valid MemberSignUpRequestDto dto) {

        Member member = MemberSignUpRequestDto.toEntity(dto);

        memberService.signUp(member);

        Iterator<String> headersName = request.getHeaderNames().asIterator();
        while(headersName.hasNext()) {
            String nextHeader = headersName.next();

            log.info(nextHeader + " : " + request.getHeader(nextHeader));
        }

        return Response.success(MemberSignUpResponseDto.toDto(member));
    }

    @GetMapping("duplicated/{email}")
    public Response<Void> isDuplicatedEmail(@PathVariable String email) {
        memberService.isDuplicatedEmail(email);
        return Response.success();
    }

    @PostMapping("login")
    public Response<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto dto) {
        memberService.isValidLoginInfo(dto);
        return Response.success(MemberLoginResponseDto.toDto(dto));
    }

    @GetMapping("/logout")
    public Response<Void> logout() {
        loginService.logout();
        return Response.success();
    }
}
