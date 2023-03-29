package com.manggoormy.manggoormy.domain.common.exception;


public class UnAuthorizedException extends MangGoormyException {

    public UnAuthorizedException(String message) {
        super(message, ErrorCode.E401_UNAUTHORIZED);
    }

}
