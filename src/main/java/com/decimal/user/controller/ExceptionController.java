package com.decimal.user.controller;


import com.decimal.user.dto.ResponseDto;
import com.decimal.user.exception.GeneralException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log
public class ExceptionController {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseDto> runtimeException(RuntimeException ex){
        String message = ex.getMessage();

        ResponseDto responseDTO = new ResponseDto("TEST_500", message);
        return new ResponseEntity<ResponseDto>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<ResponseDto> generalException(GeneralException gExcp){
        String message = gExcp.getMessage();
        String code = gExcp.getStatusCode();
        Object errorMessage = gExcp.getErrorMessages();

        ResponseDto responseDTO = new ResponseDto("FAILURE", code, message, errorMessage);
        return new ResponseEntity<ResponseDto>(responseDTO, HttpStatus.BAD_REQUEST);
    }

}
