package com.map.nudonado.common.fixtures;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.dto.response.BoothIdResponse;
import com.map.nudonado.booth.domain.Category;
import com.map.nudonado.booth.domain.Location;
import com.map.nudonado.member.domain.Member;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class BoothFixtures {

    public static final Location 테스트_위치 = new Location(35.180563157689654, 128.09436303925034);
    public static final String 테스트_카테고리 = Category.LIFEFOURUCUT.getValue();
    public static final String 테스트_카테고리_인생네컷 = Category.LIFEFOURUCUT.getValue();
    public static final String 테스트_카테고리_셀플릭스 = Category.LIFEFOURUCUT.getValue();

    public static Point createPointFromLocation(Location location) throws ParseException {
        String pointWKT = String.format("POINT(%s %s)", location.getLatitude(), location.getLongitude());
        return (Point) new WKTReader().read(pointWKT);
    }

    public static Booth 테스트_부스(final Member creator) throws ParseException {
        return Booth.builder()
                .member(creator)
                .category(테스트_카테고리)
                .location(테스트_위치)
                .point(createPointFromLocation(테스트_위치))
                .build();
    }

    public static Booth 테스트_부스_인생네컷(final Member creator) throws ParseException {
        return Booth.builder()
                .member(creator)
                .category(테스트_카테고리_인생네컷)
                .location(테스트_위치)
                .point(createPointFromLocation(테스트_위치))
                .build();
    }

    public static Booth 테스트_부스_셀플릭스(final Member creator) throws ParseException {
        return Booth.builder()
                .member(creator)
                .category(테스트_카테고리_셀플릭스)
                .location(테스트_위치)
                .point(createPointFromLocation(테스트_위치))
                .build();
    }

    public static final BoothIdResponse 부스_생성_응답 = new BoothIdResponse(1L);
}
