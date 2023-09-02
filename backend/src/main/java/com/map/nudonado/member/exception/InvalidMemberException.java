package com.map.nudonado.member.exception;

import com.map.nudonado.common.exception.BadRequestException;

public class InvalidMemberException extends BadRequestException {

    public InvalidMemberException(String optionalMessage) {
        super(optionalMessage);
    }
}
