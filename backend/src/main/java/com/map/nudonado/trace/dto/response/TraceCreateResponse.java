package com.map.nudonado.trace.dto.response;


import com.map.nudonado.trace.domain.Trace;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TraceCreateResponse {

    private Long id;

    @Builder
    public TraceCreateResponse(Long id) {
        this.id = id;
    }
}
