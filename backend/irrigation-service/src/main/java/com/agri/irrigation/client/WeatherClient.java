package com.agri.irrigation.client;

public interface WeatherClient {

    WeatherData getCurrentWeather(
            double latitude,
            double longitude
    );
}