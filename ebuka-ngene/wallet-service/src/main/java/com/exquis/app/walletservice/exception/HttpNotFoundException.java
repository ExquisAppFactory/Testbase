package com.exquis.app.walletservice.exception;

public class HttpNotFoundException extends RuntimeException{
    public HttpNotFoundException()
    {
        super("not found");
    }
    public HttpNotFoundException(String message)
    {
        super(message);
    }
}
