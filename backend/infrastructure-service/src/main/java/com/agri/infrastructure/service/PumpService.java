package com.agri.infrastructure.service;

import com.agri.infrastructure.entity.Pump;
import com.agri.infrastructure.enums.PumpStatus;
import com.agri.infrastructure.repository.PumpRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PumpService {

    private final PumpRepository repository;

    public PumpService(PumpRepository repository) {
        this.repository = repository;
    }

    public List<Pump> findByFarm(Long farmId) {
        return repository.findByFarmId(farmId);
    }

    public Pump findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pump not found: " + id));
    }

    public Pump startPump(Long id) {
        Pump pump = findById(id);

        if (pump.getStatus() == PumpStatus.FAULT) {
            throw new IllegalStateException(
                    "Cannot start pump because it is in FAULT state");
        }

        // Hardware integration can be added here later.
        // Example: MQTT / Modbus / PLC / IoT gateway.
        pump.setStatus(PumpStatus.ON);
        pump.setLastStartedAt(Instant.now());

        return repository.save(pump);
    }

    public Pump stopPump(Long id) {
        Pump pump = findById(id);

        // Hardware STOP command can be added here later.
        pump.setStatus(PumpStatus.OFF);
        pump.setLastStoppedAt(Instant.now());

        return repository.save(pump);
    }
}
