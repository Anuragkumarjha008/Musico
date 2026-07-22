package com.musico.user.controller;

import com.musico.common.response.ApiResponse;
import com.musico.user.dto.RegisterRequest;
import com.musico.user.dto.UserResponse;
import com.musico.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {

        UserResponse response = userService.register(request);

        return ApiResponse.success(
                response, "User registered successfully"
        );
    }
}