package com.map.nudonado.common.fixtures;

import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.SocialType;

public class MemberFixtures {

    public static final String 테스트_이메일 = "dbstn6477@gmail.com";
    public static final String 테스트_이름 = "윤수";
    public static final String 테스트_프로필 = "/profileImg.png";

    public static Member 테스트_멤버() {
        return new Member(테스트_이메일, 테스트_이름, 테스트_프로필, SocialType.GOOGLE);
    }
}
