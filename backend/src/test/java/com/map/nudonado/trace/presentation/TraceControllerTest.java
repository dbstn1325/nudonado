package com.map.nudonado.trace.presentation;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.map.nudonado.auth.application.AuthService;
import com.map.nudonado.auth.presentation.AuthController;
import com.map.nudonado.booth.application.BoothService;
import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.BoothRepository;
import com.map.nudonado.booth.dto.request.BoothCreateRequest;
import com.map.nudonado.booth.presentation.BoothController;
import com.map.nudonado.common.config.ExternalApiConfig;
import com.map.nudonado.member.application.MemberService;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import com.map.nudonado.member.presentation.MemberController;
import com.map.nudonado.trace.application.TraceService;
import com.map.nudonado.trace.dto.request.TraceCreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.map.nudonado.common.fixtures.AuthFixtures.토큰_정보;
import static com.map.nudonado.common.fixtures.BoothFixtures.*;
import static com.map.nudonado.common.fixtures.MemberFixtures.테스트_멤버;
import static com.map.nudonado.common.fixtures.TraceFixtures.테스트_흔적_생성_요청;
import static com.map.nudonado.common.fixtures.TraceFixtures.테스트_흔적_생성_응답;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest({
        AuthController.class,
        MemberController.class,
        BoothController.class,
        TraceController.class
})
@Import(ExternalApiConfig.class)
@ActiveProfiles("test")
public class TraceControllerTest {

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    protected AuthService authService;

    @MockBean
    private MemberService memberService;

    @MockBean
    private BoothService boothService;

    @MockBean
    protected TraceService traceService;

    @MockBean
    private BoothRepository boothRepository;

    @MockBean
    private MemberRepository memberRepository;

    private final Member member = 테스트_멤버();

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        memberRepository.save(member);
    }

    @DisplayName("흔적 정보를 등록하면 상태코드 201을 반환한다.")
    @Test
    void 흔적_정보를_등록하면_상태코드_201을_반환한다() throws Exception {
        //given
        Long boothId = 1L;
        TraceCreateRequest request = 테스트_흔적_생성_요청();

        given(traceService.save(any(), any(), any())).willReturn(테스트_흔적_생성_응답());

        //when & then
        mockMvc.perform(post("/api/{boothId}/traces", boothId)
                .header(AUTHORIZATION_HEADER_NAME, 토큰_정보)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
            ).andDo(print())
            .andExpect(status().isCreated());
    }


    @Test
    @DisplayName("부스에 등록된 멤버의 흔적을 정상적으로 조회하면 200을 반환한다")
    void 부스에_등록된_멤버의_흔적을_정상적으로_조회하면_200을_반환한다() throws Exception {
        // given
        Long boothId = 1L;
        given(traceService.save(any(), any(), any())).willReturn(테스트_흔적_생성_응답());

        // when & then
        mockMvc.perform(get("/api/{boothId}/traces", boothId)
                        .header(AUTHORIZATION_HEADER_NAME, 토큰_정보)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }


}
