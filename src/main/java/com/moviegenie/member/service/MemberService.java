package com.moviegenie.member.service;

import com.moviegenie.member.controller.dto.MemberLoginRequestDto;
import com.moviegenie.member.domain.entity.Member;

public interface MemberService {

    void signUp(Member member);

    void isDuplicatedEmail(String email);

    void isValidLoginInfo(MemberLoginRequestDto loginDto);
}
