package com.agri.auth.service.impl;

import com.agri.auth.dto.LoginRequest;
import com.agri.auth.dto.RegisterRequest;
import com.agri.auth.dto.response.LoginResponse;
import com.agri.auth.dto.response.UserResponse;
import com.agri.auth.dto.sendOtpRequest.UpdateUserRequest;
import com.agri.auth.entity.User;
import com.agri.auth.enums.Role;

import com.agri.auth.exception.ResourceAlreadyExistsException;

import com.agri.auth.jwt.JwtService;

import com.agri.auth.repository.UserRepository;

import com.agri.auth.service.AuthService;
import com.agri.auth.service.OtpService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final OtpService otpService;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            OtpService otpService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager =
                authenticationManager;
        this.jwtService = jwtService;
        this.otpService = otpService;
    }


    // =====================================================
    // REGISTER
    // =====================================================

    @Override
    public void register(
            RegisterRequest request) {

        String email =
                normalizeEmail(
                        request.email()
                );

        String phone =
                request.phone().trim();


        if (userRepository.existsByEmail(email)) {

            throw new ResourceAlreadyExistsException(
                    "Email is already registered"
            );
        }


        if (userRepository.existsByPhone(phone)) {

            throw new ResourceAlreadyExistsException(
                    "Phone number is already registered"
            );
        }


        User user = new User();

        user.setEmail(email);

        user.setPassword(
                passwordEncoder.encode(
                        request.password()
                )
        );

        user.setFirstName(
                request.firstName().trim()
        );

        user.setLastName(
                request.lastName() == null
                        ? null
                        : request.lastName().trim()
        );

        user.setPhone(phone);

        user.setRole(Role.FARMER);

        user.setEnabled(true);

        user.setAccountLocked(false);

        userRepository.save(user);
    }


    // =====================================================
    // EMAIL + PASSWORD LOGIN
    // =====================================================

    @Override
    public LoginResponse login(
            LoginRequest request) {

        String email =
                normalizeEmail(
                        request.email()
                );


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        request.password()
                )
        );


        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found"
                                )
                        );


        if (!user.isEnabled()) {

            throw new RuntimeException(
                    "User account is disabled"
            );
        }


        if (user.isAccountLocked()) {

            throw new RuntimeException(
                    "User account is locked"
            );
        }


        String token =
                jwtService.generateToken(user);


        return new LoginResponse(
                token,
                "Bearer",
                jwtService.getExpiration(),
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );
    }


    // =====================================================
    // SEND OTP
    // =====================================================

    @Override
    public void sendOtp(
            String phone) {

        // IMPORTANT:
        // Don't modify the method parameter.
        // Use a new variable because it is used
        // inside lambda expressions.

        String normalizedPhone =
                phone.trim();


        User user =
                userRepository
                        .findByPhone(normalizedPhone)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found with phone: "
                                                + normalizedPhone
                                )
                        );


        if (!user.isEnabled()) {

            throw new RuntimeException(
                    "User account is disabled"
            );
        }


        if (user.isAccountLocked()) {

            throw new RuntimeException(
                    "User account is locked"
            );
        }


        otpService.sendOtp(
                normalizedPhone
        );
    }


    // =====================================================
    // OTP LOGIN
    // =====================================================

    @Override
    public LoginResponse loginWithOtp(
            String phone,
            String otp) {

        String normalizedPhone =
                phone.trim();


        otpService.verifyOtp(
                normalizedPhone,
                otp
        );


        User user =
                userRepository
                        .findByPhone(normalizedPhone)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found with phone: "
                                                + normalizedPhone
                                )
                        );


        if (!user.isEnabled()) {

            throw new RuntimeException(
                    "User account is disabled"
            );
        }


        if (user.isAccountLocked()) {

            throw new RuntimeException(
                    "User account is locked"
            );
        }


        String token =
                jwtService.generateToken(user);


        return new LoginResponse(
                token,
                "Bearer",
                jwtService.getExpiration(),
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );
    }


    // =====================================================
    // UPDATE USER
    // =====================================================

    @Override
    public UserResponse updateUser(
            Long id,
            UpdateUserRequest request) {

        User user =
                userRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "User not found with id: "
                                                + id
                                )
                        );


        String newPhone =
                request.phone() == null
                        ? null
                        : request.phone().trim();


        // Check duplicate phone
        if (newPhone != null
                && !newPhone.equals(user.getPhone())
                && userRepository.existsByPhone(newPhone)) {

            throw new ResourceAlreadyExistsException(
                    "Phone number is already registered"
            );
        }


        // Update first name
        user.setFirstName(
                request.firstName().trim()
        );


        // Update last name
        user.setLastName(
                request.lastName() == null
                        ? null
                        : request.lastName().trim()
        );


        // Update phone
        if (newPhone != null) {

            user.setPhone(newPhone);
        }


        User savedUser =
                userRepository.save(user);


        return new UserResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getPhone(),
                savedUser.getRole(),
                savedUser.isEnabled(),
                savedUser.isAccountLocked()
        );
    }


    // =====================================================
    // NORMALIZE EMAIL
    // =====================================================

    private String normalizeEmail(
            String email) {

        return email
                .trim()
                .toLowerCase();
    }
}