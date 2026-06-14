package com.devforge.devforge_ai_backend.controller;

import com.devforge.devforge_ai_backend.dto.UserDTO;
import com.devforge.devforge_ai_backend.entity.User;
import com.devforge.devforge_ai_backend.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserRepository userRepository;

    public UserController(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }


    @GetMapping("/test")
    public String test() {

        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        System.out.println(
                "USER TEST AUTH = "
                        + auth
        );

        return "WORKING";
    }

    @GetMapping("/me")
    public UserDTO getCurrentUser() {

        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        System.out.println(
                "USER ME AUTH = "
                        + auth
        );

        String email =
                auth.getName();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "User not found"
                                )
                        );

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}