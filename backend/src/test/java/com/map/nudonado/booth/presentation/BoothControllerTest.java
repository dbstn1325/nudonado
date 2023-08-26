package com.map.nudonado.booth.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.map.nudonado.booth.application.BoothService;
import com.map.nudonado.booth.domain.BoothDetail;
import com.map.nudonado.booth.dto.request.BoothCreateRequest;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static com.map.nudonado.common.fixtures.BoothFixtures.*;
import static com.map.nudonado.common.fixtures.MemberFixtures.테스트_멤버;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BoothControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoothService boothService;

    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    private final Member member = 테스트_멤버();

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        memberRepository.save(member);
    }

    @Test
    @DisplayName("사용자가 부스를 생성하면 상태코드 201을 반환한다")
    void 사용자가_부스를_생성하면_상태코드_201을_반환한다() throws Exception {
        // given
        Long memberId = member.getId();
        BoothCreateRequest request = new BoothCreateRequest(테스트_카테고리, 테스트_위치);

        given(boothService.save(eq(memberId), any(BoothCreateRequest.class))).willReturn(부스_생성_응답);

        // when & then
        mockMvc.perform(post("/api/booths/{memberId}", memberId)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("사용자의 위치 반경 10km 내의 부스를 조회하면 200을 반환한다")
    void 사용자의_위치_반경_10km_내의_부스를_조회하면_200을_반환한다() throws Exception {
        // given
        BoothDetail boothDetail = BoothDetail.fromEntity(테스트_부스(member));  // Create a Booth object as needed
        List<BoothDetail> expectedBoothDetails = Collections.singletonList(boothDetail);

        given(boothService.findBoothsNearLocation(테스트_위치.getLatitude(), 테스트_위치.getLongitude()))
                .willReturn(expectedBoothDetails);

        // when & then
        mockMvc.perform(get("/api/booths/nearbyBooths")
                        .param("latitude", String.valueOf(테스트_위치.getLatitude()))
                        .param("longitude", String.valueOf(테스트_위치.getLongitude())))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
