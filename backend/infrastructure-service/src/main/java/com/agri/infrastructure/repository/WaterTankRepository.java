package com.agri.infrastructure.repository;

import com.agri.infrastructure.entity.WaterTank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaterTankRepository extends JpaRepository<WaterTank, Long> {
}
