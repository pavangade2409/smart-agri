package com.agri.weather.dto.response;

public class DailyForecastResponse {

    private String date;

    private Double temperatureMax;

    private Double temperatureMin;

    private Double precipitation;

    private Integer precipitationProbability;

    private Double evapotranspiration;

    public DailyForecastResponse() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(
            Double temperatureMax) {

        this.temperatureMax = temperatureMax;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(
            Double temperatureMin) {

        this.temperatureMin = temperatureMin;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(
            Double precipitation) {

        this.precipitation = precipitation;
    }

    public Integer getPrecipitationProbability() {
        return precipitationProbability;
    }

    public void setPrecipitationProbability(
            Integer precipitationProbability) {

        this.precipitationProbability =
                precipitationProbability;
    }

    public Double getEvapotranspiration() {
        return evapotranspiration;
    }

    public void setEvapotranspiration(
            Double evapotranspiration) {

        this.evapotranspiration =
                evapotranspiration;
    }
}