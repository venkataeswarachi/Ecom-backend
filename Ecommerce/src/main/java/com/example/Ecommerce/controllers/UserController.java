package com.example.Ecommerce.controllers;



import com.example.Ecommerce.payload.JwtAuthenticationResponse;
import com.example.Ecommerce.payload.LoginDTO;
import com.example.Ecommerce.payload.UserDTO;
import com.example.Ecommerce.payload.UserResponseDTO;
import com.example.Ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

   @PostMapping("/register")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO)
   {
       UserDTO dto = userService.createUser(userDTO);
       return ResponseEntity.ok(dto);
   }
   @GetMapping("/getusers")
    public List<UserDTO> getUsers(){
       return userService.getAllUsers();
   }

   @GetMapping("/getuser/{id}")
    public UserDTO getUserById(@PathVariable Long id){
       return userService.getUserById(id);
   }

    @PostMapping("/login")
   public ResponseEntity<JwtAuthenticationResponse> loginUser(@RequestBody LoginDTO loginDTO){
        return userService.loginUser(loginDTO);

   }

}
