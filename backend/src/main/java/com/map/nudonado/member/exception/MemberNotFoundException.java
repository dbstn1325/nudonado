package com.map.nudonado.member.exception;

import com.map.nudonado.common.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {
    public MemberNotFoundException(long memberId) {
        super(String.format("(memberId: %d)", memberId));
    }
}
