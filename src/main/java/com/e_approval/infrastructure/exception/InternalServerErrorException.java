package com.e_approval.infrastructure.exception;

public class InternalServerErrorException extends BaseException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
