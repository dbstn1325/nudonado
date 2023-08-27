package com.map.nudonado.auth.dto;

import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.SocialType;
import lombok.Getter;

@Getter
public class OAuthMember {

    private final String email;
    private final String displayName;
    private final String profileImageUrl;
    private final String refreshToken;

    public OAuthMember(String email, String displayName, String profileImageUrl, String refreshToken) {
        this.email = email;
        this.displayName = displayName;
        this.profileImageUrl = profileImageUrl;
        this.refreshToken = refreshToken;
    }

    public Member toMember() {
        return Member.builder()
                    .email(email)
                    .displayName(displayName)
                    .profileImageUrl(profileImageUrl)
                    .socialType(SocialType.GOOGLE)
                    .build();
    }
}
