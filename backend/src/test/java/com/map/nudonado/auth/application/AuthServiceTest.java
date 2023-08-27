package com.map.nudonado.auth.application;

import com.map.nudonado.auth.dto.response.AccessAndRefreshTokenResponse;
import com.map.nudonado.common.config.ExternalApiConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.map.nudonado.common.fixtures.OAuthFixtures.윤수;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ExternalApiConfig.class)
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @DisplayName("토큰 생성을 하면 OAuth 서버에서 인증 후 토큰을 반환한다")
    @Test
    void 토큰_생성을_하면_OAuth_서버에서_인증_후_토큰을_반환한다() {
        //given & when
        AccessAndRefreshTokenResponse actual = authService.generateAccessAndRefreshToken(윤수.getoAuthMember());

        //then
        assertAll(() -> {
            assertThat(actual.getAccessToken()).isNotEmpty();
            assertThat(actual.getRefreshToken()).isNotEmpty();
        });
    }


}