package com.agri.infrastructure.service;

import com.agri.infrastructure.entity.Valve;
import com.agri.infrastructure.repository.ValveRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ValveService {

    private final ValveRepository repository;

    public ValveService(ValveRepository repository) {
        this.repository = repository;
    }

    public List<Valve> findByPlot(Long plotId) {
        return repository.findByPlotId(plotId);
    }

    public Valve findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Valve not found: " + id));
    }

    public Valve openValve(Long id) {
        Valve valve = findById(id);
        valve.setOpen(true);
        valve.setLastChangedAt(Instant.now());
        return repository.save(valve);
    }

    public Valve closeValve(Long id) {
        Valve valve = findById(id);
        valve.setOpen(false);
        valve.setLastChangedAt(Instant.now());
        return repository.save(valve);
    }
}
