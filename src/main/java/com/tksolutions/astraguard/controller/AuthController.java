package com.tksolutions.astraguard.controller;

import com.tksolutions.astraguard.dto.LoginRequest;
import com.tksolutions.astraguard.dto.LoginResponse;
import com.tksolutions.astraguard.dto.SignUpRequest;
import com.tksolutions.astraguard.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest request) {
        System.out.println("Request SIGNUP");
        authService.signup(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}
