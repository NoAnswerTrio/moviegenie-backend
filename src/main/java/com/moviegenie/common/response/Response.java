package com.moviegenie.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class Response<T> {

    private String resultCode;
    private T result;

    public static Response<Void> error(String errorCode) {
        return new Response<>(errorCode, null);
    }
    public static <T> Response<T> success() {
        return new Response<T>("SUCCESS", null);
    }
    public static <T> Response<T> success(T result) {
        return new Response<>("SUCCESS", result);
    }
}
