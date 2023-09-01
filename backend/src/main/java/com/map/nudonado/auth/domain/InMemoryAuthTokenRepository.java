package com.map.nudonado.auth.domain;

import com.map.nudonado.auth.domain.exception.NotCorrespondTokenException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryAuthTokenRepository implements TokenRepository {

    private static final Map<Long, String> TOKEN_REPOSITORY = new ConcurrentHashMap<>();

    @Override
    public String save(final Long memberId, final String refreshToken) {
        TOKEN_REPOSITORY.put(memberId, refreshToken);
        return TOKEN_REPOSITORY.get(memberId);
    }

    @Override
    public boolean exist(final Long memberId) {
        return TOKEN_REPOSITORY.containsKey(memberId);
    }

    @Override
    public String getToken(final Long memberId) {
        Optional<String> token = Optional.ofNullable(TOKEN_REPOSITORY.get(memberId));
        return token.orElseThrow(NotCorrespondTokenException::new);
    }
}
