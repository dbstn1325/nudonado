package com.map.nudonado.common.fixtures;

import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.SocialType;
import com.map.nudonado.member.dto.MemberUpdateRequest;

public class MemberFixtures {

    public static final String 테스트_이메일 = "dbstn6477@gmail.com";
    public static final String 테스트_이름 = "윤수";
    public static final String 테스트_수정_이름 = "수정한윤수";
    public static final String 테스트_수정_이름_길이초과 = "0".repeat(Member.getMaxDisplayNameLength()+1);
    public static final String 테스트_프로필 = "/profileImg.png";

    public static Member 테스트_멤버() {
        return new Member(테스트_이메일, 테스트_이름, 테스트_프로필, SocialType.GOOGLE);
    }

    public static MemberUpdateRequest 테스트_멤버_수정_요청() {
        return new MemberUpdateRequest(테스트_수정_이름);
    }

    public static MemberUpdateRequest 테스트_멤버_수정_요청_길이초과() {
        return new MemberUpdateRequest(테스트_수정_이름_길이초과);
    }
}
