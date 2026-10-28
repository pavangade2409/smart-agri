package com.agri.crop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.agri.crop.entity.Crop;

public interface CropRepository
        extends JpaRepository<Crop, UUID>,
                JpaSpecificationExecutor<Crop> {

    boolean existsByCropCode(String cropCode);
}