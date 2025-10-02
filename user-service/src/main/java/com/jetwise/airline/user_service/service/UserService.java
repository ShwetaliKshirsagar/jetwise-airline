package com.jetwise.airline.user_service.service;

import com.jetwise.airline.user_service.controller.UserController;
import com.jetwise.airline.user_service.dto.RegisterUser;
import com.jetwise.airline.user_service.dto.UserResponse;
import com.jetwise.airline.user_service.exceptions.UserAlreadyExistsException;
import com.jetwise.airline.user_service.repository.UserRepository;

public interface UserService {
    UserResponse register(RegisterUser registerUser) throws UserAlreadyExistsException;
}
