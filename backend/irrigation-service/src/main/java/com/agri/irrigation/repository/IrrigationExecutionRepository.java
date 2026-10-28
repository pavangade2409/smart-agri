package com.agri.irrigation.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agri.irrigation.entity.IrrigationExecution;

public interface IrrigationExecutionRepository
        extends JpaRepository<IrrigationExecution, UUID> {

    boolean existsByIdempotencyKey(
            String idempotencyKey
    );

    Optional<IrrigationExecution> findByIdempotencyKey(
            String idempotencyKey
    );
}