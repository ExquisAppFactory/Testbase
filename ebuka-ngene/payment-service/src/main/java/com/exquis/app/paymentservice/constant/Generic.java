package com.exquis.app.paymentservice.constant;

public interface Generic {
    String USER_AGENT_NAME = "user-agent";
    String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";
    String CURRENCY_CODE = "NGN";
    String TOKEN_REGEXP = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$";
    String MESSAGE_PUBLISHED = "wallet funding published";
}
