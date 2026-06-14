package com.devforge.devforge_ai_backend.service;

import com.devforge.devforge_ai_backend.dto.LoginRequest;
import com.devforge.devforge_ai_backend.dto.RegisterRequest;
import com.devforge.devforge_ai_backend.entity.User;
import com.devforge.devforge_ai_backend.repository.UserRepository;
import com.devforge.devforge_ai_backend.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(
            RegisterRequest request
    ) {

        if (userRepository.existsByEmail(
                request.getEmail()
        )) {

            throw new RuntimeException(
                    "Email already exists"
            );
        }

        User user = new User();

        user.setName(
                request.getName()
        );

        user.setEmail(
                request.getEmail()
        );

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        userRepository.save(user);

        return jwtService.generateToken(
                user.getEmail()
        );
    }

    public String login(
            LoginRequest request
    ) {

        User user =
                userRepository
                        .findByEmail(
                                request.getEmail()
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Invalid Email or Password"
                                )
                        );

        boolean valid =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!valid) {

            throw new RuntimeException(
                    "Invalid Email or Password"
            );
        }

        return jwtService.generateToken(
                user.getEmail()
        );
    }
}