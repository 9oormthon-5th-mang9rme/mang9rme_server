package com.manggoormy.manggoormy.domain.common.exception;

import lombok.Getter;

@Getter
public abstract class MangGoormyException extends RuntimeException{
    private final ErrorCode errorCode;

    public MangGoormyException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return errorCode.getStatus();
    }
}
