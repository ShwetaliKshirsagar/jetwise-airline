package com.jetwise.airline.user_service.service;

import com.jetwise.airline.user_service.dto.LoginUser;
import com.jetwise.airline.user_service.dto.RegisterUser;
import com.jetwise.airline.user_service.dto.UserResponse;
import com.jetwise.airline.user_service.entity.UserEntity;
import com.jetwise.airline.user_service.enums.Role;
import com.jetwise.airline.user_service.exceptions.InvalidCredentialsException;
import com.jetwise.airline.user_service.exceptions.UserAlreadyExistsException;
import com.jetwise.airline.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserResponse register(RegisterUser registerUser) throws UserAlreadyExistsException {
        if (userRepository.findByUserName(registerUser.getUserName()).isPresent()) {
            throw new UserAlreadyExistsException("USER.ALREADY.EXCEPTION");
        }
        UserEntity newUser = new UserEntity();
        newUser.setUserName(registerUser.getUserName());
        newUser.setPassword(registerUser.getPassword());
        newUser.setRole(Role.ROLE_USER);

        UserEntity savedUser = userRepository.save(newUser);

        UserResponse response = new UserResponse();
        response.setUsername(savedUser.getUserName());
        response.setRole(savedUser.getRole());
        return response;
    }

    @Override
    public UserResponse login(LoginUser loginUser) throws InvalidCredentialsException {
        Optional<UserEntity> userEntity = userRepository.findByUserName(loginUser.getUserName());
        if (userEntity.isEmpty()) {
            throw new InvalidCredentialsException("INVALID.CREDENTIALS");
        } else if (!loginUser.getPassword().equals(userEntity.get().getPassword())) {
            throw new InvalidCredentialsException("INVALID.CREDENTIALS");
        } else {
            UserEntity user = userEntity.get();
            UserResponse userResponse = new UserResponse();
            userResponse.setUsername(user.getUserName());
            userResponse.setRole(user.getRole());
            return userResponse;

        }

    }
}
