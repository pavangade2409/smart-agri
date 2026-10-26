package com.agri.weather.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenMeteoResponse {

    private Double latitude;

    private Double longitude;

    private String timezone;

    private Current current;

    private Daily daily;

    public OpenMeteoResponse() {
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

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public static class Current {

        private String time;

        @JsonProperty("temperature_2m")
        private Double temperature;

        @JsonProperty("relative_humidity_2m")
        private Double humidity;

        private Double precipitation;

        @JsonProperty("wind_speed_10m")
        private Double windSpeed;

        @JsonProperty("weather_code")
        private Integer weatherCode;

        public Current() {
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
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
    }

    public static class Daily {

        private String[] time;

        @JsonProperty("temperature_2m_max")
        private Double[] temperatureMax;

        @JsonProperty("temperature_2m_min")
        private Double[] temperatureMin;

        @JsonProperty("precipitation_sum")
        private Double[] precipitationSum;

        @JsonProperty("precipitation_probability_max")
        private Integer[] precipitationProbabilityMax;

        @JsonProperty("et0_fao_evapotranspiration")
        private Double[] et0;

        public Daily() {
        }

        public String[] getTime() {
            return time;
        }

        public void setTime(String[] time) {
            this.time = time;
        }

        public Double[] getTemperatureMax() {
            return temperatureMax;
        }

        public void setTemperatureMax(Double[] temperatureMax) {
            this.temperatureMax = temperatureMax;
        }

        public Double[] getTemperatureMin() {
            return temperatureMin;
        }

        public void setTemperatureMin(Double[] temperatureMin) {
            this.temperatureMin = temperatureMin;
        }

        public Double[] getPrecipitationSum() {
            return precipitationSum;
        }

        public void setPrecipitationSum(Double[] precipitationSum) {
            this.precipitationSum = precipitationSum;
        }

        public Integer[] getPrecipitationProbabilityMax() {
            return precipitationProbabilityMax;
        }

        public void setPrecipitationProbabilityMax(
                Integer[] precipitationProbabilityMax) {

            this.precipitationProbabilityMax =
                    precipitationProbabilityMax;
        }

        public Double[] getEt0() {
            return et0;
        }

        public void setEt0(Double[] et0) {
            this.et0 = et0;
        }
    }
}