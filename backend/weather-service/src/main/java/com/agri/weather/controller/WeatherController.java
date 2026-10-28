package com.agri.weather.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agri.weather.dto.external.CurrentWeatherRequest;
import com.agri.weather.dto.request.ForecastRequest;
import com.agri.weather.dto.response.CurrentWeatherResponse;
import com.agri.weather.dto.response.ForecastResponse;
import com.agri.weather.service.WeatherService;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/weather")
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(
            WeatherService weatherService) {

        this.weatherService = weatherService;
    }

    @GetMapping("/current")
    public ResponseEntity<CurrentWeatherResponse>
    getCurrentWeather(

            @RequestParam
            @DecimalMin("-90.0")
            @DecimalMax("90.0")
            Double latitude,

            @RequestParam
            @DecimalMin("-180.0")
            @DecimalMax("180.0")
            Double longitude) {

        CurrentWeatherRequest request =
                new CurrentWeatherRequest();

        request.setLatitude(latitude);
        request.setLongitude(longitude);

        return ResponseEntity.ok(
                weatherService.getCurrentWeather(
                        request
                )
        );
    }

    @GetMapping("/forecast")
    public ResponseEntity<ForecastResponse>
    getForecast(

            @RequestParam
            @DecimalMin("-90.0")
            @DecimalMax("90.0")
            Double latitude,

            @RequestParam
            @DecimalMin("-180.0")
            @DecimalMax("180.0")
            Double longitude,

            @RequestParam(defaultValue = "7")
            @Min(1)
            @Max(7)
            int days) {

        ForecastRequest request =
                new ForecastRequest();

        request.setLatitude(latitude);
        request.setLongitude(longitude);
        request.setDays(days);

        return ResponseEntity.ok(
                weatherService.getForecast(
                        request
                )
        );
    }
}