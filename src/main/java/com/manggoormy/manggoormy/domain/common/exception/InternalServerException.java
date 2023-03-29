package com.manggoormy.manggoormy.domain.common.exception;

public class InternalServerException extends MangGoormyException {

    public InternalServerException(String message) {
        super(message, ErrorCode.E500_INTERNAL_SERVER);
    }
}
