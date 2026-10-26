package com.agri.gateway.filter;

import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import org.springframework.stereotype.Component;

import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class RequestLoggingFilter
        implements GlobalFilter, Ordered {

    private static final Logger log =
            LoggerFactory.getLogger(
                    RequestLoggingFilter.class
            );

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        Instant start =
                Instant.now();

        String method =
                exchange.getRequest()
                        .getMethod()
                        .name();

        String path =
                exchange.getRequest()
                        .getURI()
                        .getPath();

        String correlationId =
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(
                                "X-Correlation-Id"
                        );

        return chain.filter(exchange)
                .doFinally(signal -> {

                    long duration =
                            Duration.between(
                                    start,
                                    Instant.now()
                            ).toMillis();

                    Integer status =
                            exchange.getResponse()
                                    .getStatusCode()
                                    != null
                                    ? exchange
                                        .getResponse()
                                        .getStatusCode()
                                        .value()
                                    : null;

                    log.info(
                            "method={} path={} status={} durationMs={} correlationId={}",
                            method,
                            path,
                            status,
                            duration,
                            correlationId
                    );
                });
    }

    @Override
    public int getOrder() {

        return Ordered.LOWEST_PRECEDENCE;
    }
}