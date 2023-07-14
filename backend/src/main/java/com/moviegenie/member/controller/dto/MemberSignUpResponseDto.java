package com.moviegenie.member.controller.dto;

import com.moviegenie.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignUpResponseDto {

    private String email;
    private String password;

    public static MemberSignUpResponseDto toDto (Member member) {
        return new MemberSignUpResponseDto(member.getEmail(), member.getPassword());
    }
}
