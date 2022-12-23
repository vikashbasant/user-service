package com.decimal.user.service;


import com.decimal.user.dto.ResponseDto;
import com.decimal.user.dto.ResponseTemplate;
import com.decimal.user.dto.UserDto;
import com.decimal.user.entity.User;
import com.decimal.user.exception.GeneralException;
import com.decimal.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseDto save(UserDto userDto) throws GeneralException {
        try{
            log.info("Inside saveUser of UserServiceImp");
            User user = new User();
            user.setUserId(userDto.getUserId());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setDepartmentId(userDto.getDepartmentId ());
            userRepository.save(user);
            return new ResponseDto("OK", "200", "user information is save in the database", user);

        }catch (Exception excp){
            throw new GeneralException("500", "user information is not save in database", "");
        }
    }

    @Override
    public ResponseTemplate getUserWithDepartment(Long userId) throws GeneralException {
        log.info("Inside getUserWithDepartment of UserController");
        ResponseTemplate responseTemplate = new ResponseTemplate();

        Optional<User> byUserId = userRepository.findById (userId);

        if(!byUserId.isPresent()){
            throw new GeneralException("500","User Id doesn't exist","");
        }

        UserDto userDto = new UserDto();
        userDto.setUserId(byUserId.get ().getUserId ());
        userDto.setFirstName(byUserId.get().getFirstName());
        userDto.setLastName(byUserId.get().getLastName());
        userDto.setEmail(byUserId.get().getEmail());
        userDto.setDepartmentId(byUserId.get().getDepartmentId());
        ResponseDto responseDto = new ResponseDto("SUCCESS", "200", "user_details_fetch_successfully", userDto);
        ResponseEntity<Object> userExchange = new ResponseEntity<>(responseDto, HttpStatus.OK);
        responseTemplate.setUser(userExchange.getBody());

        

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Long departmentId = userDto.getDepartmentId();
        ResponseEntity<Object> exchange = restTemplate.exchange("http://DEPARTMENT-SERVICE/department/findByDepartmentById/"+ userDto.getDepartmentId(), HttpMethod.POST, null, Object.class);
        responseTemplate.setDepartmentDto(exchange.getBody());


        return responseTemplate;
    }
}
