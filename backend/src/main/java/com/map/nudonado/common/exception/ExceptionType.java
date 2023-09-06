package com.map.nudonado.common.exception;

import com.map.nudonado.auth.domain.exception.EmptyAuthorizationHeaderException;
import com.map.nudonado.auth.domain.exception.InvalidTokenException;
import com.map.nudonado.auth.domain.exception.NotCorrespondTokenException;
import com.map.nudonado.auth.domain.exception.WrongFormatTokenException;
import com.map.nudonado.booth.domain.exception.BoothBackgroundColorDiversityNotFoundException;
import com.map.nudonado.booth.domain.exception.BoothCategoryNotFoundException;
import com.map.nudonado.booth.domain.exception.BoothNotFoundException;
import com.map.nudonado.member.exception.MemberNotFoundException;
import com.map.nudonado.trace.domain.exception.TraceMemoNullOrEmptyException;
import com.map.nudonado.trace.domain.exception.TraceMemoTooLongException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum ExceptionType {

    INVALID_TOKEN_EXCEPTION("1001", "유효하지 않은 토큰입니다.", InvalidTokenException.class),
    NOT_FOUND_OAUTH_TOKEN_EXCEPTION("1002", "존재하지 않는 토큰 입니다.", NotFoundException.class),
    NOT_MEMBER_REFRESH_TOKEN_EXCEPTION("1003", "회원의 리프레시 토큰이 아닙니다."),
    NOT_CORRESPOND_TOKEN_EXCEPTION("1004", "일치하는 토큰이 존재하지 않습니다.", NotCorrespondTokenException.class),
    EMPTY_AUTHORIZATION_HEADER_EXCEPTION("1005", "헤더에 Authorization이 존재하지 않습니다.", EmptyAuthorizationHeaderException.class),
    WRONG_FORMAT_TOKEN_EXCEPTION("1006", "token 형식이 잘못 되었습니다.", WrongFormatTokenException.class),

    NOT_FOUND_MEMBER_EXCEPTION("2001", "존재하지 않는 회원입니다.", MemberNotFoundException.class),
    INVALID_MEMBER_EXCEPTION("2002", "잘못된 회원 정보입니다.", InvalidTokenException.class),


    BOOTH_NOT_FOUND_EXCEPTION("3001", "존재하지 않는 부스입니다.", BoothNotFoundException.class),
    BOOTH_CATEGORY_NOT_FOUND_EXCEPTION("3002", "존재하지 않는 카테고리입니다.", BoothCategoryNotFoundException.class),
    BOOTH_BACKGROUND_COLOR_DIVERSITY_NOT_FOUND_EXCEPTION("3003", "존재하지 않는 배경색 다양 유무입니다.", BoothBackgroundColorDiversityNotFoundException.class),

    TRACE_MEMO_NULL_OR_EMPTY_EXCEPTION("4001", "흔적 메모리 길이를 한 글자 이상 입력해야합니다.", TraceMemoNullOrEmptyException.class),
    TRACE_MEMO_TOO_LONG_EXCEPTION("4002", "흔적 메모의 길이는 255 글자를 초과할 수 없습니다.", TraceMemoTooLongException.class),


    UNKNOWN_SERVER_EXCEPTION("0000", "알 수 없는 서버 오류가 발생했습니다."),
    ;

    private final String errorCode;
    private final String message;

    private Class<? extends ApplicationException> type;

    ExceptionType(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static ExceptionType from(Class<?> classType) {
        return Arrays.stream(values())
                .filter(et -> Objects.nonNull(et.type) && et.type.equals(classType))
                .findFirst()
                .orElse(UNKNOWN_SERVER_EXCEPTION);
    }
}
