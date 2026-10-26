package com.agri.infrastructure.repository;

import com.agri.infrastructure.entity.Valve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValveRepository extends JpaRepository<Valve, Long> {
    List<Valve> findByPlotId(Long plotId);
}
