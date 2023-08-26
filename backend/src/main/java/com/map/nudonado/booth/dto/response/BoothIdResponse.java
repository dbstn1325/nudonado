package com.map.nudonado.booth.dto.response;

import com.map.nudonado.booth.domain.Booth;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BoothIdResponse {

    private Long id;

    public BoothIdResponse(Booth booth) {
        this.id = booth.getId();
    }
}
