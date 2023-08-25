package com.map.nudonado.booth.domain;

import lombok.Getter;
@Getter
public class BoothDetail {
    private final Long id;
    private final String category;
    private final Location location;
    private final int removeReq;
    private final String point; // I'm using String to represent the Point, adjust as necessary

    // Private constructor to ensure immutability
    private BoothDetail(Long id, String category, Location location, int removeReq, String point) {
        this.id = id;
        this.category = category;
        this.location = location;
        this.removeReq = removeReq;
        this.point = point;
    }

    // 부스 엔티티로부터 BoothDetail DTO를 생성
    public static BoothDetail fromEntity(Booth booth) {
        return new BoothDetail(
                booth.getId(),
                booth.getCategory().getValue(),
                booth.getLocation(),
                booth.getRemoveReq(),
                booth.getPoint().toString()
        );
    }

}

