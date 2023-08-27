package com.map.nudonado.auth.application;

import com.map.nudonado.auth.dto.OAuthMember;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;



public interface OAuthClient {
    OAuthMember getOAuthMember(final String code);
}
