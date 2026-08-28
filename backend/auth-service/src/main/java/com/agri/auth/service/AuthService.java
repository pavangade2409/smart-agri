package com.agri.auth.service;

import com.agri.auth.dto.LoginRequest;
import com.agri.auth.dto.RegisterRequest;
import com.agri.auth.dto.response.LoginResponse;
import com.agri.auth.dto.response.UserResponse;
import com.agri.auth.dto.sendOtpRequest.UpdateUserRequest;

public interface AuthService {

    void register(
            RegisterRequest request
    );

    LoginResponse login(
            LoginRequest request
    );

    void sendOtp(
            String phone
    );

    LoginResponse loginWithOtp(
            String phone,
            String otp
    );

    UserResponse updateUser(
            Long id,
            UpdateUserRequest request
    );
}