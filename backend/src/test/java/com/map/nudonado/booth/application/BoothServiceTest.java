package com.map.nudonado.booth.application;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.BoothIdResponse;
import com.map.nudonado.booth.domain.BoothRepository;
import com.map.nudonado.booth.domain.Category;
import com.map.nudonado.booth.dto.request.BoothCreateRequest;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.map.nudonado.common.fixtures.BoothFixtures.*;
import static com.map.nudonado.common.fixtures.MemberFixtures.테스트_멤버;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
public class BoothServiceTest {

    private final Member member = 테스트_멤버();

    @Autowired
    private BoothService boothService;

    @Autowired
    private BoothRepository boothRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.save(member);
    }

    @DisplayName("새로운 부스를 생성한다")
    @Test
    void 새로운_부스를_생성한다() {
        //given
        Long memberId = 1L;
        BoothCreateRequest request = BoothCreateRequest.builder()
                .categoryType(테스트_카테고리)
                .location(테스트_위치)
                .build();
        //when
        BoothIdResponse boothIdResponse = boothService.save(memberId, request);
        Booth foundBooth = boothRepository.findById(boothIdResponse.getId()).get();

        //then
        assertAll(
                () -> assertThat(boothIdResponse).isNotNull(),
                () -> assertThat(foundBooth.getLocation()).usingRecursiveComparison().isEqualTo(테스트_위치),
                () -> assertThat(foundBooth.getCategory().getValue()).isEqualTo(테스트_카테고리)

        );
    }
}
