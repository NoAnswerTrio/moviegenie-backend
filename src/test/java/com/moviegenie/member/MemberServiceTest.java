package com.moviegenie.member;

import com.moviegenie.fixture.MemberEntityFixture;
import com.moviegenie.member.domain.MemberRepository;
import com.moviegenie.member.domain.entity.Member;
import com.moviegenie.member.service.GeneralMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@DisplayName("회원 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    @InjectMocks
    private GeneralMemberService memberService;
    @Mock
    private MemberRepository memberRepository;

    @DisplayName("회원가입 성공 테스트")
    @Test
    void givenValidMemberDto_whenSignUp_thenSaveMember() {

        Member validMember = MemberEntityFixture.create("test@email", "12345678");

        memberService.signUp(validMember);

        verify(memberRepository, times(1)).save(validMember);
    }

}