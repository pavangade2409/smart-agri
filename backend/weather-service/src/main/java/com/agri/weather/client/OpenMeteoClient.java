package com.agri.weather.client;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.agri.weather.config.WeatherProperties;
import com.agri.weather.dto.external.OpenMeteoResponse;
import com.agri.weather.exception.WeatherException;

@Component
public class OpenMeteoClient
        implements WeatherProviderClient {

    private final RestClient restClient;

    private final WeatherProperties properties;

    public OpenMeteoClient(
            RestClient weatherRestClient,
            WeatherProperties properties) {

        this.restClient = weatherRestClient;
        this.properties = properties;
    }

    @Override
    public OpenMeteoResponse getCurrentWeather(
            double latitude,
            double longitude) {

        return executeWithRetry(
                latitude,
                longitude,
                null
        );
    }

    @Override
    public OpenMeteoResponse getForecast(
            double latitude,
            double longitude,
            int days) {

        return executeWithRetry(
                latitude,
                longitude,
                days
        );
    }

    private OpenMeteoResponse executeWithRetry(
            double latitude,
            double longitude,
            Integer days) {

        int maxAttempts =
                Math.max(
                        1,
                        properties.getMaxRetries()
                );

        Exception lastException = null;

        for (int attempt = 1;
             attempt <= maxAttempts;
             attempt++) {

            try {

                return callProvider(
                        latitude,
                        longitude,
                        days
                );

            } catch (RestClientException ex) {

                lastException = ex;

                if (attempt < maxAttempts) {

                    try {

                        long delay =
                                500L * attempt;

                        Thread.sleep(delay);

                    } catch (
                            InterruptedException interrupted) {

                        Thread.currentThread()
                                .interrupt();

                        throw new WeatherException(
                                "Weather request interrupted",
                                interrupted
                        );
                    }
                }
            }
        }

        throw new WeatherException(
                "Weather provider unavailable after "
                        + maxAttempts
                        + " attempts",
                lastException
        );
    }

    private OpenMeteoResponse callProvider(
            double latitude,
            double longitude,
            Integer days) {

        return restClient
                .get()
                .uri(uriBuilder -> {

                    uriBuilder
                            .path("/v1/forecast")
                            .queryParam(
                                    "latitude",
                                    latitude
                            )
                            .queryParam(
                                    "longitude",
                                    longitude
                            )
                            .queryParam(
                                    "timezone",
                                    "auto"
                            );

                    if (days == null) {

                        uriBuilder.queryParam(
                                "current",
                                "temperature_2m,"
                                        + "relative_humidity_2m,"
                                        + "precipitation,"
                                        + "wind_speed_10m,"
                                        + "weather_code"
                        );

                    } else {

                        uriBuilder
                                .queryParam(
                                        "forecast_days",
                                        days
                                )
                                .queryParam(
                                        "current",
                                        "temperature_2m,"
                                                + "relative_humidity_2m,"
                                                + "precipitation,"
                                                + "wind_speed_10m,"
                                                + "weather_code"
                                )
                                .queryParam(
                                        "daily",
                                        "temperature_2m_max,"
                                                + "temperature_2m_min,"
                                                + "precipitation_sum,"
                                                + "precipitation_probability_max,"
                                                + "et0_fao_evapotranspiration"
                                );
                    }

                    return uriBuilder.build();
                })
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        (request, response) -> {
                            throw new WeatherException(
                                    "Weather provider returned HTTP "
                                            + response.getStatusCode()
                            );
                        }
                )
                .body(OpenMeteoResponse.class);
    }
}