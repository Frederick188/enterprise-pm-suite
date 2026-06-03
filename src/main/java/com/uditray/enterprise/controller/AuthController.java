package com.uditray.enterprise.controller;

import com.uditray.enterprise.dto.*;
import com.uditray.enterprise.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @Valid @RequestBody RegisterRequest request
    ){

        String token = authService.register(request);

        return new AuthResponse(token);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request
    ) {

        String token = authService.login(request);

        return new AuthResponse(token);
    }
}