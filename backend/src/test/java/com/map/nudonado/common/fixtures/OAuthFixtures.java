package com.map.nudonado.common.fixtures;

import com.map.nudonado.auth.dto.OAuthMember;

public enum OAuthFixtures {

    관리자(관리자()),
    윤수(윤수()),
    MEMBER(MEMBER());

    private OAuthMember oAuthMember;

    OAuthFixtures(final OAuthMember oAuthMember) {
        this.oAuthMember = oAuthMember;
    }

    private static OAuthMember 관리자() {
        String 관리자_이메일 = "dallog.admin@gmail.com";
        String 관리자_이름 = "관리자";
        String 관리자_프로필 = "/admin.png";
        String 관리자_REFRESH_TOKEN = "aaaaaaaaaa.bbbbbbbbbb.cccccccccc";
        return new OAuthMember(관리자_이메일, 관리자_이름, 관리자_프로필, 관리자_REFRESH_TOKEN);
    }

    private static OAuthMember 윤수() {
        String 윤수_이메일 = "dbstn6477@email.com";
        String 윤수_이름 = "윤수";
        String 윤수_프로필 = "/yoonsu.png";
        String 윤수_REFRESH_TOKEN = "aaaaaaaaaa.bbbbbbbbbb.cccccccccc";
        return new OAuthMember(윤수_이메일, 윤수_이름, 윤수_프로필, 윤수_REFRESH_TOKEN);
    }

    private static OAuthMember MEMBER() {
        String MEMBER_이메일 = "member@email.com";
        String MEMBER_이름 = "member";
        String MEMBER_프로필 = "/member.png";
        String MEMBER_REFRESH_TOKEN = "aaaaaaaaaa.bbbbbbbbbb.cccccccccc";
        return new OAuthMember(MEMBER_이메일, MEMBER_이름, MEMBER_프로필, MEMBER_REFRESH_TOKEN);
    }

    public OAuthMember getOAuthMember() {
        return oAuthMember;
    };

}
