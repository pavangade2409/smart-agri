package com.agri.auth.controller;

import com.agri.auth.dto.LoginRequest;
import com.agri.auth.dto.RegisterRequest;
import com.agri.auth.dto.response.LoginResponse;
import com.agri.auth.dto.response.UserResponse;
import com.agri.auth.dto.sendOtpRequest.SendOtpRequest;
import com.agri.auth.dto.sendOtpRequest.UpdateUserRequest;
import com.agri.auth.dto.sendOtpRequest.VerifyOtpRequest;
import com.agri.auth.repository.UserRepository;

import com.agri.auth.service.AuthService;
import com.agri.auth.service.OtpService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final OtpService otpService;

    private final UserRepository userRepository;


    public AuthController(
            AuthService authService,
            OtpService otpService,
            UserRepository userRepository) {

        this.authService = authService;
        this.otpService = otpService;
        this.userRepository = userRepository;
    }


    // =====================================================
    // REGISTER
    // =====================================================

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid
            @RequestBody
            RegisterRequest request) {

        authService.register(request);

        return ResponseEntity.ok(
                java.util.Map.of(
                        "success", true,
                        "message",
                        "User registered successfully"
                )
        );
    }


    // =====================================================
    // EMAIL LOGIN
    // =====================================================

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid
            @RequestBody
            LoginRequest request) {

        LoginResponse response =
                authService.login(request);

        return ResponseEntity.ok(
                java.util.Map.of(
                        "success", true,
                        "message",
                        "Login successful",
                        "data",
                        response
                )
        );
    }


    // =====================================================
    // SEND OTP
    // =====================================================

    @PostMapping("/otp/send")
    public ResponseEntity<?> sendOtp(
            @Valid
            @RequestBody
            SendOtpRequest request) {

        authService.sendOtp(
                request.phone()
        );

        return ResponseEntity.ok(
                java.util.Map.of(
                        "success", true,
                        "message",
                        "OTP sent successfully"
                )
        );
    }


    // =====================================================
    // VERIFY OTP
    // =====================================================

    @PostMapping("/otp/verify")
    public ResponseEntity<?> verifyOtp(
            @Valid
            @RequestBody
            VerifyOtpRequest request) {

        LoginResponse response =
                authService.loginWithOtp(
                        request.phone(),
                        request.otp()
                );

        return ResponseEntity.ok(
                java.util.Map.of(
                        "success", true,
                        "message",
                        "Login successful",
                        "data",
                        response
                )
        );
    }


    // =====================================================
    // GET USERS
    // DEVELOPMENT ONLY
    // =====================================================

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>>
    getUsers() {

        List<UserResponse> users =
                userRepository
                        .findAll()
                        .stream()
                        .map(user ->
                                new UserResponse(
                                        user.getId(),
                                        user.getEmail(),
                                        user.getFirstName(),
                                        user.getLastName(),
                                        user.getPhone(),
                                        user.getRole(),
                                        user.isEnabled(),
                                        user.isAccountLocked()
                                )
                        )
                        .toList();

        return ResponseEntity.ok(users);
    }


    // =====================================================
    // UPDATE USER
    // =====================================================

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse>
    updateUser(
            @PathVariable Long id,
            @Valid
            @RequestBody
            UpdateUserRequest request) {

        UserResponse response =
                authService.updateUser(
                        id,
                        request
                );

        return ResponseEntity.ok(response);
    }
}