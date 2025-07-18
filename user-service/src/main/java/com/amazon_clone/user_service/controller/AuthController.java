package com.amazon_clone.user_service.controller;

import com.amazon_clone.user_service.dto.validators.auth.AuthRequestDTO;
import com.amazon_clone.user_service.dto.validators.auth.AuthResponseDTO;
import com.amazon_clone.user_service.dto.validators.users.UserRequestDTO;
import com.amazon_clone.user_service.dto.validators.users.UserResponseDTO;
import com.amazon_clone.user_service.service.auth.authImpl.AuthServiceImpl;
import com.amazon_clone.user_service.service.user.Impl.UserServiceImpl;
import com.amazon_clone.user_service.util.Jwtutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Jwtutil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;
//    register
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private AuthServiceImpl authServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> registerUser(@Validated @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authServiceImpl.register(userRequestDTO));
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO request) {
        try {
            // Authenticate username and password
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Load user and generate token
//            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtUtil.generateToken(String.valueOf(authentication));

            // Send response
            AuthResponseDTO response = new AuthResponseDTO();
            response.setMessage("Login successful");
            response.setToken(token);
            return response;

        } catch (AuthenticationException e) {
            // Invalid credentials
            AuthResponseDTO response = new AuthResponseDTO();
            response.setMessage("Invalid username or password");
            response.setToken(null);
            return response;
        }
    }



//    logout
//    refresh token
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }

}
