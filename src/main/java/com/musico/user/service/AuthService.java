package com.musico.user.service;

import com.musico.user.dto.LoginRequest;
import com.musico.user.dto.LoginResponse;
import com.musico.user.dto.RegisterRequest;
import com.musico.user.dto.UserResponse;

public interface AuthService {

    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);

}