package com.map.nudonado.booth.domain;

import com.map.nudonado.booth.domain.exception.BoothCategoryNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Category {
    LIFEFOURUCUT("인생 네컷"),
    SELFLEX("셀플릭스"),
    PHOTOMATIC("포토 매틱"),
    HARUFILM("하루 필름");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public static Category from(String value) {
        return Arrays.stream(values())
                .filter(bt -> bt.value.equals(value))
                .findFirst()
                .orElseThrow(BoothCategoryNotFoundException::new);
    }
}
