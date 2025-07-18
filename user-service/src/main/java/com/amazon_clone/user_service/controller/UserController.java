package com.amazon_clone.user_service.controller;

import com.amazon_clone.user_service.dto.validators.users.UserRequestDTO;
import com.amazon_clone.user_service.dto.validators.users.UserResponseDTO;
import com.amazon_clone.user_service.entity.User;
import com.amazon_clone.user_service.service.user.Impl.UserServiceImpl;
import com.amazon_clone.user_service.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //    get-/user/profileall
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(userServiceImpl.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to fetch users: " + e.getMessage());
        }
    }
//    put-/user/update
//    delete-/user/delete
//    get-user/all
//    get-/user/{id}


}
