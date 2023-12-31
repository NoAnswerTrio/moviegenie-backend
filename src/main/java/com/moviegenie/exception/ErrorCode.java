package com.moviegenie.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    MEMBER_EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "입력하신 이메일은 없는 이메일입니다."),
    MEMBER_PASSWORD_NOT_FOUND(HttpStatus.NOT_FOUND, "입력하신 비밀번호는 없는 비밀번호입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
