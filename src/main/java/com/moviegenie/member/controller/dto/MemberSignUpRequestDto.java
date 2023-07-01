package com.moviegenie.member.controller.dto;


import com.moviegenie.member.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class MemberSignUpRequestDto {

    @NotBlank
    @Email( message = "유효하지 않은 이메일입니다.",
            regexp = "^(.+)@(\\S+)$" )
    private String email;

    @NotBlank
    @Pattern( message = "8글자 이상 입력하세요.",
              regexp = "^.{8,}$" )
    private String password;
    public static Member toEntity(MemberSignUpRequestDto dto) {
        return Member.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }
}
