package com.moviegenie.member.controller;

import com.moviegenie.member.controller.dto.MemberSignUpRequestDto;
import com.moviegenie.member.controller.dto.MemberSignUpResponseDto;
import com.moviegenie.member.domain.entity.Member;
import com.moviegenie.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    private final MemberService memberService;

    @PostMapping("sign-up")
    public MemberSignUpResponseDto signUp(HttpServletRequest request, @RequestBody @Valid MemberSignUpRequestDto dto) {

        Member member = MemberSignUpRequestDto.toEntity(dto);

        memberService.signUp(member);

        Iterator<String> headersName = request.getHeaderNames().asIterator();
        while(headersName.hasNext()) {
            String nextHeader = headersName.next();

            log.info(nextHeader + " : " + request.getHeader(nextHeader));
        }

        return MemberSignUpResponseDto.toDto(member);
    }
}
