package com.jetwise.airline.user_service.controller;

import com.jetwise.airline.user_service.dto.LoginUser;
import com.jetwise.airline.user_service.dto.RegisterUser;
import com.jetwise.airline.user_service.dto.UserResponse;
import com.jetwise.airline.user_service.exceptions.InvalidCredentialsException;
import com.jetwise.airline.user_service.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/login")
   public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginUser loginUser) {
       UserResponse login = userService.login(loginUser);
       return new ResponseEntity<>(login, HttpStatus.OK);
   }

}
