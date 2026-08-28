package com.agri.farm.service;

import com.agri.farm.dto.FarmRequest;
import com.agri.farm.dto.FarmResponse;

import java.util.List;

public interface FarmService {

    FarmResponse createFarm(
            FarmRequest request
    );

    FarmResponse getFarm(
            Long farmId
    );

    List<FarmResponse> getAllFarms();

    List<FarmResponse> getFarmsByFarmer(
            Long farmerId
    );

    FarmResponse updateFarm(
            Long farmId,
            FarmRequest request
    );

    void deleteFarm(
            Long farmId
    );
}