package com.agri.sensor.service;

import java.util.UUID;

import com.agri.sensor.dto.request.SensorReadingRequest;
import com.agri.sensor.dto.response.SensorReadingResponse;

public interface SensorReadingService {

    SensorReadingResponse record(
            SensorReadingRequest request
    );

    SensorReadingResponse getLatest(
            UUID sensorId
    );
}