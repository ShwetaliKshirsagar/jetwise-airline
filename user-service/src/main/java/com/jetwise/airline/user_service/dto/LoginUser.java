package com.jetwise.airline.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class LoginUser {
    @NotNull(message = "Please enter username.")
    @Email(message = "Please enter email id.")
    @NotBlank(message = "Please enter username.")
    private String userName;
    @NotNull(message = "Please enter username.")
    @NotBlank(message = "Please enter username.")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
