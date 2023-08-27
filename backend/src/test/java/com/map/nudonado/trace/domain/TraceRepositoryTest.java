package com.map.nudonado.trace.domain;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.BoothRepository;
import com.map.nudonado.common.config.JpaConfig;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import com.map.nudonado.trace.dto.response.IntegrationTrace;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


import static com.map.nudonado.common.fixtures.BoothFixtures.테스트_부스;
import static com.map.nudonado.common.fixtures.MemberFixtures.테스트_멤버;
import static com.map.nudonado.common.fixtures.TraceFixtures.테스트_메모;
import static com.map.nudonado.common.fixtures.TraceFixtures.테스트_흔적;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@Import(JpaConfig.class)
class TraceRepositoryTest {

    private final Member member = 테스트_멤버();

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoothRepository boothRepository;

    @Autowired
    private TraceRepository traceRepository;

    @BeforeEach
    void setUp() {
        memberRepository.save(member);
    }

    @DisplayName("멤버 아이디와 부스 아이디를 전달하면 멤버의 부스에 등록된 흔적을 조회한다.")
    @Test
    void 멤버_아이디와_부스_아이디를_전달하면_멤버의_부스에_등록된_흔적을_조회한다() throws ParseException {
        //given
        Booth booth = boothRepository.save(테스트_부스(member));
        traceRepository.save(테스트_흔적(member, booth));

        //when
        List<IntegrationTrace> actual = traceRepository.getByMemberAndBooth(member, booth);

        //then
        assertAll(
                () -> assertThat(actual.size()).isEqualTo(1),
                () -> assertThat(actual.get(0).getMemo()).isEqualTo(테스트_메모)
        );
    }

}