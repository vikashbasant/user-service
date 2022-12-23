package com.decimal.user.dto;

import lombok.*;

@Data
public class ResponseDto {
    private String status;
    private String statusCode;
    private String message;
    private Object response;
    private Object errorResponse;


    public ResponseDto(String status, String message, Object errorResponse){
        this.status = status;
        this.message = message;
        this.errorResponse = errorResponse;
    }

    public ResponseDto(String status, String response){
        this.status = status;
        this.response = response;
    }

    public ResponseDto(String status, String statusCode, String message, Object response){
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.response = response;
    }


}
