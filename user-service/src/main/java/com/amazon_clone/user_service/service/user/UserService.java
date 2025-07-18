package com.amazon_clone.user_service.service.user;

import com.amazon_clone.user_service.dto.validators.users.UserRequestDTO;
import com.amazon_clone.user_service.dto.validators.users.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    public List<UserResponseDTO> getAllUsers();
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    public UserResponseDTO updateUser(UserRequestDTO userRequestDTO, UUID id);
    public UserResponseDTO getUserById(UUID id);
    public void deleteUser(UUID id);
}
