package com.moviegenie.member.service;

import com.moviegenie.member.controller.dto.MemberLoginDto;
import com.moviegenie.member.domain.MemberRepository;
import com.moviegenie.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneralMemberService implements MemberService{

    private final MemberRepository memberRepository;
    @Override
    public void signUp(Member member) {
        memberRepository.save(member);
    }

    @Override
    public boolean isDuplicatedEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
    @Override
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
