package com.map.nudonado.auth.application;

import com.map.nudonado.auth.dto.OAuthMember;

public interface OAutchClient {

    OAuthMember getOAuthMember(final String code);
}
