package com.decimal.user.service;

import com.decimal.user.dto.ResponseDto;
import com.decimal.user.dto.ResponseTemplate;
import com.decimal.user.dto.UserDto;
import com.decimal.user.exception.GeneralException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseDto save(UserDto userDto) throws GeneralException;

    ResponseTemplate getUserWithDepartment(Long userId) throws GeneralException;
}
