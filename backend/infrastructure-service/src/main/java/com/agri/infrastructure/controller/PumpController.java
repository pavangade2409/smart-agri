package com.agri.infrastructure.controller;

import com.agri.infrastructure.entity.Pump;
import com.agri.infrastructure.service.PumpService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/infrastructure/pumps")
public class PumpController {

    private final PumpService service;

    public PumpController(PumpService service) {
        this.service = service;
    }

    @GetMapping("/farm/{farmId}")
    public List<Pump> getByFarm(@PathVariable Long farmId) {
        return service.findByFarm(farmId);
    }

    @GetMapping("/{id}")
    public Pump getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/{id}/start")
    public Pump start(@PathVariable Long id) {
        return service.startPump(id);
    }

    @PostMapping("/{id}/stop")
    public Pump stop(@PathVariable Long id) {
        return service.stopPump(id);
    }
}
