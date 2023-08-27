package com.map.nudonado.common.config;

import com.map.nudonado.auth.application.OAuthClient;
import com.map.nudonado.common.infrastructure.oauth.client.StubOAuthClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ExternalApiConfig {

    @Bean
    public OAuthClient oAuthClient() {
        return new StubOAuthClient();
    }
}
