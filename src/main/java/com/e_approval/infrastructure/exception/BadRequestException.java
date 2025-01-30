package com.e_approval.infrastructure.exception;

public class BadRequestException extends BaseException {
    public BadRequestException(String msg) {
        super(msg);
    }
}
