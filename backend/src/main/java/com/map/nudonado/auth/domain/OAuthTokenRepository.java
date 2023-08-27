package com.map.nudonado.auth.domain;

import com.map.nudonado.auth.domain.exception.NotFoundOAuthTokenException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OAuthTokenRepository extends JpaRepository<OAuthToken, Long> {

    boolean existsByMemberId(final Long memberId);

    @Query("SELECT o "
            + "FROM OAuthToken o "
            + "WHERE o.member.id = :memberId"
    )
    Optional<OAuthToken> findByMemberId(final Long memberId);

    default OAuthToken getByMemberId(final Long memberId) {
        return findByMemberId(memberId)
                .orElseThrow(NotFoundOAuthTokenException::new);
    }



}
