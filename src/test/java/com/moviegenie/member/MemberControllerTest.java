package com.moviegenie.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviegenie.member.controller.MemberController;
import com.moviegenie.member.controller.dto.MemberSignUpRequestDto;
import com.moviegenie.member.service.GeneralMemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DisplayName("회원 컨트롤러 테스트")
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GeneralMemberService memberService;

    @DisplayName("회원 가입 컨트롤러 테스트")
    @Test
    void givenValidMember_whenSignUp_ReturnOkResponse() throws Exception {

        MemberSignUpRequestDto dto = new MemberSignUpRequestDto("test@email", "12345678");

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/member/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
