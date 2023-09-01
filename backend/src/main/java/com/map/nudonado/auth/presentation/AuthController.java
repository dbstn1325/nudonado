package com.map.nudonado.auth.presentation;

import com.map.nudonado.auth.application.AuthService;
import com.map.nudonado.auth.application.OAuthClient;
import com.map.nudonado.auth.dto.OAuthMember;
import com.map.nudonado.auth.dto.request.TokenRequest;
import com.map.nudonado.auth.dto.response.AccessAndRefreshTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OAuthClient oAuthClient;
    private final AuthService authService;

    @GetMapping("/kakao/sign_in")
    public String generateAccessAndRefreshToken(@RequestParam("code") @Valid TokenRequest tokenRequest) {

        OAuthMember oAuthMember = oAuthClient.getOAuthMember(tokenRequest.getCode());
        AccessAndRefreshTokenResponse response = authService.generateAccessAndRefreshToken(oAuthMember);

        return "redirect:webauthcallback://success?";
    }

}