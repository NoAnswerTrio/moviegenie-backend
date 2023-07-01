package com.moviegenie.member.controller;

import com.moviegenie.member.controller.dto.MemberSignUpRequestDto;
import com.moviegenie.member.domain.entity.Member;
import com.moviegenie.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberController {

    private final MemberService memberService;
    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid MemberSignUpRequestDto dto) {
        Member member = MemberSignUpRequestDto.toEntity(dto);
        memberService.signUp(member);
        return ResponseEntity.ok().build();
    }
}
