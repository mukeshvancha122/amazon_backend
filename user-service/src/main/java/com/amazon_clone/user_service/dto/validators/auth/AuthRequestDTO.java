package com.amazon_clone.user_service.dto.validators.auth;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

public class AuthRequestDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
