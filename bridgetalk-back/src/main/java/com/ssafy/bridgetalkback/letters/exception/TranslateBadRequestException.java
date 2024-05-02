package com.ssafy.bridgetalkback.letters.exception;

import java.io.IOException;

public class TranslateBadRequestException extends IOException {
    private final int statusCode;

    public TranslateBadRequestException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
