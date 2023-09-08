package com.map.nudonado.booth.domain;

import com.map.nudonado.booth.domain.exception.BoothBackgroundColorDiversityNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BackgroundColorDiversity {
    HIGH("3개 이상"),
    MEDIUM("2개"),
    LOW("1개");

    private final String level;

    BackgroundColorDiversity(String level) {
        this.level = level;
    }

    public static BackgroundColorDiversity from(String level) {
        return Arrays.stream(values())
                .filter(bcd -> bcd.level.equals(level))
                .findFirst()
                .orElseThrow(BoothBackgroundColorDiversityNotFoundException::new);
    }
}

