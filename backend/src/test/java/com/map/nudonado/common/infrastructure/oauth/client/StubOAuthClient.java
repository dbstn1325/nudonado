package com.map.nudonado.common.infrastructure.oauth.client;

import com.map.nudonado.auth.application.OAuthClient;
import com.map.nudonado.auth.dto.OAuthMember;
import com.map.nudonado.common.fixtures.OAuthFixtures;

public class StubOAuthClient implements OAuthClient {

    @Override
    public OAuthMember getOAuthMember(final String code) {
        return OAuthFixtures.윤수.getoAuthMember();
    }
}
