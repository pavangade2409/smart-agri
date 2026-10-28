package com.agri.sensor.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agri.sensor.entity.Sensor;
import com.agri.sensor.enums.SensorStatus;

public interface SensorRepository
        extends JpaRepository<Sensor, UUID> {

    boolean existsBySensorCode(
            String sensorCode
    );

    Optional<Sensor> findBySensorCode(
            String sensorCode
    );

    Optional<Sensor> findFirstByFarmIdAndZoneIdAndStatus(
            UUID farmId,
            UUID zoneId,
            SensorStatus status
    );
}