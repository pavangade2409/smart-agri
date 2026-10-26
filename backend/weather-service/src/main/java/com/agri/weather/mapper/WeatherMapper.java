package com.agri.weather.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.agri.weather.dto.external.OpenMeteoResponse;
import com.agri.weather.dto.response.CurrentWeatherResponse;
import com.agri.weather.dto.response.DailyForecastResponse;
import com.agri.weather.dto.response.ForecastResponse;
import com.agri.weather.enums.WeatherCondition;
import com.agri.weather.enums.WeatherProvider;

@Component
public class WeatherMapper {

    public CurrentWeatherResponse toCurrentResponse(
            OpenMeteoResponse source) {

        CurrentWeatherResponse response =
                new CurrentWeatherResponse();

        response.setLatitude(
                source.getLatitude()
        );

        response.setLongitude(
                source.getLongitude()
        );

        response.setTimezone(
                source.getTimezone()
        );

        response.setProvider(
                WeatherProvider.OPEN_METEO
        );

        if (source.getCurrent() != null) {

            OpenMeteoResponse.Current current =
                    source.getCurrent();

            response.setObservedAt(
                    current.getTime()
            );

            response.setTemperature(
                    current.getTemperature()
            );

            response.setHumidity(
                    current.getHumidity()
            );

            response.setPrecipitation(
                    current.getPrecipitation()
            );

            response.setWindSpeed(
                    current.getWindSpeed()
            );

            response.setWeatherCode(
                    current.getWeatherCode()
            );

            response.setCondition(
                    mapCondition(
                            current.getWeatherCode()
                    )
            );
        }

        return response;
    }

    public ForecastResponse toForecastResponse(
            OpenMeteoResponse source) {

        ForecastResponse response =
                new ForecastResponse();

        response.setLatitude(
                source.getLatitude()
        );

        response.setLongitude(
                source.getLongitude()
        );

        response.setTimezone(
                source.getTimezone()
        );

        response.setProvider(
                WeatherProvider.OPEN_METEO
        );

        List<DailyForecastResponse> forecasts =
                new ArrayList<>();

        if (source.getDaily() != null) {

            OpenMeteoResponse.Daily daily =
                    source.getDaily();

            String[] dates = daily.getTime();

            Double[] max =
                    daily.getTemperatureMax();

            Double[] min =
                    daily.getTemperatureMin();

            Double[] rain =
                    daily.getPrecipitationSum();

            Integer[] probability =
                    daily.getPrecipitationProbabilityMax();

            Double[] et0 =
                    daily.getEt0();

            if (dates != null) {

                for (int i = 0;
                     i < dates.length;
                     i++) {

                    DailyForecastResponse forecast =
                            new DailyForecastResponse();

                    forecast.setDate(
                            dates[i]
                    );

                    if (max != null && i < max.length) {
                        forecast.setTemperatureMax(
                                max[i]
                        );
                    }

                    if (min != null && i < min.length) {
                        forecast.setTemperatureMin(
                                min[i]
                        );
                    }

                    if (rain != null && i < rain.length) {
                        forecast.setPrecipitation(
                                rain[i]
                        );
                    }

                    if (probability != null
                            && i < probability.length) {

                        forecast
                                .setPrecipitationProbability(
                                        probability[i]
                                );
                    }

                    if (et0 != null && i < et0.length) {
                        forecast.setEvapotranspiration(
                                et0[i]
                        );
                    }

                    forecasts.add(forecast);
                }
            }
        }

        response.setDailyForecast(
                forecasts
        );

        return response;
    }

    private WeatherCondition mapCondition(
            Integer code) {

        if (code == null) {
            return WeatherCondition.UNKNOWN;
        }

        if (code == 0) {
            return WeatherCondition.CLEAR;
        }

        if (code >= 1 && code <= 3) {
            return WeatherCondition.PARTLY_CLOUDY;
        }

        if (code >= 45 && code <= 48) {
            return WeatherCondition.FOG;
        }

        if (code >= 51 && code <= 57) {
            return WeatherCondition.DRIZZLE;
        }

        if (code >= 61 && code <= 67) {
            return WeatherCondition.RAIN;
        }

        if (code >= 71 && code <= 77) {
            return WeatherCondition.SNOW;
        }

        if (code >= 80 && code <= 82) {
            return WeatherCondition.RAIN;
        }

        if (code >= 95) {
            return WeatherCondition.THUNDERSTORM;
        }

        return WeatherCondition.UNKNOWN;
    }
}