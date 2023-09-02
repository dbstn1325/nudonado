package com.map.nudonado.common.fixtures;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.Category;
import com.map.nudonado.booth.domain.Location;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.trace.domain.Trace;
import com.map.nudonado.trace.dto.request.TraceCreateRequest;
import com.map.nudonado.trace.dto.response.TraceCreateResponse;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class TraceFixtures {

    public static final String 테스트_메모 = "테스트 메모";
    public static final String 테스트_길이_초과_메모 = "0".repeat(256);


    public static Trace 테스트_흔적(final Member creator, final Booth booth) {
        return Trace.builder()
                .member(creator)
                .booth(booth)
                .memo(테스트_메모)
                .build();
    }

    public static TraceCreateResponse 테스트_흔적_생성_응답() {
        return TraceCreateResponse
                .builder()
                .id(1L)
                .build();
    }

    public static TraceCreateRequest 테스트_흔적_생성_요청() {
        return TraceCreateRequest
                .builder()
                .memo(테스트_메모)
                .build();
    }
}
