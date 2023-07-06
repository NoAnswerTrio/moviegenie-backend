package com.moviegenie.member.controller;

import com.moviegenie.member.controller.dto.MemberLoginDto;
import com.moviegenie.member.controller.dto.MemberSignUpRequestDto;
import com.moviegenie.member.controller.dto.MemberSignUpResponseDto;
import com.moviegenie.member.domain.entity.Member;
import com.moviegenie.member.service.LoginService;
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
    private final LoginService loginService;

    @PostMapping("sign-up")
    public ResponseEntity<MemberSignUpResponseDto> signUp(HttpServletRequest request, @RequestBody @Valid MemberSignUpRequestDto dto) {

        Member member = MemberSignUpRequestDto.toEntity(dto);

        memberService.signUp(member);

        Iterator<String> headersName = request.getHeaderNames().asIterator();
        while(headersName.hasNext()) {
            String nextHeader = headersName.next();

            log.info(nextHeader + " : " + request.getHeader(nextHeader));
        }

        return ResponseEntity.ok(MemberSignUpResponseDto.toDto(member));
    }

    @GetMapping("duplicated/{email}")
    public ResponseEntity<?> isDuplicatedEmail(@PathVariable String email) {
        boolean duplicatedEmail = memberService.isDuplicatedEmail(email);

        if (duplicatedEmail) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody MemberLoginDto dto) {
        boolean login = loginService.login(dto.getEmail(), dto.getPassword());
        if (login) return ResponseEntity.ok().build();
        else return  ResponseEntity.notFound().build();
    }
}
