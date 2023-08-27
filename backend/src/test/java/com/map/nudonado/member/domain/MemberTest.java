package com.map.nudonado.member.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.map.nudonado.common.fixtures.MemberFixtures.*;

public class MemberTest {

    @DisplayName("회원을 생성한다.")
    @Test
    void 회원을_생성한다() {
        // given & when & then
        Assertions.assertDoesNotThrow(() -> new Member(테스트_이메일, 테스트_이름, 테스트_프로필, SocialType.GOOGLE));
    }



    
}
