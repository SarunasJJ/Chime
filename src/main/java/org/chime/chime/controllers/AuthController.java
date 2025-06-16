package org.chime.chime.controllers;

import org.chime.chime.dto.request.LoginRequest;
import org.chime.chime.dto.request.SignUpRequest;
import org.chime.chime.dto.response.AuthResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public AuthResponse login(LoginRequest loginRequest) {
        return new AuthResponse();
    }

    @PostMapping("/signup")
    public AuthResponse signup(SignUpRequest signUpRequest) {
        return new AuthResponse();
    }
}
