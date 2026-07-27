package com.musico.user.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        return ResponseEntity.ok("Authenticated successfully!");
    }

}
