package com.moviegenie.member.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginResponseDto {

    private String email;
    private String password;

    public static MemberLoginResponseDto toDto(MemberLoginRequestDto dto) {
        return new MemberLoginResponseDto(dto.getEmail(), dto.getPassword());
    }
}
