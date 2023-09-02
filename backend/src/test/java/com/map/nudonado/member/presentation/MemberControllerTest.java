package com.map.nudonado.member.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.map.nudonado.auth.application.AuthService;
import com.map.nudonado.auth.presentation.AuthController;
import com.map.nudonado.common.config.ExternalApiConfig;
import com.map.nudonado.member.application.MemberService;
import com.map.nudonado.member.dto.MemberUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.map.nudonado.common.fixtures.MemberFixtures.테스트_멤버_수정_요청;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureRestDocs
@WebMvcTest({
        AuthController.class,
        MemberController.class
})
@Import(ExternalApiConfig.class)
@ActiveProfiles("test")
class MemberControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected MemberService memberService;

    @MockBean
    protected AuthService authService;


    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    private static final String AUTHORIZATION_HEADER_VALUE = "Bearer aaaaaaaa.bbbbbbbb.cccccccc";

    @DisplayName("등록된 회원이 자신의 이름을 수정한다.")
    @Test
    void 등록된_회원이_자신의_이름을_수정한다() throws Exception {
        //given
        willDoNothing()
                .given(memberService)
                .update(any(), any());
        MemberUpdateRequest 회원_수정_요청 = 테스트_멤버_수정_요청();

        //when & then
        mockMvc.perform(patch("/api/members/me")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .content(objectMapper.writeValueAsString(회원_수정_요청))
            )
            .andDo(print())
            .andExpect(status().isNoContent());
    }

}