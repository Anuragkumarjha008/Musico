package com.musico.user.mapper;

import com.musico.user.dto.RegisterRequest;
import com.musico.user.dto.UserResponse;
import com.musico.user.entity.User;
import com.musico.user.entity.UserRole;
import com.musico.user.entity.UserStatus;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setDisplayName(request.getDisplayName());
        user.setEmail(request.getEmail());

        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);

        return user;
    }

    public UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();
    }
}