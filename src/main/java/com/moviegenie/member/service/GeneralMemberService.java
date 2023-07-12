package com.moviegenie.member.service;

import com.moviegenie.exception.ErrorCode;
import com.moviegenie.exception.MovieGenieAppException;
import com.moviegenie.member.controller.dto.MemberLoginRequestDto;
import com.moviegenie.member.domain.MemberRepository;
import com.moviegenie.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralMemberService implements MemberService{

    private final MemberRepository memberRepository;
    @Override
    public void signUp(Member member) {
        memberRepository.findByEmail(member.getEmail()).ifPresent(e -> {
            throw new MovieGenieAppException(ErrorCode.DUPLICATED_EMAIL);
        });

        memberRepository.save(member);
    }

    @Override
    public void isDuplicatedEmail(String email) {
        if (memberRepository.existsByEmail(email)) throw new MovieGenieAppException(ErrorCode.DUPLICATED_EMAIL);
    }
    @Override
    public void isValidLoginInfo(MemberLoginRequestDto loginDto) {
        String loginEmail = loginDto.getEmail();
        String loginDtoPassword = loginDto.getPassword();

        Member member = memberRepository.findByEmail(loginEmail).orElseThrow(
                () -> new MovieGenieAppException(ErrorCode.MEMBER_EMAIL_NOT_FOUND));

        if (!member.getPassword().equals(loginDtoPassword)) throw new MovieGenieAppException(ErrorCode.MEMBER_PASSWORD_NOT_FOUND);
    }
}
