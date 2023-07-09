package com.moviegenie.fixture;

import com.moviegenie.member.domain.entity.Member;

public class MemberEntityFixture {
    public static Member create(String email, String password) {
        return new Member(email, password);
    }
}
