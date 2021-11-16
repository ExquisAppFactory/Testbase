package com.exquis.app.user.exception;

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
