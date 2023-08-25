package com.map.nudonado.booth.domain;

import com.map.nudonado.member.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.map.nudonado.common.fixtures.BoothFixtures.*;
import static com.map.nudonado.common.fixtures.MemberFixtures.*;

public class BoothTest {

    @DisplayName("부스를 생성한다.")
    @Test
    void 부스를_생성한다(){
        // given
        Member 테스트_멤버 = 테스트_멤버();

        // when & then
        Assertions.assertDoesNotThrow(() -> 테스트_부스(테스트_멤버));
    }
}
