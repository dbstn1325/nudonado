package com.map.nudonado.auth.presentation;

import com.map.nudonado.auth.domain.exception.EmptyAuthorizationHeaderException;
import com.map.nudonado.auth.domain.exception.WrongFormatTokenException;
import org.springframework.http.HttpHeaders;


import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

public class AuthorizationExtractor {

    private static final String BEARER_TYPE = "Bearer ";

    public static String extract(final HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(authorizationHeader)) {
            throw new EmptyAuthorizationHeaderException();
        }

        validateAuthorizationFormat(authorizationHeader);
        return authorizationHeader.substring(BEARER_TYPE.length()).trim();
    }

    private static void validateAuthorizationFormat(final String authorizationHeader) {
        if (!authorizationHeader.toLowerCase().startsWith(BEARER_TYPE.toLowerCase())) {
            throw new WrongFormatTokenException();
        }
    }
}
