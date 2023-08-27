package com.map.nudonado.auth.domain;

import com.map.nudonado.common.BaseEntity;
import com.map.nudonado.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "oauth_tokens")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_id", nullable = false)
    private Member member;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Builder
    public OAuthToken(Member member, String refreshToken) {
        this.member = member;
        this.refreshToken = refreshToken;
    }

    public void change(final String refreshToken) {
        if(!Objects.isNull(refreshToken)) {
            this.refreshToken = refreshToken;
        }
    }
}
