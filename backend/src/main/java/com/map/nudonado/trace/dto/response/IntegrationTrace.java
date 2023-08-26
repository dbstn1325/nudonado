package com.map.nudonado.trace.dto.response;


import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.Category;
import com.map.nudonado.trace.domain.Memo;
import com.map.nudonado.trace.domain.Trace;
import lombok.Getter;


@Getter
public class IntegrationTrace {

    private final Long id;
    private final Long boothId;
    private final String category;
    private final String memo;

    public IntegrationTrace(final Trace trace) {
        this.id = trace.getId();
        Booth booth = trace.getBooth();
        this.boothId = booth.getId();
        this.category = booth.getCategory().getValue();
        this.memo = trace.getMemo().getValue();
    }
}
