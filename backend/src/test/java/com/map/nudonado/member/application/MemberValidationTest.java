package com.map.nudonado.member.application;

import com.map.nudonado.member.dto.JoinDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MemberValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @DisplayName("회원의 email을 형식에 맞게 입력한 경우, 정상적으로 유효성 검사에 통과한다.")
    @Test
    void isCorrectValidation(){
        JoinDto joinDto = JoinDto.builder()
                .email("dbstn6477@gmail.com")
                .displayName("테스트")
                .build();

        Set<ConstraintViolation<JoinDto>> violations = validator.validate(joinDto);
        assertThat(violations).isEmpty();
    }

    @DisplayName("회원의 email을 형식에 맞지 않게 입력한 경우, 정상적으로 유효성 검사에 통과한다.")
    @ParameterizedTest
    @ValueSource(strings = {"dbstn6477", "dbstn6477@", "dbstn6477@gmail", "@gmail", "@gmail.com"})
    void isNotValidEmail(final String email){
        JoinDto joinDto = JoinDto.builder()
                .email(email)
                .displayName("테스트")
                .build();

        Set<ConstraintViolation<JoinDto>> violations = validator.validate(joinDto);
        assertEquals("유효하지 않은 이메일 형식입니다.", violations.iterator().next().getMessage());
    }
}
