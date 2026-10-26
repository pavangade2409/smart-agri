package com.agri.farm.controller;

import com.agri.farm.dto.FarmRequest;
import com.agri.farm.dto.FarmResponse;
import com.agri.farm.service.FarmService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/farms")
public class FarmController {

    private final FarmService farmService;

    public FarmController(
            FarmService farmService) {

        this.farmService =
                farmService;
    }

    @PostMapping
    public ResponseEntity<FarmResponse>
    createFarm(
            @Valid
            @RequestBody
            FarmRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        farmService.createFarm(
                                request
                        )
                );
    }

    @GetMapping("/{farmId}")
    public ResponseEntity<FarmResponse>
    getFarm(
            @PathVariable Long farmId) {

        return ResponseEntity.ok(
                farmService.getFarm(
                        farmId
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<FarmResponse>>
    getAllFarms() {

        return ResponseEntity.ok(
                farmService.getAllFarms()
        );
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<FarmResponse>>
    getFarmsByFarmer(
            @PathVariable Long farmerId) {

        return ResponseEntity.ok(
                farmService.getFarmsByFarmer(
                        farmerId
                )
        );
    }

    @PutMapping("/{farmId}")
    public ResponseEntity<FarmResponse>
    updateFarm(
            @PathVariable Long farmId,

            @Valid
            @RequestBody
            FarmRequest request) {

        return ResponseEntity.ok(
                farmService.updateFarm(
                        farmId,
                        request
                )
        );
    }

    @DeleteMapping("/{farmId}")
    public ResponseEntity<Void>
    deleteFarm(
            @PathVariable Long farmId) {

        farmService.deleteFarm(
                farmId
        );

        return ResponseEntity
                .noContent()
                .build();
    }
}
