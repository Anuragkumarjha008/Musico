package com.musico.user.service;

import com.musico.user.dto.LoginRequest;
import com.musico.user.dto.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}