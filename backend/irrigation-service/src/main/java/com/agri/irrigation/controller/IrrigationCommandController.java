package com.agri.irrigation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agri.irrigation.dto.request.IrrigationCommandRequest;
import com.agri.irrigation.dto.response.IrrigationCommandResponse;
import com.agri.irrigation.service.IrrigationCommandService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/irrigation/commands")
public class IrrigationCommandController {

    private final IrrigationCommandService service;

    public IrrigationCommandController(
            IrrigationCommandService service) {

        this.service = service;
    }

    @PostMapping
    public ResponseEntity<IrrigationCommandResponse>
    execute(
            @Valid
            @RequestBody
            IrrigationCommandRequest request) {

        return ResponseEntity.ok(
                service.execute(request)
        );
    }
}