package com.moviegenie.member.service;

import com.moviegenie.member.controller.dto.MemberLoginDto;
import com.moviegenie.member.domain.entity.Member;

public interface MemberService {

    void signUp(Member member);

    boolean isDuplicatedEmail(String email);

    boolean isValidLoginInfo(MemberLoginDto loginDto);
}
