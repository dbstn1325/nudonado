package com.map.nudonado.trace.domain;


import com.map.nudonado.common.exception.ExceptionType;
import com.map.nudonado.trace.domain.exception.TraceMemoNullOrEmptyException;
import com.map.nudonado.trace.domain.exception.TraceMemoTooLongException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.map.nudonado.common.fixtures.TraceFixtures.테스트_길이_초과_메모;


class MemoTest {

    @Test
    void 흔적메모가_null_이면_예외를_발생한다() {
        Assertions.assertThatThrownBy(() -> new Memo(null))
                .isExactlyInstanceOf(TraceMemoNullOrEmptyException.class)
                .hasMessage(String.valueOf(ExceptionType.TRACE_MEMO_NULL_OR_EMPTY_EXCEPTION.getMessage()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    void 흔적메모가_비어있는_경우_예외를_발생한다(String memo) {
        Assertions.assertThatThrownBy(() -> new Memo(memo))
                .isExactlyInstanceOf(TraceMemoNullOrEmptyException.class)
                .hasMessage(String.valueOf(ExceptionType.TRACE_MEMO_NULL_OR_EMPTY_EXCEPTION.getMessage()));
    }

    @Test
    void 흔적메모가_300글자를_초과하면_예외를_발생한다() {
        Assertions.assertThatThrownBy(() -> new Memo(테스트_길이_초과_메모))
                .isExactlyInstanceOf(TraceMemoTooLongException.class)
                .hasMessage(String.valueOf(ExceptionType.TRACE_MEMO_TOO_LONG_EXCEPTION.getMessage()));
    }
}