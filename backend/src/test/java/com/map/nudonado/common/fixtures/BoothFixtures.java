package com.map.nudonado.common.fixtures;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.dto.request.BoothCreateRequest;
import com.map.nudonado.booth.dto.response.BoothIdResponse;
import com.map.nudonado.booth.domain.Category;
import com.map.nudonado.booth.domain.Location;
import com.map.nudonado.member.domain.Member;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class BoothFixtures {

    public static final Location 테스트_위치 = new Location(35.180563157689654, 128.09436303925034);
    public static final String 테스트_제목 = "인생네컷";
    public static final boolean 테스트_타이머_유무 = false;
    public static final boolean 테스트_고데기_유무 = true;
    public static final String 테스트_없는_카테고리 = "dummy";
    public static final String 테스트_카테고리 = Category.LIFEFOURUCUT.getValue();
    public static final String 테스트_카테고리_인생네컷 = Category.LIFEFOURUCUT.getValue();
    public static final String 테스트_카테고리_셀플릭스 = Category.LIFEFOURUCUT.getValue();

    public static Point createPointFromLocation(Location location) throws ParseException {
        String pointWKT = String.format("POINT(%s %s)", location.getLatitude(), location.getLongitude());
        return (Point) new WKTReader().read(pointWKT);
    }

    public static Booth 테스트_부스(final Member creator){
        return Booth.builder()
                .member(creator)
                .title(테스트_제목)
                .isTimer(테스트_타이머_유무)
                .isCurlingIron(테스트_고데기_유무)
                .category(테스트_카테고리)
                .location(테스트_위치)
                .build();
    }

    public static Booth 테스트_부스_인생네컷(final Member creator){
        return Booth.builder()
                .member(creator)
                .category(테스트_카테고리_인생네컷)
                .location(테스트_위치)
                .build();
    }

    public static Booth 테스트_부스_셀플릭스(final Member creator){
        return Booth.builder()
                .member(creator)
                .category(테스트_카테고리_셀플릭스)
                .location(테스트_위치)
                .build();
    }

    public static final BoothCreateRequest 부스_생성_요청() {
        return BoothCreateRequest.builder()
                .title(테스트_제목)
                .categoryType(테스트_카테고리)
                .location(테스트_위치)
                .isTimer(테스트_타이머_유무)
                .isCurlingIron(테스트_고데기_유무)
                .build();
    }

    public static final BoothIdResponse 부스_생성_응답 = new BoothIdResponse(1L);
}
