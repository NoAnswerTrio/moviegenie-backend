package com.moviegenie.member.service;

import com.moviegenie.member.controller.dto.MemberLoginDto;
import com.moviegenie.member.domain.MemberRepository;
import com.moviegenie.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void signUp(Member member) {
        memberRepository.save(member);
    }

    public boolean isDuplicatedEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public boolean isValidLoginInfo(MemberLoginDto loginDto) {
        Optional<Member> loginMember = memberRepository.findByEmail(loginDto.getEmail());

        if (loginMember.isPresent()) {
            if (loginMember.get().getPassword().equals(loginDto.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
