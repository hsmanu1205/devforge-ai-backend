package com.devforge.devforge_ai_backend.controller;

import com.devforge.devforge_ai_backend.dto.AuthResponse;
import com.devforge.devforge_ai_backend.dto.LoginRequest;
import com.devforge.devforge_ai_backend.dto.RegisterRequest;
import com.devforge.devforge_ai_backend.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(
            @Valid
            @RequestBody
            RegisterRequest request
    ) {

        String token =
                authService.register(
                        request
                );

        return new AuthResponse(
                token
        );
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid
            @RequestBody
            LoginRequest request
    ) {

        String token =
                authService.login(
                        request
                );

        return new AuthResponse(
                token
        );
    }
}