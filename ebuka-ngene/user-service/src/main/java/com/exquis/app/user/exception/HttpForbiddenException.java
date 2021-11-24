package com.exquis.app.user.exception;

public class HttpForbiddenException extends RuntimeException{

    public HttpForbiddenException(){
        super("Forbidden");
    }
    public HttpForbiddenException(String message)
    {
        super(message);
    }
}
