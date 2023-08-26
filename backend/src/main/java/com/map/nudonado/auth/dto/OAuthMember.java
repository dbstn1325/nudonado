package com.map.nudonado.auth.dto;

import com.map.nudonado.member.domain.Member;
import lombok.Getter;

@Getter
public class OAuthMember {

    private final String email;
    private final String displayName;
    private final String profileImageUrl;

    public OAuthMember(String email, String displayName, String profileImageUrl) {
        this.email = email;
        this.displayName = displayName;
        this.profileImageUrl = profileImageUrl;
    }
}
