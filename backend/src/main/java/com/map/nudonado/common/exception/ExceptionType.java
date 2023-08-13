package com.map.nudonado.common.exception;

import com.map.nudonado.booth.domain.exception.BoothCategoryNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum ExceptionType {

    BOOTH_CATEGORY_NOT_FOUND_EXCEPTION("3001", "존재하지 않는 카테고리입니다.", BoothCategoryNotFoundException.class),
    UNKNOWN_SERVER_EXCEPTION("0000", "알 수 없는 서버 오류가 발생했습니다.");

    private final String errorCode;
    private final String message;

    private Class<? extends ApplicationException> type;

    ExceptionType(String errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }

    public static ExceptionType from(Class<?> classType){
        return Arrays.stream(values())
                .filter(et -> Objects.nonNull(et.type) && et.type.equals(classType))
                .findFirst()
                .orElse(UNKNOWN_SERVER_EXCEPTION);
    }
}
