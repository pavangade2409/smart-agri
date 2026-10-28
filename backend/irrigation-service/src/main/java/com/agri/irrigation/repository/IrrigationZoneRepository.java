
package com.agri.irrigation.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agri.irrigation.entity.IrrigationZone;

public interface IrrigationZoneRepository
        extends JpaRepository<IrrigationZone, UUID> {

    boolean existsByZoneCode(String zoneCode);
}
