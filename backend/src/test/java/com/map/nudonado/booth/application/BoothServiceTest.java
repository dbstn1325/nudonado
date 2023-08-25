package com.map.nudonado.booth.application;

import com.map.nudonado.booth.domain.*;
import com.map.nudonado.booth.dto.request.BoothCreateRequest;
import com.map.nudonado.common.fixtures.BoothFixtures;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import org.assertj.core.api.Assertions;
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
import static org.assertj.core.api.Assertions.*;
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

    @DisplayName("사용자의 위치 반경 10m 내의 부스를 조회한다")
    @Test
    void 사용자의_위치_반경_10m_내의_부스를_조회한다() throws ParseException {
        //given
        Double userX = 테스트_위치.getLatitude();
        Double userY = 테스트_위치.getLongitude();

        boothService.save(1L, BoothCreateRequest.builder()
                .categoryType(테스트_카테고리)
                .location(테스트_위치)
                .build());

        //when
        List<BoothDetail> nearbyBooths = boothService.findBoothsNearLocation(userX, userY);

        //then
        assertAll(
                () -> assertThat(nearbyBooths).isNotNull(),
                () -> assertThat(nearbyBooths).isNotEmpty(),
                // Verify that the found booth matches our test data
                () -> assertThat(nearbyBooths.get(0).getLocation()).usingRecursiveComparison().isEqualTo(테스트_위치),
                () -> assertThat(nearbyBooths.get(0).getCategory()).isEqualTo(테스트_카테고리)
        );
    }


    @Test
    void testFindBoothsNearLocation() {
        // Given: a location and some booths within the bounds
        Double x = 40.0;  // latitude
        Double y = -73.0; // longitude
        Location location = new Location(x, y);

        Booth booth1 = Booth.builder()
                .member(null) // add actual Member object or make it nullable
                .category(테스트_카테고리)
                .location(location)
                .point(null) // use appropriate Point object
                .build();

        Booth booth2 = Booth.builder()
                .member(null) // add actual Member object or make it nullable
                .category(테스트_카테고리)
                .location(location)
                .point(null) // use appropriate Point object
                .build();

        // Assuming a repository or a method that returns a list of booths for testing.
        // For a real test, you'd have to set up a database and repository.
        List<Booth> booths = List.of(booth1, booth2);

        // When: we try to find booths near the given location
        List<BoothDetail> boothDetails = boothService.findBoothsNearLocation(x, y);

        // Then: we should get the booths we set up in the given location
        assertEquals(2, boothDetails.size());
        assertEquals(booth1.getId(), boothDetails.get(0).getId());
        assertEquals(booth2.getId(), boothDetails.get(1).getId());
    }
}
