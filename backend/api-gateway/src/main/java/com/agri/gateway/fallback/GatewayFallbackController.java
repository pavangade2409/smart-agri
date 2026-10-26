package com.agri.gateway.fallback;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fallback")
public class GatewayFallbackController {

    @GetMapping("/crop")
    public ResponseEntity<Map<String, Object>>
    cropFallback() {

        return buildResponse(
                "CROP_SERVICE_UNAVAILABLE"
        );
    }

    @GetMapping("/farm")
    public ResponseEntity<Map<String, Object>>
    farmFallback() {

        return buildResponse(
                "FARM_SERVICE_UNAVAILABLE"
        );
    }

    @GetMapping("/sensor")
    public ResponseEntity<Map<String, Object>>
    sensorFallback() {

        return buildResponse(
                "SENSOR_SERVICE_UNAVAILABLE"
        );
    }

    @GetMapping("/weather")
    public ResponseEntity<Map<String, Object>>
    weatherFallback() {

        return buildResponse(
                "WEATHER_SERVICE_UNAVAILABLE"
        );
    }

    @GetMapping("/irrigation")
    public ResponseEntity<Map<String, Object>>
    irrigationFallback() {

        return buildResponse(
                "IRRIGATION_SERVICE_UNAVAILABLE"
        );
    }

    private ResponseEntity<Map<String, Object>>
    buildResponse(String code) {

        Map<String, Object> response =
                new LinkedHashMap<>();

        response.put(
                "timestamp",
                OffsetDateTime.now()
        );

        response.put(
                "status",
                HttpStatus.SERVICE_UNAVAILABLE.value()
        );

        response.put(
                "code",
                code
        );

        response.put(
                "message",
                "Requested service is temporarily unavailable"
        );

        return ResponseEntity
                .status(
                        HttpStatus.SERVICE_UNAVAILABLE
                )
                .body(response);
    }
}