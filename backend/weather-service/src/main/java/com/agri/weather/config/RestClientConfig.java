package com.agri.weather.config;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(WeatherProperties.class)
public class RestClientConfig {

    @Bean
    public RestClient weatherRestClient(
            WeatherProperties properties) {

        RequestConfig requestConfig =
                RequestConfig.custom()
                .setConnectionRequestTimeout(
                        Timeout.ofSeconds(
                                properties.getConnectTimeoutSeconds()
                        )
                )
                
                        .setResponseTimeout(
                                Timeout.ofSeconds(
                                        properties
                                                .getReadTimeoutSeconds()
                                )
                        )
                        .build();

        CloseableHttpClient httpClient =
                HttpClients.custom()
                        .setDefaultRequestConfig(
                                requestConfig
                        )
                        .setConnectionManager(
                                PoolingHttpClientConnectionManagerBuilder
                                        .create()
                                        .build()
                        )
                        .build();

        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory(
                        httpClient
                );

        return RestClient.builder()
                .requestFactory(factory)
                .baseUrl(properties.getBaseUrl())
                .build();
    }
}