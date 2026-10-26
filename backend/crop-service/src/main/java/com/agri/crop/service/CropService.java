package com.agri.crop.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.agri.crop.dto.request.CreateCropRequest;
import com.agri.crop.dto.request.CropSearchRequest;
import com.agri.crop.dto.request.UpdateCropRequest;
import com.agri.crop.dto.response.CropResponse;

import java.util.UUID;

public interface CropService {

    CropResponse create(CreateCropRequest request);

    CropResponse getById(UUID id);

    Page<CropResponse> search(
            CropSearchRequest request,
            Pageable pageable
    );

    CropResponse update(
            UUID id,
            UpdateCropRequest request
    );

    void deactivate(UUID id);
}
