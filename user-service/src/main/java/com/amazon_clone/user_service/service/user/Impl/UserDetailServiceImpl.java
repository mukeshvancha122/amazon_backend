package com.amazon_clone.user_service.service.user.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.amazon_clone.user_service.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Load user from database using username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Return a Spring Security user object
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                Collections.emptyList() // No roles/authorities for now
        );
    }
}
