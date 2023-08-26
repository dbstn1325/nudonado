package com.map.nudonado.trace.presentation;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.map.nudonado.booth.application.BoothService;
import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.BoothRepository;
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

import static com.map.nudonado.common.fixtures.BoothFixtures.*;
import static com.map.nudonado.common.fixtures.MemberFixtures.테스트_멤버;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TraceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoothService boothService;

    private ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoothRepository boothRepository;

    private final Member member = 테스트_멤버();

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        memberRepository.save(member);
    }


    @Test
    @DisplayName("멤버의 부스에 등록된 흔적을 정상적으로 조회하면 200을 반환한다")
    void 멤버의_부스에_등록된_흔적을_정상적으로_조회하면_200을_반환한다() throws Exception {
        // given
        Long memberId = member.getId();
        Long boothId = boothRepository.save(테스트_부스(member)).getId();

        given(boothService.save(eq(memberId), any(BoothCreateRequest.class))).willReturn(부스_생성_응답);

        // when & then
        mockMvc.perform(get("/api/traces/{memberId}/{bootId}", memberId, boothId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
