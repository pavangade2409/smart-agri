package com.agri.infrastructure.controller;

import com.agri.infrastructure.entity.WaterTank;
import com.agri.infrastructure.service.TankService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/infrastructure/tanks")
public class TankController {

    private final TankService service;

    public TankController(TankService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public WaterTank getById(@PathVariable Long id) {
        return service.findById(id);
    }
}
