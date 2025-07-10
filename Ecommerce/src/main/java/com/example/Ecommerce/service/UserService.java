package com.example.Ecommerce.service;

import com.example.Ecommerce.models.UserModel;
import com.example.Ecommerce.payload.JwtAuthenticationResponse;
import com.example.Ecommerce.payload.LoginDTO;
import com.example.Ecommerce.payload.UserDTO;
import com.example.Ecommerce.payload.UserResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    public List<UserDTO> getAllUsers();
    public UserDTO getUserById(Long id);
    public UserDTO createUser(UserDTO userDTO);
    public ResponseEntity<JwtAuthenticationResponse> loginUser(LoginDTO loginDTO);
}
