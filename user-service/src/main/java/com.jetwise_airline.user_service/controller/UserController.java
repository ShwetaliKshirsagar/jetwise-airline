package com.jetwise_airline.user_service.controller;

import com.jetwise_airline.user_service.dto.LoginUser;
import com.jetwise_airline.user_service.dto.RegisterUser;
import com.jetwise_airline.user_service.dto.UserResponse;
import com.jetwise_airline.user_service.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUser registerUser){
        UserResponse registeredUser = userService.register(registerUser);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
    @PostMapping("/login")
   public ResponseEntity<String> login(@RequestBody LoginUser loginUser) {
       String generatedToken = userService.login(loginUser);
       return new ResponseEntity<>(generatedToken, HttpStatus.OK);
   }
}
