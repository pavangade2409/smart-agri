package com.agri.irrigation.client;

public class WeatherData {

    private Double temperature;

    private Double humidity;

    private Double precipitation;

    private Double precipitationProbability;

    private Double windSpeed;

    public WeatherData() {
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(
            Double temperature) {

        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(
            Double humidity) {

        this.humidity = humidity;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(
            Double precipitation) {

        this.precipitation = precipitation;
    }

    public Double getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(
            Double precipitationProbability) {

        this.precipitationProbability =
                precipitationProbability;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(
            Double windSpeed) {

        this.windSpeed = windSpeed;
    }
}