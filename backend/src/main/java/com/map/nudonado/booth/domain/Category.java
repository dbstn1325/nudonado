package com.map.nudonado.booth.domain;

import com.map.nudonado.booth.domain.exception.BoothCategoryNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Category {
    LIFEFOURUCUT("lifefourcut"),
    SELFLEX("selflex"),
    PHOTOMATIC("photomatic"),
    HARUFILM("harufilm");

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
