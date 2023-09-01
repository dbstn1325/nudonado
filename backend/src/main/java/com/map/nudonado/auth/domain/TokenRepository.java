package com.map.nudonado.auth.domain;

public interface TokenRepository {

    String save(final Long memberId, final String refreshToken);

    boolean exist(final Long memberId);

    String getToken(final Long memberId);
}
