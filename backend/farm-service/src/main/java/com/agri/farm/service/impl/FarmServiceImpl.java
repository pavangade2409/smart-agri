package com.agri.farm.service.impl;

import com.agri.farm.dto.FarmRequest;
import com.agri.farm.dto.FarmResponse;
import com.agri.farm.entity.Farm;
import com.agri.farm.exception.ResourceNotFoundException;
import com.agri.farm.repository.FarmRepository;
import com.agri.farm.service.FarmService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FarmServiceImpl
        implements FarmService {

    private final FarmRepository farmRepository;

    public FarmServiceImpl(
            FarmRepository farmRepository) {

        this.farmRepository =
                farmRepository;
    }

    @Override
    public FarmResponse createFarm(
            FarmRequest request) {

        Farm farm = new Farm();

        farm.setFarmerId(
                request.farmerId()
        );

        farm.setFarmName(
                request.farmName().trim()
        );

        farm.setLatitude(
                request.latitude()
        );

        farm.setLongitude(
                request.longitude()
        );

        farm.setTotalAcres(
                request.totalAcres()
        );

        Farm saved =
                farmRepository.save(farm);

        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public FarmResponse getFarm(
            Long farmId) {

        Farm farm =
                findFarm(farmId);

        return toResponse(farm);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FarmResponse> getAllFarms() {

        return farmRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FarmResponse>
    getFarmsByFarmer(
            Long farmerId) {

        return farmRepository
                .findByFarmerId(farmerId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public FarmResponse updateFarm(
            Long farmId,
            FarmRequest request) {

        Farm farm =
                findFarm(farmId);

        farm.setFarmName(
                request.farmName().trim()
        );

        farm.setLatitude(
                request.latitude()
        );

        farm.setLongitude(
                request.longitude()
        );

        farm.setTotalAcres(
                request.totalAcres()
        );

        Farm updated =
                farmRepository.save(farm);

        return toResponse(updated);
    }

    @Override
    public void deleteFarm(
            Long farmId) {

        Farm farm =
                findFarm(farmId);

        farmRepository.delete(farm);
    }

    private Farm findFarm(
            Long farmId) {

        return farmRepository
                .findById(farmId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Farm not found: "
                                        + farmId
                        )
                );
    }

    private FarmResponse toResponse(
            Farm farm) {

        return new FarmResponse(
                farm.getId(),
                farm.getFarmerId(),
                farm.getFarmName(),
                farm.getLatitude(),
                farm.getLongitude(),
                farm.getTotalAcres(),
                farm.getCreatedAt(),
                farm.getUpdatedAt()
        );
    }
}