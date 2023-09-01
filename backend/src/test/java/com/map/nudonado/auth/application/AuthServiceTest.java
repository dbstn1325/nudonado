package com.map.nudonado.auth.application;

import com.map.nudonado.auth.domain.exception.InvalidTokenException;
import com.map.nudonado.auth.dto.request.TokenRenewalRequest;
import com.map.nudonado.auth.dto.response.AccessAndRefreshTokenResponse;
import com.map.nudonado.auth.dto.response.AccessTokenResponse;
import com.map.nudonado.common.config.ExternalApiConfig;
import com.map.nudonado.common.fixtures.OAuthFixtures;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.map.nudonado.common.fixtures.AuthFixtures.MEMBER_이메일;
import static com.map.nudonado.common.fixtures.OAuthFixtures.*;
import static com.map.nudonado.common.fixtures.OAuthFixtures.윤수;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ExternalApiConfig.class)
class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("토큰 생성을 하면 OAuth 서버에서 인증 후 토큰을 반환한다")
    @Test
    void 토큰_생성을_하면_OAuth_서버에서_인증_후_토큰을_반환한다() {
        //given & when
        AccessAndRefreshTokenResponse actual = authService.generateAccessAndRefreshToken(MEMBER.getOAuthMember());

        System.out.println(actual.getAccessToken());
        System.out.println(actual.getRefreshToken());
        //then
        assertAll(() -> {
            assertThat(actual.getAccessToken()).isNotEmpty();
            assertThat(actual.getRefreshToken()).isNotEmpty();
        });
    }

    @DisplayName("유저 정보를 Auth 서버로 전송하면 회원이 데이터베이스에 저장된다.")
    @Test
    void 유저_정보를_Auth_서버로_전송하면_회원이_데이터베이스에_저장된다() {
        //given & when
        authService.generateAccessAndRefreshToken(MEMBER.getOAuthMember());

        //then
        assertThat(memberRepository.existsByEmail(MEMBER_이메일)).isTrue();

        assertAll(() -> {
            // SutbOAuthClient가 반환하는 OAuthMember의 이메일
            assertThat(memberRepository.existsByEmail(MEMBER_이메일)).isTrue();
        });
    }

    @DisplayName("이미 가입된 회원에 대한 유저 정보를 전달받으면 추가로 회원이 생성되지 않는다")
    @Test
    void 이미_가입된_회원에_대한_유저_정보를_전달받으면_추가로_회원이_생성되지_않는다() {
        //given
        authService.generateAccessAndRefreshToken(MEMBER.getOAuthMember());

        // when
        authService.generateAccessAndRefreshToken(MEMBER.getOAuthMember());
        List<Member> actual = memberRepository.findAll();

        // then
        assertThat(actual).hasSize(1);
    }

    @DisplayName("이미 가입된 회원이고 저장된 RefreshToken이 있으면, 저장된 RefreshToken을 반환한다.")
    @Test
    void 이미_가입된_회원이고_저장된_RefreshToken이_있으면_저장된_RefreshToken을_반환한다() {
        //given
        AccessAndRefreshTokenResponse response = authService.generateAccessAndRefreshToken(MEMBER.getOAuthMember());

        //when
        AccessAndRefreshTokenResponse actual = authService.generateAccessAndRefreshToken(MEMBER.getOAuthMember());

        //then
        assertThat(actual.getRefreshToken()).isEqualTo(response.getRefreshToken());
    }

    @DisplayName("리프레시 토큰으로 새로운 엑세스 토큰을 발급한다.")
    @Test
    void 리프레시_토큰으로_새로운_엑세스_토큰을_발급한다() {
        //given
        AccessAndRefreshTokenResponse response = authService.generateAccessAndRefreshToken(MEMBER.getOAuthMember());
        TokenRenewalRequest tokenRenewalRequest = new TokenRenewalRequest(response.getRefreshToken());

        //when
        AccessTokenResponse accessTokenResponse = authService.generateAccessToken(tokenRenewalRequest);

        //then
        assertThat(accessTokenResponse.getAccessToken()).isNotEmpty();
    }

    @DisplayName("리프레시 토큰으로 새로운 엑세스 토큰을 발급 할 때, 리프레시 토큰이 존재하지 않으면 예외를 던진다.")
    @Test
    void 리프레시_토큰으로_새로운_엑세스_토큰을_발급_할_때_리프레시_토큰이_존재하지_않으면_예외를_던진다() {
        //given
        authService.generateAccessAndRefreshToken(MEMBER.getOAuthMember());
        TokenRenewalRequest tokenRenewalRequest = new TokenRenewalRequest("DummyRefreshToken");

        //when & then
        assertThatThrownBy(() -> authService.generateAccessToken(tokenRenewalRequest))
                .isInstanceOf(InvalidTokenException.class);
    }



}