package com.map.nudonado.infrastructure.oauth.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.Valid;


@ConstructorBinding
public class GoogleProperties {

    @Value("{oauth.google.client_id}")
    private final String clientId;
    @Value("{oauth.google.client_secret}")
    private final String clientSecret;
    private final String oAuthEndPoint;
    private final String responseType;
    private final List<String> scopes;
    @Value("{oauth.google.token_uri}")
    private final String tokenUri;
    private final String accessType;

    public GoogleProperties(final String clientId, final String clientSecret, final String oAuthEndPoint,
                            final String responseType, final List<String> scopes, final String tokenUri,
                            final String accessType) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.oAuthEndPoint = oAuthEndPoint;
        this.responseType = responseType;
        this.scopes = scopes;
        this.tokenUri = tokenUri;
        this.accessType = accessType;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getOAuthEndPoint() {
        return oAuthEndPoint;
    }

    public String getResponseType() {
        return responseType;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public String getTokenUri() {
        return tokenUri;
    }

    public String getAccessType() {
        return accessType;
    }
}
