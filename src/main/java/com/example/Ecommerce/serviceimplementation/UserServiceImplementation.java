package com.example.Ecommerce.serviceimplementation;

import com.example.Ecommerce.exceptionHandlers.UserNotFound;
import com.example.Ecommerce.models.UserModel;
import com.example.Ecommerce.payload.JwtAuthenticationResponse;
import com.example.Ecommerce.payload.LoginDTO;
import com.example.Ecommerce.payload.UserDTO;
import com.example.Ecommerce.payload.UserResponseDTO;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.security.JwtProvider;
import com.example.Ecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public UserDTO getUserById(Long id) {
        UserModel userModel = userRepository.findById(id).orElseThrow(() -> new UserNotFound("User Not Found with ID"+id));
        return modelMapper.map(userModel,UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        UserModel userModel = modelMapper.map(userDTO,UserModel.class);
        userModel.setIsSeller(userDTO.getIsSeller());
        UserModel savedUser =  userRepository.save(userModel);
        return modelMapper.map(savedUser,UserDTO.class);
    }

    @Override
    public ResponseEntity<JwtAuthenticationResponse> loginUser(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
        );
                UserModel userModel = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(()->new UserNotFound("User Not found!"));
                UserResponseDTO user = new UserResponseDTO();
                user.setEmail(loginDTO.getEmail());
                user.setName(userModel.getName());
                user.setId(userModel.getUser_id());
                user.setIsSeller(userModel.getIsSeller());
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String token = jwtProvider.generateToken(user);


        return ResponseEntity.ok().body(new JwtAuthenticationResponse(token));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserModel> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }


}
