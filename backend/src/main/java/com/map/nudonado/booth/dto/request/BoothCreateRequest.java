package com.map.nudonado.booth.dto.request;

import com.map.nudonado.booth.domain.Booth;
import com.map.nudonado.booth.domain.Category;
import com.map.nudonado.booth.domain.Location;
import com.map.nudonado.member.domain.Member;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class BoothCreateRequest {

    @NotBlank(message = "부스 명을 입력해주세요.")
    private String name;

    @NotBlank(message = "카테고리를 입력해주세요.")
    private String categoryType;

    @NotBlank(message = "위치 정보를 입력해주세요.")
    private Location location;


    public Booth toBooth(Member member) {
        return Booth.builder()
                .member(member)
                .category(Category.from(categoryType))
                .location(this.location)
                .build();
    }

}
