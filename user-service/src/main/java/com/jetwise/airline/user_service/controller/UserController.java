package com.jetwise.airline.user_service.controller;

import com.jetwise.airline.user_service.dto.RegisterUser;
import com.jetwise.airline.user_service.dto.UserResponse;
import com.jetwise.airline.user_service.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUser registerUser){
        UserResponse registeredUser = userService.register(registerUser);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

}
