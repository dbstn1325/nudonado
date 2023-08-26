package com.map.nudonado.common.exception;

import com.map.nudonado.booth.domain.exception.BoothCategoryNotFoundException;
import com.map.nudonado.booth.domain.exception.BoothNotFoundException;
import com.map.nudonado.trace.domain.exception.TraceMemoTooLongException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum ExceptionType {

    BOOTH_NOT_FOUND_EXCEPTION("3001", "존재하지 않는 부스입니다.", BoothNotFoundException.class),
    BOOTH_CATEGORY_NOT_FOUND_EXCEPTION("3002", "존재하지 않는 카테고리입니다.", BoothCategoryNotFoundException.class),

    TRACE_MEMO_TOO_LONG_EXCEPTION("4002", "흔적 메모의 길이는 255 글자를 초과할 수 없습니다.", TraceMemoTooLongException.class),


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
