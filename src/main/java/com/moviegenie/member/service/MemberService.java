package com.moviegenie.member.service;

import com.moviegenie.member.domain.MemberRepository;
import com.moviegenie.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
