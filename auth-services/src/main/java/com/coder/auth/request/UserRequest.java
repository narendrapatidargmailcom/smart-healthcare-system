package com.coder.auth.request;

import com.coder.auth.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


public class UserRequest {

    private String username;
    private String email;
    private Set<UserRole> roles;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRequest(){}



    public UserRequest(String username, String email, Set<UserRole> roles, String password) {
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }
}

