package com.map.nudonado.member.domain;


import com.map.nudonado.common.config.JpaConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static com.map.nudonado.common.fixtures.MemberFixtures.테스트_멤버;
import static com.map.nudonado.common.fixtures.MemberFixtures.테스트_이메일;
import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@Import(JpaConfig.class)
public class MemberRepositoryTest{

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("중복된 이메일이 존재하는 경우 true를 반환한다")
    @Test
    void 중복된_이메일이_존재하는_경우_true를_반환한다(){
        //given
        memberRepository.save(테스트_멤버());

        // when & then
        assertThat(memberRepository.existsByEmail(테스트_이메일)).isTrue();

    }
}
