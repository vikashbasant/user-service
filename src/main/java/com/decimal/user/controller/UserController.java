package com.decimal.user.controller;

import com.decimal.user.dto.ResponseDto;
import com.decimal.user.dto.ResponseTemplate;
import com.decimal.user.dto.UserDto;
import com.decimal.user.exception.GeneralException;
import com.decimal.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/save")
    public ResponseEntity<ResponseDto> saveUser(@RequestBody UserDto userDto) throws GeneralException {
        log.info("Inside saveUser method of UserController.");
        ResponseDto save = userService.save(userDto);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }


    @PostMapping("/getUserWithDepartment/{id}")
    public ResponseTemplate getUserWithDepartment(@PathVariable("id") Long userId) throws GeneralException {
        return userService.getUserWithDepartment(userId);
    }


}
