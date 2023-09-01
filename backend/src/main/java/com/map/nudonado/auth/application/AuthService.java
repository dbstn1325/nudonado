package com.map.nudonado.auth.application;

import com.map.nudonado.auth.domain.AuthToken;
import com.map.nudonado.auth.domain.OAuthToken;
import com.map.nudonado.auth.domain.OAuthTokenRepository;
import com.map.nudonado.auth.dto.OAuthMember;
import com.map.nudonado.auth.dto.request.TokenRenewalRequest;
import com.map.nudonado.auth.dto.response.AccessAndRefreshTokenResponse;
import com.map.nudonado.auth.dto.response.AccessTokenResponse;
import com.map.nudonado.member.application.MemberService;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final OAuthTokenRepository oAuthTokenRepository;
    private final TokenCreator tokenCreator;

    @Transactional
    public AccessAndRefreshTokenResponse generateAccessAndRefreshToken(final OAuthMember oAuthMember) {
        Member foundMember = findMember(oAuthMember);

        OAuthToken oAuthToken = getOAuthToken(oAuthMember, foundMember);
        oAuthToken.change(oAuthMember.getRefreshToken());

        AuthToken authToken = tokenCreator.createAuthToken(foundMember.getId());
        return new AccessAndRefreshTokenResponse(authToken.getAccessToken(), authToken.getRefreshToken());
    }

    private Member findMember(final OAuthMember oAuthMember) {
        String email = oAuthMember.getEmail();
        if (memberRepository.existsByEmail(email)) {
            return memberRepository.getByEmail(email);
        }

        return saveMember(oAuthMember);
    }

    private Member saveMember(final OAuthMember oAuthMember) {
        return memberRepository.save(oAuthMember.toMember());
    }



    private OAuthToken getOAuthToken(final OAuthMember oAuthMember, final Member member) {
        Long memberId = member.getId();
        if (oAuthTokenRepository.existsByMemberId(memberId)) {
            return oAuthTokenRepository.getByMemberId(memberId);
        }

        return oAuthTokenRepository.save(OAuthToken.builder()
                        .member(member)
                        .refreshToken(oAuthMember.getRefreshToken())
                        .build());
    }


    public AccessTokenResponse generateAccessToken(final TokenRenewalRequest tokenRenewalRequest) {
        String refreshToken = tokenRenewalRequest.getRefreshToken();
        AuthToken authToken = tokenCreator.renewAuthToken(refreshToken);
        return new AccessTokenResponse(authToken.getAccessToken());
    }

}
