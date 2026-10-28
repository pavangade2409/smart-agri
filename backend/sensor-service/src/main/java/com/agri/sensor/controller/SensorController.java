package com.agri.sensor.controller;

import java.util.UUID;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agri.sensor.dto.request.CreateSensorRequest;
import com.agri.sensor.dto.response.SensorResponse;
import com.agri.sensor.service.SensorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(
            SensorService sensorService) {

        this.sensorService =
                sensorService;
    }

    @PostMapping
    public ResponseEntity<SensorResponse> create(
            @Valid
            @RequestBody
            CreateSensorRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        sensorService.create(request)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorResponse> getById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                sensorService.getById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<SensorResponse>> getAll() {
        return ResponseEntity.ok(sensorService.getAll());
    }
    @GetMapping("/farm/{farmId}/zone/{zoneId}")
    public ResponseEntity<SensorResponse> getByFarmAndZone(
            @PathVariable UUID farmId,
            @PathVariable UUID zoneId) {

        return ResponseEntity.ok(
                sensorService.getSensorByFarmAndZone(
                        farmId,
                        zoneId
                )
        );
    }
}
