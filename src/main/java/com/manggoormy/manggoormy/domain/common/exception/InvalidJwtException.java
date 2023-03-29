package com.manggoormy.manggoormy.domain.common.exception;

public class InvalidJwtException extends MangGoormyException {

    public InvalidJwtException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
