package com.moviegenie.member;

import com.moviegenie.fixture.MemberEntityFixture;
import com.moviegenie.member.service.LoginService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static com.moviegenie.member.service.LoginService.MEMBER_ID;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    private LoginService loginService;

    private MockHttpSession httpSession;

    @BeforeEach
    void setUp() {
        httpSession = new MockHttpSession();

        loginService = new LoginService(httpSession);
    }

    @DisplayName("세션 로그인 성공 테스트")
    @Test
    void givenValidMember_whenLogin_thenSessionSetMember() {

        loginService.login(1L);

        assertThat(httpSession.getAttribute(MEMBER_ID)).isNotNull();
        assertThat(httpSession.getAttribute(MEMBER_ID)).isEqualTo(1L);
    }

    @DisplayName("세션 로그아웃 성공 테스트")
    @Test
    void givenSuccessLogin_whenLogout_thenSessionRemoveMember() {

        httpSession.setAttribute(MEMBER_ID, 1L);

        loginService.logout();

        assertThat(httpSession.getAttribute(MEMBER_ID)).isNull();
    }
}
