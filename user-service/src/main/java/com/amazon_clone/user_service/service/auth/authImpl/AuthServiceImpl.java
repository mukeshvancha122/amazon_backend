package com.amazon_clone.user_service.service.auth.authImpl;

import com.amazon_clone.user_service.dto.validators.auth.AuthRequestDTO;
import com.amazon_clone.user_service.dto.validators.auth.AuthResponseDTO;
import com.amazon_clone.user_service.entity.User;
import com.amazon_clone.user_service.exceptions.UserNotFoundException;
import com.amazon_clone.user_service.repository.UserRepository;
import com.amazon_clone.user_service.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public AuthResponseDTO login(AuthRequestDTO authRequestDTO) {
        User user=userRepository.findByUsername(authRequestDTO.getUsername()).orElseThrow(()->new UserNotFoundException("User Not found"));

        if(user.getPassword().equals(authRequestDTO.getPassword())){
            AuthResponseDTO authResponseDTO = new AuthResponseDTO();
            authResponseDTO.setMessage("Login successful");
            return authResponseDTO;
        }
        return null;
    }
}
