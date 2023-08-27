package com.map.nudonado.auth.application;

import com.map.nudonado.auth.domain.AuthToken;
import com.map.nudonado.auth.domain.exception.NotCorrespondTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class AuthTokenCreator implements TokenCreator{

    private static final Map<Long, String> TOKEN_MAP = new ConcurrentHashMap<>();
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public AuthToken createAuthToken(Long memberId) {
        String accessToken = jwtTokenProvider.createAccessToken(String.valueOf(memberId));
        String refreshToken = createRefreshToken(memberId);

        return AuthToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String createRefreshToken(final Long memberId) {
        if(TOKEN_MAP.containsKey(memberId)) {
            String token = Optional.ofNullable(TOKEN_MAP.get(memberId))
                    .orElseThrow(NotCorrespondTokenException::new);
            return token;
        }

        String refreshToken = jwtTokenProvider.createRefreshToken(String.valueOf(memberId));
        return saveTokenMap(memberId, refreshToken);
    }

    private String saveTokenMap(final Long memberId, final String refreshToken) {
        TOKEN_MAP.put(memberId, refreshToken);
        return TOKEN_MAP.get(memberId);
    }
}
