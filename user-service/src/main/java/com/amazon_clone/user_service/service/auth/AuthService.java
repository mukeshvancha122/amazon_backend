package com.amazon_clone.user_service.service.auth;

import com.amazon_clone.user_service.dto.validators.auth.AuthRequestDTO;
import com.amazon_clone.user_service.dto.validators.auth.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO login(AuthRequestDTO authRequestDTO);
}
