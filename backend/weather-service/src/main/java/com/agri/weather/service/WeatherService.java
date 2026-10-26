package com.agri.weather.service;

import com.agri.weather.dto.external.CurrentWeatherRequest;
import com.agri.weather.dto.request.ForecastRequest;
import com.agri.weather.dto.response.CurrentWeatherResponse;
import com.agri.weather.dto.response.ForecastResponse;

public interface WeatherService {
    CurrentWeatherResponse getCurrentWeather(CurrentWeatherRequest request);
    ForecastResponse getForecast(ForecastRequest request);
}