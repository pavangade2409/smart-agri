package com.agri.weather.dto.response;

import com.agri.weather.enums.WeatherCondition;
import com.agri.weather.enums.WeatherProvider;

public class CurrentWeatherResponse {

    private Double latitude;
    private Double longitude;
    private String timezone;

    private String observedAt;

    private Double temperature;
    private Double humidity;
    private Double precipitation;
    private Double windSpeed;

    private Integer weatherCode;

    private WeatherCondition condition;

    private WeatherProvider provider;

    public CurrentWeatherResponse() {
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

    public String getObservedAt() {
        return observedAt;
    }

    public void setObservedAt(String observedAt) {
        this.observedAt = observedAt;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(Integer weatherCode) {
        this.weatherCode = weatherCode;
    }

    public WeatherCondition getCondition() {
        return condition;
    }

    public void setCondition(
            WeatherCondition condition) {

        this.condition = condition;
    }

    public WeatherProvider getProvider() {
        return provider;
    }

    public void setProvider(
            WeatherProvider provider) {

        this.provider = provider;
    }
}