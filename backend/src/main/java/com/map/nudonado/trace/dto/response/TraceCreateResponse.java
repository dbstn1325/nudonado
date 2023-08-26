package com.map.nudonado.trace.dto.response;


import com.map.nudonado.trace.domain.Trace;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class TraceCreateResponse {

    private Long id;

    public TraceCreateResponse(Trace trace) {
        this.id = trace.getId();
    }
}
