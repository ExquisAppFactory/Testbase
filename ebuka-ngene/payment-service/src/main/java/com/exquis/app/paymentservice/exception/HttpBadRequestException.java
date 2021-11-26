package com.exquis.app.paymentservice.exception;

/**
 *
 * @author chukwuebuka
 */
public class HttpBadRequestException extends RuntimeException {
    public HttpBadRequestException(String message) {
        super(message);
    }

    public HttpBadRequestException() {
        super("bad request");
    }
}
