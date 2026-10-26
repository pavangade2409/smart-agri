package com.agri.crop.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agri.crop.dto.request.CreateCropRequest;
import com.agri.crop.dto.request.CropSearchRequest;
import com.agri.crop.dto.request.UpdateCropRequest;
import com.agri.crop.dto.response.CropResponse;
import com.agri.crop.enums.CropSeason;
import com.agri.crop.enums.CropStatus;
import com.agri.crop.enums.CropType;
import com.agri.crop.service.CropService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/crops")
@Tag(
        name = "Crop Management",
        description = "Crop master data APIs"
)
public class CropController {

    private final CropService cropService;

    // Constructor Injection
    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    // =========================================================
    // CREATE CROP
    // =========================================================

    @PostMapping
    @Operation(summary = "Create crop")
    public ResponseEntity<CropResponse> createCrop(
            @Valid
            @RequestBody
            CreateCropRequest request) {

        CropResponse response =
                cropService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // =========================================================
    // GET CROP BY ID
    // =========================================================

    @GetMapping("/{id}")
    @Operation(summary = "Get crop by ID")
    public ResponseEntity<CropResponse> getCrop(
            @PathVariable UUID id) {

        CropResponse response =
                cropService.getById(id);

        return ResponseEntity.ok(response);
    }

    // =========================================================
    // SEARCH / LIST CROPS
    // =========================================================

    @GetMapping
    @Operation(summary = "Search crops")
    public ResponseEntity<Page<CropResponse>> searchCrops(

            @RequestParam(required = false)
            String name,

            @RequestParam(required = false)
            CropType cropType,

            @RequestParam(required = false)
            CropSeason season,

            @RequestParam(required = false)
            CropStatus status,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "20")
            int size,

            @RequestParam(defaultValue = "name")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        // Prevent negative page
        if (page < 0) {
            page = 0;
        }

        // Prevent invalid / excessive page size
        if (size < 1) {
            size = 20;
        }

        size = Math.min(size, 100);

        // Determine sorting direction
        Sort.Direction sortDirection =
                direction.equalsIgnoreCase("desc")
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC;

        // Create pageable
        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(
                                sortDirection,
                                sortBy
                        )
                );

        // Create search request
        CropSearchRequest request =
                new CropSearchRequest(
                        name,
                        cropType,
                        season,
                        status
                );

        // Call service
        Page<CropResponse> response =
                cropService.search(
                        request,
                        pageable
                );

        return ResponseEntity.ok(response);
    }

    // =========================================================
    // UPDATE CROP
    // =========================================================

    @PutMapping("/{id}")
    @Operation(summary = "Update crop")
    public ResponseEntity<CropResponse> updateCrop(

            @PathVariable UUID id,

            @Valid
            @RequestBody
            UpdateCropRequest request) {

        CropResponse response =
                cropService.update(
                        id,
                        request
                );

        return ResponseEntity.ok(response);
    }

    // =========================================================
    // DEACTIVATE CROP
    // =========================================================

    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate crop")
    public ResponseEntity<Void> deactivateCrop(
            @PathVariable UUID id) {

        cropService.deactivate(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}