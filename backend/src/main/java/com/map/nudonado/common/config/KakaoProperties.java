package com.map.nudonado.common.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;


import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("oauth.kakao")
@ConstructorBinding
@AllArgsConstructor
@Getter
public class KakaoProperties {

    private final String clientId;
    private final String tokenUri;
    private final String userUri;
    private final String grantType;
    private final String redirectUrl;
}
