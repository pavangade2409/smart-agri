package com.agri.infrastructure.service;

import com.agri.infrastructure.entity.Device;
import com.agri.infrastructure.enums.DeviceStatus;
import com.agri.infrastructure.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    public List<Device> findAll() {
        return repository.findAll();
    }

    public Device findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Device not found: " + id));
    }

    public Device create(Device device) {
        device.setId(null);
        device.setLastSeen(Instant.now());
        return repository.save(device);
    }

    public Device updateStatus(Long id, DeviceStatus status) {
        Device device = findById(id);
        device.setStatus(status);
        device.setLastSeen(Instant.now());
        return repository.save(device);
    }
}
