package com.agri.sensor.service;

import java.util.UUID;
import java.util.List;

import com.agri.sensor.dto.request.CreateSensorRequest;
import com.agri.sensor.dto.response.SensorResponse;

public interface SensorService {

    SensorResponse create(
            CreateSensorRequest request
    );

    SensorResponse getById(
            UUID id
    );
    SensorResponse getSensorByFarmAndZone(
            UUID farmId,
            UUID zoneId
    );

    List<SensorResponse> getAll();
}
