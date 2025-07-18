package com.amazon_clone.user_service.dto.validators.auth;

import org.antlr.v4.runtime.misc.NotNull;

public class AuthResponseDTO {

    @NotNull
    private String token;
    @NotNull
    private String message;

    public AuthResponseDTO(String registrationSuccessful, String token) {
        this.message = registrationSuccessful;
        this.token = token;
    }

    public AuthResponseDTO() {
    }

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




}
