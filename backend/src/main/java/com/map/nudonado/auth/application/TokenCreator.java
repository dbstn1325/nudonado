package com.map.nudonado.auth.application;

import com.map.nudonado.auth.domain.AuthToken;

public interface TokenCreator {

    AuthToken createAuthToken(final Long memberId);
}
