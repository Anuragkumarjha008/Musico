package com.musico.user.controller;
import com.musico.security.userdetails.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<String> me() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser.getUsername());
    }

}
