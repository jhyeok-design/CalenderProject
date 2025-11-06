package com.example.calenderproject.dto.response;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private final String status;
    private final String Error;
    private final String message;


    public ExceptionResponse(String status, String error, String message) {
        this.status = status;
        Error = error;
        this.message = message;
    }
}
