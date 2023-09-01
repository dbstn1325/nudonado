package com.map.nudonado.auth.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class TokenRenewalRequest {

    @NotNull(message = "리프레시 토큰은 공백일 수 없습니다.")
    private String refreshToken;
}
