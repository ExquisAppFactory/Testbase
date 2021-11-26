package com.exquis.app.paymentservice.exception;

/**
 *
 * @author chukwuebuka
 */

public class HttpNotFoundException extends RuntimeException {
    public HttpNotFoundException(String message) {
        super(message);
    }

    public HttpNotFoundException() {
        super("not found");
    }
}
