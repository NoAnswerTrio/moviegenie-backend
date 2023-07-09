package com.moviegenie.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final HttpSession httpSession;
    public static final String MEMBER_ID = "member_id";
    public void login(Long id) {
        httpSession.setAttribute(MEMBER_ID, id);
    }
    public void logout() {
        httpSession.removeAttribute(MEMBER_ID);
    }
}
