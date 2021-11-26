package com.exquis.app.paymentservice.utility;

import com.exquis.app.paymentservice.constant.Generic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.text.SimpleDateFormat;

public class Helper {
    public static final SimpleDateFormat DATE_FORMAT_DASH = new SimpleDateFormat("yyyy-MM-dd");

    public static <T> boolean isEmpty(T value) {
        if (value == null) return true;
        if (value instanceof String && ((String) value).trim().isEmpty()) return true;
        return false;
    }

    public static <T> boolean isNotEmpty(T value) {
        return !isEmpty(value);
    }

    public static HttpHeaders getHeaders(String token, Boolean isBearer, Boolean isBasic)
    {
        // set header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(Generic.USER_AGENT_NAME, Generic.USER_AGENT_VALUE);
        if(isNotEmpty(token) && isBearer.equals(Boolean.TRUE)) {
            headers.setBearerAuth(token);
        }
        else if(isNotEmpty(token) && isBasic.equals(Boolean.TRUE))
        {
            headers.setBasicAuth(token);
        }

        return headers;
    }

    public static String convertToJson(Object object) {
        Gson gson =
                new GsonBuilder()
                        .serializeNulls()
                        .setDateFormat(DATE_FORMAT_DASH.toPattern())
                        .create();

        return gson.toJson(object);
    }
}
