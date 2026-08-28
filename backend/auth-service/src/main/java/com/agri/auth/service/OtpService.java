package com.agri.auth.service;

import com.agri.auth.entity.OtpVerification;
import com.agri.auth.repository.OtpVerificationRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    private final OtpVerificationRepository otpRepository;

    public OtpService(
            OtpVerificationRepository otpRepository) {

        this.otpRepository = otpRepository;
    }

    @Transactional
    public void sendOtp(String phone) {

        String otp = generateOtp();

        OtpVerification verification =
                new OtpVerification();

        verification.setPhone(phone);
        verification.setOtp(otp);

        verification.setExpiresAt(
                LocalDateTime.now().plusMinutes(5)
        );

        verification.setVerified(false);
        verification.setAttempts(0);

        otpRepository.save(verification);

        // DEVELOPMENT ONLY
        System.out.println(
                "===================================="
        );

        System.out.println(
                "OTP for " + phone + " = " + otp
        );

        System.out.println(
                "OTP expires in 5 minutes"
        );

        System.out.println(
                "===================================="
        );
    }

    @Transactional
    public boolean verifyOtp(
            String phone,
            String otp) {

        OtpVerification verification =
                otpRepository
                        .findTopByPhoneAndVerifiedFalseOrderByCreatedAtDesc(
                                phone
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "OTP not found"
                                )
                        );

        if (verification.getExpiresAt()
                .isBefore(LocalDateTime.now())) {

            throw new RuntimeException(
                    "OTP has expired"
            );
        }

        if (verification.getAttempts() >= 5) {

            throw new RuntimeException(
                    "Too many OTP attempts"
            );
        }

        verification.setAttempts(
                verification.getAttempts() + 1
        );

        if (!verification.getOtp().equals(otp)) {

            otpRepository.save(verification);

            throw new RuntimeException(
                    "Invalid OTP"
            );
        }

        verification.setVerified(true);

        otpRepository.save(verification);

        return true;
    }

    private String generateOtp() {

        return String.format(
                "%06d",
                new Random().nextInt(1_000_000)
        );
    }
}