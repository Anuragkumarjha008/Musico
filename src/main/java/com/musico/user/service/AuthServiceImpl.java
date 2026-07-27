package com.musico.user.service;


import com.musico.common.exception.DuplicateResourceException;
import com.musico.security.jwt.JwtService;
import com.musico.user.dto.LoginRequest;
import com.musico.user.dto.LoginResponse;
import com.musico.user.dto.RegisterRequest;
import com.musico.user.dto.UserResponse;
import com.musico.user.mapper.UserMapper;
import com.musico.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.musico.security.jwt.JwtService;
import com.musico.security.userdetails.CustomUserDetails;
import com.musico.user.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Service
//@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager=authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException("Username already exists.");
        }

        String email = request.getEmail().toLowerCase().trim();

        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("Email already exists.");
        }
        User user = userMapper.toEntity(request);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }


    @Override
    public LoginResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        String accessToken = jwtService.generateAccessToken(user);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(900L)
                .build();
    }
}