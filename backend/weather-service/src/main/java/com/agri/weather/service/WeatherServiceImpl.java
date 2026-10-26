package com.agri.weather.service;

import org.springframework.stereotype.Service;

import com.agri.weather.client.WeatherProviderClient;
import com.agri.weather.dto.external.CurrentWeatherRequest;
import com.agri.weather.dto.external.OpenMeteoResponse;
import com.agri.weather.dto.request.ForecastRequest;
import com.agri.weather.dto.response.CurrentWeatherResponse;
import com.agri.weather.dto.response.ForecastResponse;
import com.agri.weather.mapper.WeatherMapper;

@Service
public class WeatherServiceImpl
        implements WeatherService {

    private final WeatherProviderClient providerClient;

    private final WeatherMapper weatherMapper;

    public WeatherServiceImpl(
            WeatherProviderClient providerClient,
            WeatherMapper weatherMapper) {

        this.providerClient = providerClient;
        this.weatherMapper = weatherMapper;
    }

    @Override
    public CurrentWeatherResponse getCurrentWeather(
            CurrentWeatherRequest request) {

        OpenMeteoResponse response =
                providerClient.getCurrentWeather(
                        request.getLatitude(),
                        request.getLongitude()
                );

        return weatherMapper.toCurrentResponse(
                response
        );
    }

    @Override
    public ForecastResponse getForecast(
            ForecastRequest request) {

        OpenMeteoResponse response =
                providerClient.getForecast(
                        request.getLatitude(),
                        request.getLongitude(),
                        request.getDays()
                );

        return weatherMapper.toForecastResponse(
                response
        );
    }
}