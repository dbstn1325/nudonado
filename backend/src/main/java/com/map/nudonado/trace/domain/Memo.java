package com.map.nudonado.trace.domain;

import com.map.nudonado.trace.domain.exception.TraceMemoTooLongException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {

    private static final int MAX_MEMO_LENGTH = 255;

    @Column(name = "memo", nullable = false)
    private String value;

    public Memo(String value) {
        validateLength(value);
        this.value = value;
    }

    private void validateLength(final String memo) {
        if(memo.length() > MAX_MEMO_LENGTH) {
            throw new TraceMemoTooLongException();
        }
    }
}
