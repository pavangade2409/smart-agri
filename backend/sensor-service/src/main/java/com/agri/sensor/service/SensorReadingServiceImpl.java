package com.agri.sensor.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agri.sensor.dto.request.SensorReadingRequest;
import com.agri.sensor.dto.response.SensorReadingResponse;
import com.agri.sensor.entity.Sensor;
import com.agri.sensor.entity.SensorReading;
import com.agri.sensor.enums.ReadingStatus;
import com.agri.sensor.enums.SensorStatus;
import com.agri.sensor.exception.ResourceNotFoundException;
import com.agri.sensor.repository.SensorReadingRepository;
import com.agri.sensor.repository.SensorRepository;

@Service
@Transactional
public class SensorReadingServiceImpl
        implements SensorReadingService {

    private final SensorRepository sensorRepository;

    private final SensorReadingRepository
            readingRepository;

    public SensorReadingServiceImpl(
            SensorRepository sensorRepository,
            SensorReadingRepository readingRepository) {

        this.sensorRepository =
                sensorRepository;

        this.readingRepository =
                readingRepository;
    }

    @Override
    public SensorReadingResponse record(
            SensorReadingRequest request) {

        UUID sensorId = request.getSensorId();
		Sensor sensor =
                sensorRepository.findById(
                        sensorId
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Sensor not found: "
                                        + sensorId
                        )
                );

        if (sensor.getStatus()
                != SensorStatus.ACTIVE) {

            throw new IllegalStateException(
                    "Sensor is not active"
            );
        }

        if (request.getValue() == null) {

            throw new IllegalArgumentException(
                    "Sensor value is required"
            );
        }

        SensorReading reading =
                new SensorReading();

        reading.setSensorId(
                sensor.getId()
        );

        reading.setValue(
                request.getValue()
        );

        reading.setUnit(
                sensor.getUnit()
        );

        reading.setMeasuredAt(
                request.getMeasuredAt()
        );

        reading.setStatus(
                ReadingStatus.VALID
        );

        SensorReading saved =
                readingRepository.save(
                        reading
                );

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SensorReadingResponse getLatest(
            UUID sensorId) {

        sensorRepository.findById(sensorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Sensor not found: "
                                        + sensorId
                        )
                );

        SensorReading reading =
                readingRepository
                        .findTopBySensorIdOrderByMeasuredAtDesc(
                                sensorId
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "No reading found for sensor: "
                                                + sensorId
                                )
                        );

        return toResponse(reading);
    }

    private SensorReadingResponse toResponse(
            SensorReading reading) {

        SensorReadingResponse response =
                new SensorReadingResponse();

        response.setId(
                reading.getId()
        );

        response.setSensorId(
                reading.getSensorId()
        );

        response.setValue(
                reading.getValue()
        );

        response.setUnit(
                reading.getUnit()
        );

        response.setMeasuredAt(
                reading.getMeasuredAt()
        );

        response.setStatus(
                reading.getStatus()
        );

        return response;
    }
}