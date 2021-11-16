package com.exquis.app.user.exception;

public class HttpBadGatewayException extends RuntimeException{
    public HttpBadGatewayException(){
        super("Gateway Error");
    }
    public HttpBadGatewayException(String message){
        super(message);
    }
}
