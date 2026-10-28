package com.agri.sensor.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agri.sensor.entity.SensorReading;

public interface SensorReadingRepository
        extends JpaRepository<SensorReading, UUID> {

    Optional<SensorReading>
    findTopBySensorIdOrderByMeasuredAtDesc(
            UUID sensorId
    );
}