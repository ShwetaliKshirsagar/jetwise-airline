package com.jetwise_airline.user_service.dto;

import com.jetwise_airline.user_service.enums.Role;


public class UserResponse {
    private String userName;
    private Role role;

    public String getUserName() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
