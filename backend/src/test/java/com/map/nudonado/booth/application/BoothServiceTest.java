package com.map.nudonado.booth.application;

import com.map.nudonado.booth.domain.*;
import com.map.nudonado.booth.dto.request.BoothCreateRequest;
import com.map.nudonado.booth.dto.response.BoothIdResponse;
import com.map.nudonado.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.map.nudonado.common.fixtures.BoothFixtures.*;
import static com.map.nudonado.common.fixtures.MemberFixtures.테스트_멤버;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
public class BoothServiceTest {

    @Autowired
    private BoothService boothService;

    @Autowired
    private BoothRepository boothRepository;

    @Autowired
    private MemberRepository memberRepository;


    @BeforeEach
    void setUp() {
        memberRepository.save(테스트_멤버());
    }

    @DisplayName("새로운 부스를 생성한다")
    @Test
    void 새로운_부스를_생성한다() throws ParseException {
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

    @DisplayName("사용자의 위치 반경 10km 내의 부스를 전체 조회한다")
    @Test
    void 사용자의_위치_반경_10km_내의_부스를_전체_조회한다() throws ParseException {
        //given
        boothService.save(1L, BoothCreateRequest.builder()
                .categoryType(테스트_카테고리)
                .location(테스트_위치)
                .build());

        //when
        List<BoothDetail> nearbyBooths = boothService.findBoothsNearLocation(테스트_위치.getLatitude(), 테스트_위치.getLongitude());

        //then
        assertAll(
                () -> assertThat(nearbyBooths).isNotNull(),
                () -> assertThat(nearbyBooths).isNotEmpty(),
                () -> assertThat(nearbyBooths.get(0).getLocation()).usingRecursiveComparison().isEqualTo(테스트_위치),
                () -> assertThat(nearbyBooths.get(0).getCategory()).isEqualTo(테스트_카테고리)
        );
    }

    @DisplayName("사용자의 위치 반경 10km 내의 부스를 카테고리별로 조회한다")
    @Test
    void 사용자의_위치_반경_10km_내의_부스를_카테고리별로_조회한다() throws ParseException {
        //given
        boothService.save(1L, BoothCreateRequest.builder()
                .categoryType(테스트_카테고리)
                .location(테스트_위치)
                .build());

        // when
        List<BoothDetail> nearbyBooths = boothService.findBoothsNearLocationByCategory(테스트_위치.getLatitude(), 테스트_위치.getLongitude(), 테스트_카테고리);

        System.out.println(nearbyBooths);
        // then
        assertAll(
                () -> assertThat(nearbyBooths).isNotNull(),
                () -> assertThat(nearbyBooths).isNotEmpty(),
                () -> assertThat(nearbyBooths.get(0).getLocation()).usingRecursiveComparison().isEqualTo(테스트_위치),
                () -> assertThat(nearbyBooths.get(0).getCategory()).isEqualTo(테스트_카테고리)
        );
    }


}
