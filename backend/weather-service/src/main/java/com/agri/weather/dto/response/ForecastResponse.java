package com.agri.weather.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.agri.weather.enums.WeatherProvider;

public class ForecastResponse {

    private Double latitude;

    private Double longitude;

    private String timezone;

    private WeatherProvider provider;

    private List<DailyForecastResponse> dailyForecast =
            new ArrayList<>();

    public ForecastResponse() {
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public WeatherProvider getProvider() {
        return provider;
    }

    public void setProvider(
            WeatherProvider provider) {

        this.provider = provider;
    }

    public List<DailyForecastResponse> getDailyForecast() {
        return dailyForecast;
    }

    public void setDailyForecast(
            List<DailyForecastResponse> dailyForecast) {

        this.dailyForecast = dailyForecast;
    }
}