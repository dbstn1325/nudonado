package com.map.nudonado.common.fixtures;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.Category;
import com.map.nudonado.booth.domain.Location;
import com.map.nudonado.booth.dto.request.BoothCreateRequest;
import com.map.nudonado.member.domain.Member;

public class BoothFixtures {
    public static final Location 테스트_위치 = new Location(127.0, 37.5);
    public static final String 테스트_카테고리 = Category.LIFEFOURUCUT.getValue();

    public static Booth 테스트_부스(final Member creator){
        return new Booth(creator, 테스트_카테고리, 테스트_위치);
    }



}
