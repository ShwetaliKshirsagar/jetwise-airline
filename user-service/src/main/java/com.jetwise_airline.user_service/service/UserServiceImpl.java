package com.jetwise_airline.user_service.service;

import com.jetwise_airline.jwt_common.JWTService;
import com.jetwise_airline.user_service.dto.LoginUser;
import com.jetwise_airline.user_service.dto.RegisterUser;
import com.jetwise_airline.user_service.dto.UserResponse;
import com.jetwise_airline.user_service.entity.UserEntity;
import com.jetwise_airline.user_service.enums.Role;
import com.jetwise_airline.user_service.exceptions.InvalidCredentialsException;
import com.jetwise_airline.user_service.exceptions.UserAlreadyExistsException;
import com.jetwise_airline.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl() {
    }

    @Override
    public UserResponse register(RegisterUser registerUser) throws UserAlreadyExistsException {
        if (userRepository.findByUserName(registerUser.getUserName()).isPresent()) {
            throw new UserAlreadyExistsException("USER.ALREADY.EXCEPTION");
        }
        UserEntity newUser = new UserEntity();
        newUser.setUserName(registerUser.getUserName());
        newUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        newUser.setRole(Role.USER);

        UserEntity savedUser = userRepository.save(newUser);

        UserResponse response = new UserResponse();
        response.setUsername(savedUser.getUserName());
        response.setRole(savedUser.getRole());
        return response;
    }

    @Override
    public String login(LoginUser loginUser) throws InvalidCredentialsException {
        Optional<UserEntity> userEntity = userRepository.findByUserName(loginUser.getUserName());
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("PLEASE.REGISTER");
        } else if (!passwordEncoder.matches(loginUser.getPassword(), userEntity.get().getPassword())) {
            throw new InvalidCredentialsException("INVALID.CREDENTIALS");
        } else {

            String token = jwtService.generateToken(loginUser.getUserName());

            return token;

        }

    }
}
