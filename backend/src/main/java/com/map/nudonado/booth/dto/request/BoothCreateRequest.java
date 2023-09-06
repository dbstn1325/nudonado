package com.map.nudonado.booth.dto.request;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.Location;
import com.map.nudonado.member.domain.Member;
import lombok.*;
import org.locationtech.jts.geom.Point;


import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BoothCreateRequest {

    @NotNull(message = "부스 제목을 한 글자 이상 입력해주세요.")
    private String title;

    @NotNull(message = "타이머 유무를 선택해주세요.")
    private Boolean isTimer;

    @NotNull(message = "고데기 유무를 선택해주세요.")
    private Boolean isCurlingIron;

    @NotNull(message = "카테고리를 입력해주세요.")
    private String backgroundColorDiversity;

    @NotNull(message = "카테고리를 입력해주세요.")
    private String categoryType;

    @NotNull(message = "위치 정보를 입력해주세요.")
    private Location location;


    public Booth toBooth(Member member, Point point) {
        return Booth.builder()
                .member(member)
                .title(title)
                .isTimer(isTimer)
                .isCurlingIron(isCurlingIron)
                .backgroundColorDiversity(backgroundColorDiversity)
                .category(categoryType)
                .location(location)
                .point(point)
                .build();
    }

    @Builder
    public BoothCreateRequest(String title, boolean isTimer, boolean isCurlingIron, String backgroundColorDiversity, String categoryType, Location location) {
        this.title = title;
        this.isTimer = isTimer;
        this.isCurlingIron = isCurlingIron;
        this.backgroundColorDiversity = backgroundColorDiversity;
        this.categoryType = categoryType;
        this.location = location;
    }
}
