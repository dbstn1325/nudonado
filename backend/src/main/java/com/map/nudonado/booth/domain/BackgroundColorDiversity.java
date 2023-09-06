package com.map.nudonado.booth.domain;

import com.map.nudonado.booth.domain.exception.BoothBackgroundColorDiversityNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BackgroundColorDiversity {
    HIGH("상"),
    MEDIUM("중"),
    LOW("하");

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

