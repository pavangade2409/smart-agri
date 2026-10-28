package com.agri.sensor.service;

import java.util.UUID;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agri.sensor.dto.request.CreateSensorRequest;
import com.agri.sensor.dto.response.SensorResponse;
import com.agri.sensor.entity.Sensor;
import com.agri.sensor.enums.SensorStatus;
import com.agri.sensor.exception.ResourceNotFoundException;
import com.agri.sensor.repository.SensorRepository;

@Service
@Transactional
public class SensorServiceImpl
        implements SensorService {

    private final SensorRepository sensorRepository;

    public SensorServiceImpl(
            SensorRepository sensorRepository) {

        this.sensorRepository =
                sensorRepository;
    }

    @Override
    public SensorResponse create(
            CreateSensorRequest request) {

        String sensorCode =
                request.getSensorCode()
                        .trim()
                        .toUpperCase();

        if (sensorRepository
                .existsBySensorCode(sensorCode)) {

            throw new IllegalArgumentException(
                    "Sensor code already exists: "
                            + sensorCode
            );
        }

        Sensor sensor = new Sensor();

        sensor.setSensorCode(sensorCode);
        sensor.setFarmId(request.getFarmId());
        sensor.setZoneId(request.getZoneId());
        sensor.setSensorType(
                request.getSensorType()
        );
        sensor.setUnit(
                request.getUnit()
        );
        sensor.setDeviceId(
                request.getDeviceId()
        );
        sensor.setStatus(
                SensorStatus.ACTIVE
        );

        Sensor saved =
                sensorRepository.save(sensor);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SensorResponse getById(
            UUID id) {

        Sensor sensor =
                sensorRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Sensor not found: "
                                                + id
                                )
                        );

        return toResponse(sensor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SensorResponse> getAll() {
        return sensorRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private SensorResponse toResponse(
            Sensor sensor) {

        SensorResponse response =
                new SensorResponse();

        response.setId(
                sensor.getId()
        );

        response.setSensorCode(
                sensor.getSensorCode()
        );

        response.setFarmId(
                sensor.getFarmId()
        );

        response.setZoneId(
                sensor.getZoneId()
        );

        response.setSensorType(
                sensor.getSensorType()
        );

        response.setUnit(
                sensor.getUnit()
        );

        response.setDeviceId(
                sensor.getDeviceId()
        );

        response.setStatus(
                sensor.getStatus()
        );

        response.setCreatedAt(
                sensor.getCreatedAt()
        );

        response.setUpdatedAt(
                sensor.getUpdatedAt()
        );

        return response;
    }
    @Override
    @Transactional(readOnly = true)
    public SensorResponse getSensorByFarmAndZone(
            UUID farmId,
            UUID zoneId) {

        Sensor sensor =
                sensorRepository
                        .findFirstByFarmIdAndZoneIdAndStatus(
                                farmId,
                                zoneId,
                                SensorStatus.ACTIVE
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Active sensor not found for farm: "
                                                + farmId
                                                + ", zone: "
                                                + zoneId
                                )
                        );

        return toResponse(sensor);
    }
}
