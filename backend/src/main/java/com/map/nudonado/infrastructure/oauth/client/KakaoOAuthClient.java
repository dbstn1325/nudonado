package com.map.nudonado.infrastructure.oauth.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.map.nudonado.auth.application.OAuthClient;
import com.map.nudonado.auth.dto.OAuthMember;
import com.map.nudonado.common.config.KakaoProperties;
import com.map.nudonado.infrastructure.oauth.dto.KakaoTokenResponse;
import com.map.nudonado.infrastructure.oauth.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class KakaoOAuthClient implements OAuthClient {

    private static final String JWT_DELIMITER = "\\.";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final KakaoProperties properties;

    public KakaoOAuthClient(final KakaoProperties properties, final RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.properties = properties;
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }



    @Override
    public OAuthMember getOAuthMember(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = generateTokenParams(code);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<KakaoTokenResponse> kakaoTokenResponseResponseEntity = restTemplate.postForEntity(properties.getTokenUri(), request, KakaoTokenResponse.class);
            KakaoTokenResponse kakaoTokenResponse = kakaoTokenResponseResponseEntity.getBody();


            UserInfo userInfo = getUserInfo(kakaoTokenResponse.getAccessToken());

            String refreshToken = kakaoTokenResponse.getRefreshToken();
            return new OAuthMember(userInfo.getEmail(), userInfo.getName(), refreshToken);

        } catch (final RestClientException e) {
            throw new IllegalArgumentException("OAuth Expception");
        }

    }

    public UserInfo getUserInfo(String access_Token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + access_Token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(properties.getUserUri(), HttpMethod.GET, entity, String.class);
        String result = response.getBody();

        UserInfo userInfo = null;
        try {
            JsonElement element = JsonParser.parseString(result);
            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String name = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();

            userInfo = new UserInfo(email, name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    private MultiValueMap<String, String> generateTokenParams(final String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id",  properties.getClientId());
        params.add("code", code);
        params.add("grant_type", properties.getGrantType());
        params.add("redirect_uri", properties.getRedirectUrl());

        return params;
    }

}
