package com.agri.irrigation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agri.irrigation.dto.response.IrrigationZoneResponse;
import com.agri.irrigation.repository.IrrigationZoneRepository;

@RestController
@RequestMapping("/api/v1/irrigation/zones")
public class IrrigationZoneController {

    private final IrrigationZoneRepository zoneRepository;

    public IrrigationZoneController(
            IrrigationZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @GetMapping
    public ResponseEntity<List<IrrigationZoneResponse>> getAll() {
        List<IrrigationZoneResponse> zones = zoneRepository.findAll()
                .stream()
                .map(zone -> new IrrigationZoneResponse(
                        zone.getId(),
                        zone.getFarmId(),
                        zone.getZoneCode(),
                        zone.getName(),
                        zone.getStatus(),
                        zone.getPumpId(),
                        zone.getValveId(),
                        zone.getAreaAcres(),
                        zone.getFlowRateLpm()))
                .toList();

        return ResponseEntity.ok(zones);
    }
}
