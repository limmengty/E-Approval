package com.e_approval.infrastructure.exception;

import java.nio.file.AccessDeniedException;

public class ForbiddenException extends AccessDeniedException {
    public ForbiddenException(String message) {
        super(message);
    }
}
