package com.agri.infrastructure.service;

import com.agri.infrastructure.entity.WaterTank;
import com.agri.infrastructure.repository.WaterTankRepository;
import org.springframework.stereotype.Service;

@Service
public class TankService {

    private final WaterTankRepository repository;

    public TankService(WaterTankRepository repository) {
        this.repository = repository;
    }

    public WaterTank findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Water tank not found: " + id));
    }
}
