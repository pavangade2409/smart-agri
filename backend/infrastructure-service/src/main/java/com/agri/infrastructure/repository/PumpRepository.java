package com.agri.infrastructure.repository;

import com.agri.infrastructure.entity.Pump;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PumpRepository extends JpaRepository<Pump, Long> {
    List<Pump> findByFarmId(Long farmId);
}
