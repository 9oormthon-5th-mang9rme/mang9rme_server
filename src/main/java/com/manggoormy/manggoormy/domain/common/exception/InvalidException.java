package com.manggoormy.manggoormy.domain.common.exception;

public class InvalidException extends MangGoormyException {

    public InvalidException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}