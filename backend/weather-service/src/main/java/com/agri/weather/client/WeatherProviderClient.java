package com.agri.weather.client;

import com.agri.weather.dto.external.OpenMeteoResponse;

public interface WeatherProviderClient {

    OpenMeteoResponse getCurrentWeather(
            double latitude,
            double longitude
    );

    OpenMeteoResponse getForecast(
            double latitude,
            double longitude,
            int days
    );
}