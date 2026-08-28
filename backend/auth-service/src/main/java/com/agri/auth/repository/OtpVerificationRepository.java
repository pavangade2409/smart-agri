package com.agri.auth.repository;

import com.agri.auth.entity.OtpVerification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpVerificationRepository
        extends JpaRepository<OtpVerification, Long> {

    Optional<OtpVerification>
    findTopByPhoneAndVerifiedFalseOrderByCreatedAtDesc(
            String phone
    );
}
