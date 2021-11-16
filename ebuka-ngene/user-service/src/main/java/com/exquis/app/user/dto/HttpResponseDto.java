package com.exquis.app.user.dto;

import com.exquis.app.user.utility.Dto;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class HttpResponseDto implements Dto {
    private String message;
    private HttpStatus statusCode;
    private String entity;
    private Object object;
    private ResponseEntity<?> data;

    public HttpResponseDto(){}

    public HttpResponseDto(String message, HttpStatus statusCode, String entity, ResponseEntity<?> data, Object object) {
        this.message = message;
        this.statusCode = statusCode;
        this.entity = entity;
        this.data = data;
        this.object = object;
    }

    public HttpResponseDto(String message, HttpStatus statusCode, String entity, ResponseEntity<?> data) {
        this.message = message;
        this.statusCode = statusCode;
        this.entity = entity;
        this.data = data;
    }
}
