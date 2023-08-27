package com.map.nudonado.booth.domain;


import com.map.nudonado.common.config.JpaConfig;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static com.map.nudonado.common.fixtures.BoothFixtures.*;
import static com.map.nudonado.common.fixtures.MemberFixtures.테스트_멤버;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaConfig.class)
class BoothRepositoryTest {
    @Autowired
    private BoothRepository boothRepository;

    @Autowired
    private MemberRepository memberRepository;


    @DisplayName("부스를 생성한다")
    @Test
    void 부스를_생성한다() throws ParseException {
        Member 테스트_멤버 = memberRepository.save(테스트_멤버());
        Booth 테스트_부스 = 테스트_부스(테스트_멤버);
        Booth savedBooth = boothRepository.save(테스트_부스);

        assertThat(savedBooth).isSameAs(테스트_부스);
    }

    @ParameterizedTest
    @CsvSource({"all, 2", "lifefourcut, 1", "selflex, 1", "photomatic, 0", "harufilm, 0"})
    void 카테고리별로_부스를_조회한다(String category, int expectedSize) throws ParseException {
//        boothRepository.save(테스트_부스_인생네컷(member));
//        boothRepository.save(테스트_부스_셀플릭스(member));

    }

}
