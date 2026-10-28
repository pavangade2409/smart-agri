package com.agri.infrastructure.controller;

import com.agri.infrastructure.entity.Valve;
import com.agri.infrastructure.service.ValveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/infrastructure/valves")
public class ValveController {

    private final ValveService service;

    public ValveController(ValveService service) {
        this.service = service;
    }

    @GetMapping("/plot/{plotId}")
    public List<Valve> getByPlot(@PathVariable Long plotId) {
        return service.findByPlot(plotId);
    }

    @GetMapping("/{id}")
    public Valve getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/{id}/open")
    public Valve open(@PathVariable Long id) {
        return service.openValve(id);
    }

    @PostMapping("/{id}/close")
    public Valve close(@PathVariable Long id) {
        return service.closeValve(id);
    }
}
