package com.agri.crop.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agri.crop.dto.request.CreateCropRequest;
import com.agri.crop.dto.request.CropSearchRequest;
import com.agri.crop.dto.request.UpdateCropRequest;
import com.agri.crop.dto.response.CropResponse;
import com.agri.crop.entity.Crop;
import com.agri.crop.entity.CropClimateRequirement;
import com.agri.crop.enums.CropStatus;
import com.agri.crop.exception.CropNotFoundException;
import com.agri.crop.mapper.CropMapper;
import com.agri.crop.repository.CropClimateRequirementRepository;
import com.agri.crop.repository.CropRepository;

@Service
@Transactional
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;
    private final CropClimateRequirementRepository climateRepository;
    private final CropMapper cropMapper;

    public CropServiceImpl(
            CropRepository cropRepository,
            CropClimateRequirementRepository climateRepository,
            CropMapper cropMapper) {

        this.cropRepository = cropRepository;
        this.climateRepository = climateRepository;
        this.cropMapper = cropMapper;
    }

    @Override
    public CropResponse create(CreateCropRequest request) {

        String cropCode = request.getCropCode()
                .trim()
                .toUpperCase();

        if (cropRepository.existsByCropCode(cropCode)) {
            throw new IllegalArgumentException(
                    "Crop code already exists: " + cropCode
            );
        }

        Crop crop = new Crop();

        crop.setCropCode(cropCode);
        crop.setName(request.getName());
        crop.setScientificName(request.getScientificName());
        crop.setCropType(request.getCropType());
        crop.setSeason(request.getSeason());
        crop.setDurationDays(request.getDurationDays());
        crop.setStatus(CropStatus.ACTIVE);
        crop.setDescription(request.getDescription());

        Crop savedCrop = cropRepository.save(crop);

        return cropMapper.toResponse(savedCrop, null);
    }

    @Override
    @Transactional(readOnly = true)
    public CropResponse getById(UUID id) {

        Crop crop = findCrop(id);

        CropClimateRequirement climate =
                climateRepository.findByCropId(id)
                        .orElse(null);

        return cropMapper.toResponse(crop, climate);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CropResponse> search(
            CropSearchRequest request,
            Pageable pageable) {

    	Specification<Crop> specification =
    	        (root, query, criteriaBuilder) -> null;
        if (request != null
                && request.getName() != null
                && !request.getName().isBlank()) {

            String name =
                    request.getName().trim().toLowerCase();

            specification = specification.and(
                    (root, query, cb) ->
                            cb.like(
                                    cb.lower(
                                            root.get("name")
                                    ),
                                    "%" + name + "%"
                            )
            );
        }

        if (request != null
                && request.getCropType() != null) {

            specification = specification.and(
                    (root, query, cb) ->
                            cb.equal(
                                    root.get("cropType"),
                                    request.getCropType()
                            )
            );
        }

        if (request != null
                && request.getSeason() != null) {

            specification = specification.and(
                    (root, query, cb) ->
                            cb.equal(
                                    root.get("season"),
                                    request.getSeason()
                            )
            );
        }

        if (request != null
                && request.getStatus() != null) {

            specification = specification.and(
                    (root, query, cb) ->
                            cb.equal(
                                    root.get("status"),
                                    request.getStatus()
                            )
            );
        }

        return cropRepository
                .findAll(specification, pageable)
                .map(crop -> {

                    CropClimateRequirement climate =
                            climateRepository
                                    .findByCropId(crop.getId())
                                    .orElse(null);

                    return cropMapper.toResponse(
                            crop,
                            climate
                    );
                });
    }

    @Override
    public CropResponse update(
            UUID id,
            UpdateCropRequest request) {

        Crop crop = findCrop(id);

        crop.setName(request.getName());
        crop.setScientificName(
                request.getScientificName()
        );
        crop.setCropType(
                request.getCropType()
        );
        crop.setSeason(
                request.getSeason()
        );
        crop.setDurationDays(
                request.getDurationDays()
        );
        crop.setDescription(
                request.getDescription()
        );

        Crop savedCrop =
                cropRepository.save(crop);

        CropClimateRequirement climate =
                climateRepository
                        .findByCropId(id)
                        .orElse(null);

        return cropMapper.toResponse(
                savedCrop,
                climate
        );
    }

    @Override
    public void deactivate(UUID id) {

        Crop crop = findCrop(id);

        crop.setStatus(
                CropStatus.INACTIVE
        );

        cropRepository.save(crop);
    }

    private Crop findCrop(UUID id) {

        return cropRepository
                .findById(id)
                .orElseThrow(() ->
                        new CropNotFoundException(
                                "Crop not found: " + id
                        )
                );
    }
}