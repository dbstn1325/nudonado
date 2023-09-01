package com.map.nudonado.auth.application;

import com.map.nudonado.auth.domain.AuthToken;
import com.map.nudonado.auth.domain.TokenRepository;
import com.map.nudonado.auth.domain.exception.NotCorrespondTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class AuthTokenCreator implements TokenCreator{

    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;


    public AuthToken createAuthToken(final Long memberId) {
        String accessToken = tokenProvider.createAccessToken(String.valueOf(memberId));
        String refreshToken = createRefreshToken(memberId);
        return new AuthToken(accessToken, refreshToken);
    }

    private String createRefreshToken(final Long memberId) {
        if (tokenRepository.exist(memberId)) {
            return tokenRepository.getToken(memberId);
        }
        String refreshToken = tokenProvider.createRefreshToken(String.valueOf(memberId));
        return tokenRepository.save(memberId, refreshToken);
    }

    public AuthToken renewAuthToken(final String refreshToken) {
        tokenProvider.validateToken(refreshToken);
        Long memberId = Long.valueOf(tokenProvider.getPayload(refreshToken));

        String accessTokenForRenew = tokenProvider.createAccessToken(String.valueOf(memberId));
        String refreshTokenForRenew = tokenRepository.getToken(memberId);

        AuthToken renewalAuthToken = new AuthToken(accessTokenForRenew, refreshTokenForRenew);
        renewalAuthToken.validateHasSameRefreshToken(refreshToken);
        return renewalAuthToken;
    }

}
