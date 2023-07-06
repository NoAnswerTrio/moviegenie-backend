package com.moviegenie.member.service;

import com.moviegenie.member.domain.MemberRepository;
import com.moviegenie.member.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    public boolean login(String email, String password) {

        boolean existsByEmail = memberRepository.existsByEmail(email);

        if (existsByEmail) {
            String memberPassword = isExistEmail(email);
            if (memberPassword.equals(password)) {
                return true;
            }
        }
        return false;
    }

    private String isExistEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        return member.get().getPassword();
    }
}
