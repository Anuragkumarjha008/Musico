package com.musico.user.service;

import com.musico.user.dto.*;

public interface AuthService {

    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
    LoginResponse refreshToken(RefreshTokenRequest request);

}