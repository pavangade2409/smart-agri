package com.agri.weather.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WeatherExceptionHandler {

    @ExceptionHandler(WeatherException.class)
    public ResponseEntity<Map<String, Object>>
    handleWeatherException(
            WeatherException ex) {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "timestamp",
                Instant.now()
        );

        response.put(
                "status",
                HttpStatus.BAD_GATEWAY.value()
        );

        response.put(
                "code",
                "WEATHER_PROVIDER_ERROR"
        );

        response.put(
                "message",
                ex.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>>
    handleGenericException(Exception ex) {

        Map<String, Object> response =
                new HashMap<>();

        response.put(
                "timestamp",
                Instant.now()
        );

        response.put(
                "status",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        response.put(
                "code",
                "INTERNAL_SERVER_ERROR"
        );

        response.put(
                "message",
                "Unexpected error occurred"
        );

        return ResponseEntity
                .status(
                        HttpStatus.INTERNAL_SERVER_ERROR
                )
                .body(response);
    }
}