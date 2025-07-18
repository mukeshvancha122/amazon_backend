package com.amazon_clone.user_service.service.user.Impl;

import com.amazon_clone.user_service.dto.validators.users.UserRequestDTO;
import com.amazon_clone.user_service.dto.validators.users.UserResponseDTO;
import com.amazon_clone.user_service.entity.User;
import com.amazon_clone.user_service.exceptions.UserNotFoundException;
import com.amazon_clone.user_service.mappers.UserMapper;
import com.amazon_clone.user_service.repository.UserRepository;
import com.amazon_clone.user_service.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                throw new UserNotFoundException("No users found");
            }

            return users.stream()
                    .map(UserMapper::toResponseDTO)
                    .toList();
        } catch (UserNotFoundException e) {
            // Custom exception handling (can also rethrow)
            System.out.println("Custom Exception: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            // General fallback
            System.out.println("Exception: " + e.getMessage());
            throw new RuntimeException("Failed to fetch users", e);
        }
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        try{
            User user=UserMapper.toEntity(userRequestDTO,passwordEncoder);
            User savedUser= userRepository.save(user);
            return UserMapper.toResponseDTO(savedUser);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to create user", e);
        }
    }

}
