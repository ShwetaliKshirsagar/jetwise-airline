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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserResponse register(RegisterUser registerUser) throws UserAlreadyExistsException {
        if (userRepository.findByUserName(registerUser.getUserName()).isPresent()) {
            throw new UserAlreadyExistsException("USER.ALREADY.EXCEPTION");

        }
        UserEntity newUser = modelMapper.map(registerUser, UserEntity.class);
        newUser.setRole(Role.USER);
        newUser.setPassword(passwordEncoder.encode((CharSequence) registerUser.getPassword()));
        UserEntity savedUser = userRepository.save(newUser);
        return modelMapper.map(savedUser, UserResponse.class);
    }

    @Override
    public String login(LoginUser loginUser) throws InvalidCredentialsException {
        Optional<UserEntity> userEntity = userRepository.findByUserName(loginUser.getUserName());
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("PLEASE.REGISTER");
        } else if (!passwordEncoder.matches(loginUser.getPassword(), userEntity.get().getPassword())) {
            throw new InvalidCredentialsException("INVALID.CREDENTIALS");

        } else {
            return jwtService.generateToken(loginUser.getUserName(), String.valueOf(userEntity.get().getRole()));
        }

    }
}
