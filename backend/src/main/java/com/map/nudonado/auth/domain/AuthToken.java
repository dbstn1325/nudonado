package com.map.nudonado.auth.domain;

import com.map.nudonado.auth.domain.exception.NotMemberRefreshTokenException;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthToken {

    private String accessToken;
    private String refreshToken;

    @Builder
    public AuthToken(final String accessToken, final String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void validateHasSameRefreshToken(final String otherRefreshToken) {
        if (!refreshToken.equals(otherRefreshToken)) {
            throw new NotMemberRefreshTokenException();
        }
    }

}
