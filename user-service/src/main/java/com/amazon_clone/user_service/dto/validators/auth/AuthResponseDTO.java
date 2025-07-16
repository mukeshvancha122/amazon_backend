package com.amazon_clone.user_service.dto.validators.auth;

import org.antlr.v4.runtime.misc.NotNull;

public class AuthResponseDTO {

    @NotNull
    private String token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @NotNull
    private String message;


}
