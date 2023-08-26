package com.map.nudonado.trace.dto.request;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.member.domain.Member;
import com.map.nudonado.trace.domain.Trace;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TraceCreateRequest {

    @NotBlank(message = "내용이 공백일 수 없습니다.")
    private String memo;

    @Builder
    public TraceCreateRequest(String memo) {
        this.memo = memo;
    }

    public Trace toTrace(Member member, Booth booth) {
        return Trace.builder()
                .member(member)
                .booth(booth)
                .memo(memo)
                .build();
    }
}
