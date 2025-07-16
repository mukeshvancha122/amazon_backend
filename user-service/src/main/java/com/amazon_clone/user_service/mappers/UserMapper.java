package com.amazon_clone.user_service.mappers;

import com.amazon_clone.user_service.dto.validators.users.UserRequestDTO;
import com.amazon_clone.user_service.dto.validators.users.UserResponseDTO;
import com.amazon_clone.user_service.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setPassword(userRequestDTO.getPassword());
        return user;
    }

    public static UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setFirstName(user.getFirstName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setPhoneNumber(user.getPhoneNumber());
        userResponseDTO.setRole(user.getRole()); // Assuming Role is an enum
        return userResponseDTO;
    }
}
