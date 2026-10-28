package com.agri.crop.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.agri.crop.entity.CropClimateRequirement;

import java.util.Optional;
import java.util.UUID;

public interface CropClimateRequirementRepository
        extends JpaRepository<CropClimateRequirement, UUID> {

    Optional<CropClimateRequirement>
    findByCropId(UUID cropId);
}