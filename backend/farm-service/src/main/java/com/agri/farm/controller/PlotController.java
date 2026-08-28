package com.agri.farm.controller;

import com.agri.farm.dto.PlotRequest;
import com.agri.farm.dto.PlotResponse;
import com.agri.farm.service.PlotService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plots")
public class PlotController {

    private final PlotService plotService;

    public PlotController(
            PlotService plotService) {

        this.plotService =
                plotService;
    }

    @PostMapping
    public ResponseEntity<PlotResponse>
    createPlot(
            @Valid
            @RequestBody
            PlotRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        plotService.createPlot(
                                request
                        )
                );
    }

    @GetMapping("/{plotId}")
    public ResponseEntity<PlotResponse>
    getPlot(
            @PathVariable Long plotId) {

        return ResponseEntity.ok(
                plotService.getPlot(
                        plotId
                )
        );
    }

    @GetMapping("/farm/{farmId}")
    public ResponseEntity<List<PlotResponse>>
    getPlotsByFarm(
            @PathVariable Long farmId) {

        return ResponseEntity.ok(
                plotService.getPlotsByFarm(
                        farmId
                )
        );
    }

    @PutMapping("/{plotId}")
    public ResponseEntity<PlotResponse>
    updatePlot(
            @PathVariable Long plotId,

            @Valid
            @RequestBody
            PlotRequest request) {

        return ResponseEntity.ok(
                plotService.updatePlot(
                        plotId,
                        request
                )
        );
    }

    @DeleteMapping("/{plotId}")
    public ResponseEntity<Void>
    deletePlot(
            @PathVariable Long plotId) {

        plotService.deletePlot(
                plotId
        );

        return ResponseEntity
                .noContent()
                .build();
    }
}