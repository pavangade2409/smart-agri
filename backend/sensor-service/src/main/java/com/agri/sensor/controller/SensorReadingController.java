package com.agri.sensor.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agri.sensor.dto.request.SensorReadingRequest;
import com.agri.sensor.dto.response.SensorReadingResponse;
import com.agri.sensor.service.SensorReadingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/sensor-readings")
public class SensorReadingController {

    private final SensorReadingService readingService;

    public SensorReadingController(
            SensorReadingService readingService) {

        this.readingService =
                readingService;
    }

    @PostMapping
    public ResponseEntity<SensorReadingResponse> record(
            @Valid
            @RequestBody
            SensorReadingRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        readingService.record(
                                request
                        )
                );
    }

    @GetMapping("/sensor/{sensorId}/latest")
    public ResponseEntity<SensorReadingResponse>
    getLatest(
            @PathVariable UUID sensorId) {

        return ResponseEntity.ok(
                readingService.getLatest(
                        sensorId
                )
        );
    }
}