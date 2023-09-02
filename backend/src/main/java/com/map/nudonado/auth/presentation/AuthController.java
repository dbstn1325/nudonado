package com.map.nudonado.auth.presentation;

import com.map.nudonado.auth.application.AuthService;
import com.map.nudonado.auth.application.OAuthClient;
import com.map.nudonado.auth.dto.LoginMember;
import com.map.nudonado.auth.dto.OAuthMember;
import com.map.nudonado.auth.dto.request.TokenRenewalRequest;
import com.map.nudonado.auth.dto.request.TokenRequest;
import com.map.nudonado.auth.dto.response.AccessAndRefreshTokenResponse;
import com.map.nudonado.auth.dto.response.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;



@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OAuthClient oAuthClient;
    private final AuthService authService;

    /**
     * 유저가 최초 로그인 회원가입 시, accessToken, refreshToken 발급
     * @param tokenRequest
     * @return Flutter의 callbackUrlScheme를 통해 redirect 실행
     */
    @GetMapping("/kakao/sign_in")
    public String generateAccessAndRefreshToken(@RequestParam("code") @Valid TokenRequest tokenRequest) {

        OAuthMember oAuthMember = oAuthClient.getOAuthMember(tokenRequest.getCode());
        AccessAndRefreshTokenResponse response = authService.generateAccessAndRefreshToken(oAuthMember);

        System.out.println(response.getAccessToken());
        System.out.println(response.getRefreshToken());
        return "redirect:webauthcallback://success?";
    }

    /**
     * refreshToken을 통해 accessToken 재발급
     * @param tokenRenewalRequest
     * @return accessToken
     */
    @PostMapping("/token/access")
    @ResponseBody
    public ResponseEntity<AccessTokenResponse> generateAccessToken(
            @Valid @RequestBody final TokenRenewalRequest tokenRenewalRequest) {
        AccessTokenResponse response = authService.generateAccessToken(tokenRenewalRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate/token")
    @ResponseBody
    public ResponseEntity<Void> validateToken(@AuthenticationPrincipal final LoginMember loginMember) {
        return ResponseEntity.ok().build();
    }

}