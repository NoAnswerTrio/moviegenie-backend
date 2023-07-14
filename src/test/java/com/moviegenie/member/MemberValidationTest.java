package com.moviegenie.member;

import com.moviegenie.member.controller.dto.MemberSignUpRequestDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("회원 유효성 검사 테스트")
public class MemberValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @DisplayName("@NotBlank 테스트")
    //@ParameterizedTest
    @ValueSource(strings = {"", " "})
    void notBlankTest(String input) {
        MemberSignUpRequestDto dto = new MemberSignUpRequestDto(input, "12345678");

        Set<ConstraintViolation<MemberSignUpRequestDto>> violations = validator.validate(dto);

        ConstraintViolation<MemberSignUpRequestDto> violation = violations.stream()
                .filter(v -> v.getPropertyPath().toString().equals("email"))
                .findFirst()
                .get();

        assertThat(violation.getMessage()).isEqualTo("공백일 수 없습니다");
    }

    @DisplayName("유효하지 않은 이메일을 입력하면 유효성 검사에 실패한다.")
    @Test
    void emailTest() {
        MemberSignUpRequestDto dto = new MemberSignUpRequestDto("test", "12345678");

        Set<ConstraintViolation<MemberSignUpRequestDto>> violations = validator.validate(dto);

        ConstraintViolation<MemberSignUpRequestDto> violation = violations.iterator().next();

        assertThat(violation.getMessage()).isEqualTo("유효하지 않은 이메일입니다.");
    }

    @DisplayName("유효하지 않은 비밀번호를 입력하면 유효성 검사에 실패한다.")
    @Test
    void passwordTest() {
        MemberSignUpRequestDto dto = new MemberSignUpRequestDto("test@email", "7글자이하");

        Set<ConstraintViolation<MemberSignUpRequestDto>> violations = validator.validate(dto);

        ConstraintViolation<MemberSignUpRequestDto> violation = violations.iterator().next();

        assertThat(violation.getMessage()).isEqualTo("8글자 이상 입력하세요.");
    }
}
