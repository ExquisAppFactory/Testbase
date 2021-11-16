package com.exquis.app.user.exception;

public class HttpBadRequestException extends RuntimeException{
    public HttpBadRequestException(){
        super("bad request");
    }
    public HttpBadRequestException(String message)
    {
        super(message);
    }
}
