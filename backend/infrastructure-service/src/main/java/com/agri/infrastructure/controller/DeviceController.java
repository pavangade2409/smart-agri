package com.agri.infrastructure.controller;

import com.agri.infrastructure.entity.Device;
import com.agri.infrastructure.enums.DeviceStatus;
import com.agri.infrastructure.service.DeviceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/infrastructure/devices")
public class DeviceController {

    private final DeviceService service;

    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Device> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Device getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Device create(@RequestBody Device device) {
        return service.create(device);
    }

    @PatchMapping("/{id}/status")
    public Device updateStatus(
            @PathVariable Long id,
            @RequestParam DeviceStatus value
    ) {
        return service.updateStatus(id, value);
    }
}
