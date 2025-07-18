package com.amazon_clone.user_service.service.auth.authImpl;

import com.amazon_clone.user_service.dto.validators.auth.AuthRequestDTO;
import com.amazon_clone.user_service.dto.validators.auth.AuthResponseDTO;
import com.amazon_clone.user_service.dto.validators.users.UserRequestDTO;
import com.amazon_clone.user_service.entity.User;
import com.amazon_clone.user_service.exceptions.UserNotFoundException;
import com.amazon_clone.user_service.repository.UserRepository;
import com.amazon_clone.user_service.service.auth.AuthService;
import com.amazon_clone.user_service.service.user.Impl.UserServiceImpl;
import com.amazon_clone.user_service.util.Jwtutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Jwtutil jwtUtil;
    @Autowired
    private UserServiceImpl userServiceImpl;
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
    public AuthResponseDTO register(UserRequestDTO dto) {
        userServiceImpl.createUser(dto);
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
        );
        String token = jwtUtil.generateToken(auth.getName());
        System.out.println("Name:" + auth.getName());
        return new AuthResponseDTO("Registration successful", token);
    }

}
