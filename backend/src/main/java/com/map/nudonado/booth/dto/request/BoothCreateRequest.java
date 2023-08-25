package com.map.nudonado.booth.dto.request;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.Location;
import com.map.nudonado.member.domain.Member;
import lombok.*;
import org.locationtech.jts.geom.Point;


import javax.validation.constraints.NotNull;



@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BoothCreateRequest {

    @NotNull(message = "카테고리를 입력해주세요.")
    private String categoryType;

    @NotNull(message = "위치 정보를 입력해주세요.")
    private Location location;


    public Booth toBooth(Member member, Point point) {
        return Booth.builder()
                .member(member)
                .category(categoryType)
                .location(this.location)
                .point(point)
                .build();
    }

    @Builder
    public BoothCreateRequest(String categoryType, Location location) {
        this.categoryType = categoryType;
        this.location = location;
    }
}
