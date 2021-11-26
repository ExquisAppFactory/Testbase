package com.exquis.app.paymentservice.exception;

/**
 *
 * @author chukwuebuka
 */
public class HttpGatewayException extends RuntimeException {
    public HttpGatewayException(String message) {
        super(message);
    }

    public HttpGatewayException() {
        super("gateway exception");
    }
}
