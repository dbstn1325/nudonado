package com.map.nudonado.common.fixtures;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.Category;
import com.map.nudonado.booth.domain.Location;
import com.map.nudonado.member.domain.Member;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

public class TraceFixtures {

    public static final String 테스트_메모 = "테스트 메모";
    public static final String 테스트_길이_초과_메모 = "0".repeat(256);
}
