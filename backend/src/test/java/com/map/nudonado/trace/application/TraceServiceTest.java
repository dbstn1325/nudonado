package com.map.nudonado.trace.application;

import com.map.nudonado.booth.application.BoothService;
import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.BoothRepository;
import com.map.nudonado.booth.dto.request.BoothCreateRequest;
import com.map.nudonado.booth.dto.response.BoothIdResponse;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.member.domain.MemberRepository;
import com.map.nudonado.trace.domain.Trace;
import com.map.nudonado.trace.domain.TraceRepository;
import com.map.nudonado.trace.domain.exception.TraceMemoTooLongException;
import com.map.nudonado.trace.dto.request.TraceCreateRequest;
import com.map.nudonado.trace.dto.response.IntegrationTrace;
import com.map.nudonado.trace.dto.response.TraceCreateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.map.nudonado.common.fixtures.BoothFixtures.테스트_부스;
import static com.map.nudonado.common.fixtures.MemberFixtures.테스트_멤버;
import static com.map.nudonado.common.fixtures.TraceFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest()
class TraceServiceTest {
    @Autowired
    private TraceService traceService;

    @Autowired
    private BoothRepository boothRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TraceRepository traceRepository;

    Member member;
    Booth booth;

    @BeforeEach
    void setUp() throws ParseException {
        member = memberRepository.save(테스트_멤버());
        booth = boothRepository.save(테스트_부스(member));
    }


    @DisplayName("새로운 흔적을 생성한다.")
    @Test
    void 새로운_흔적을_생성한다() {
        //given
        TraceCreateRequest request = TraceCreateRequest.builder()
                .memo(테스트_메모)
                .build();

        //when
        TraceCreateResponse traceCreateResponse = traceService.save(member.getId(), booth.getId(), request);
        Trace foundTrace = traceRepository.findById(traceCreateResponse.getId()).get();

        //then
        assertAll(
                () -> assertThat(traceCreateResponse).isNotNull(),
                () -> assertThat(foundTrace.getMemo().getValue()).isEqualTo(테스트_메모)
        );
    }

    @DisplayName("새로운 흔적을 생성할 때 흔적 메모의 길이가 255를 초과할 경우 예외를 던진다.")
    @Test
    void 새로운_흔적을_생성할_때_흔적_메모의_길이가_255를_초과하는_경우_예외를_던진다() {
        //given
        TraceCreateRequest request = TraceCreateRequest.builder()
                .memo(테스트_길이_초과_메모)
                .build();

        //when & then
        assertThatThrownBy(() -> traceService.save(member.getId(), booth.getId(), request))
                .isInstanceOf((TraceMemoTooLongException.class));
    }

    @DisplayName("멤버 아이디와 부스 아이디를 전달하면 멤버의 부스에 등록된 흔적을 조회한다.")
    @Test
    void 멤버_아이디와_부스_아이디를_전달하면_멤버의_부스에_등록된_흔적을_조회한다() {
        //given
        Long memberId = member.getId();
        Long boothId = booth.getId();
        traceRepository.save(테스트_흔적(member, booth));

        //when & then
        List<IntegrationTrace> traces = traceService.findBoothTraces(memberId, boothId);
        assertAll(
                () -> assertThat(traces.size()).isEqualTo(1),
                () -> assertThat(traces.get(0).getMemo()).isEqualTo(테스트_메모)
        );
    }

}