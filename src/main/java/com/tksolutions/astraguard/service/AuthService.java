package com.tksolutions.astraguard.service;

import com.tksolutions.astraguard.dto.LoginRequest;
import com.tksolutions.astraguard.dto.LoginResponse;
import com.tksolutions.astraguard.dto.SignUpRequest;
import com.tksolutions.astraguard.dto.UserResponse;
import com.tksolutions.astraguard.model.entity.UserEntity;
import com.tksolutions.astraguard.repository.UserRepository;
import com.tksolutions.astraguard.utils.JwtUtil;
import com.tksolutions.astraguard.utils.PasswordUtil;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // SIGNUP
    public void signup(SignUpRequest request) {

        if (userRepository.existsByUpiId(request.upiId)) {
            throw new RuntimeException("UPI ID already exists");
        }

        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID().toString());
        user.setUpiId(request.upiId);
        user.setName(request.name);
        user.setMobile(request.mobile);

        user.setPasswordHash(PasswordUtil.hash(request.password));
        user.setPinHash(PasswordUtil.hash(request.pin));

        user.setCurrentBalance(0.0);
        user.setDeviceIds(List.of(request.deviceId));

        user.setFailedPinAttempts(0);
        user.setPinLockedUntil(null);

        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        userRepository.save(user);
    }

    // LOGIN
    public LoginResponse login(LoginRequest request) {

        UserEntity user = userRepository.findByUpiId(request.upiId)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!PasswordUtil.matches(request.password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        // TEMP TOKEN (replace with JWT later)
        String jwtToken = jwtUtil.generateToken(
                user.getId(),
                user.getUpiId()
        );

        return new LoginResponse(
                jwtToken,
                new UserResponse(user.getUpiId(), user.getName())
        );
    }
}