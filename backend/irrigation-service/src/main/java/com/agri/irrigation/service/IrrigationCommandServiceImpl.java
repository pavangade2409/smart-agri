package com.agri.irrigation.service;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agri.irrigation.client.DeviceClient;

import com.agri.irrigation.decision.IrrigationDecision;
import com.agri.irrigation.decision.IrrigationDecisionEngine;

import com.agri.irrigation.dto.request.IrrigationCommandRequest;
import com.agri.irrigation.dto.response.IrrigationCommandResponse;

import com.agri.irrigation.entity.IrrigationExecution;
import com.agri.irrigation.entity.IrrigationZone;

import com.agri.irrigation.enums.CommandType;
import com.agri.irrigation.enums.DecisionStatus;
import com.agri.irrigation.enums.IrrigationStatus;
import com.agri.irrigation.enums.ZoneStatus;

import com.agri.irrigation.exception.ResourceNotFoundException;

import com.agri.irrigation.repository.IrrigationExecutionRepository;
import com.agri.irrigation.repository.IrrigationZoneRepository;

@Service
@Transactional
public class IrrigationCommandServiceImpl
        implements IrrigationCommandService {

    private final IrrigationZoneRepository zoneRepository;

    private final IrrigationExecutionRepository executionRepository;

    private final IrrigationDecisionEngine decisionEngine;

    private final DeviceClient deviceClient;

    public IrrigationCommandServiceImpl(
            IrrigationZoneRepository zoneRepository,
            IrrigationExecutionRepository executionRepository,
            IrrigationDecisionEngine decisionEngine,
            DeviceClient deviceClient) {

        this.zoneRepository = zoneRepository;
        this.executionRepository = executionRepository;
        this.decisionEngine = decisionEngine;
        this.deviceClient = deviceClient;
    }

    @Override
    public IrrigationCommandResponse execute(
            IrrigationCommandRequest request) {

        /*
         * STEP 1
         * Find irrigation zone
         */
        IrrigationZone zone =
                zoneRepository.findById(
                        request.getZoneId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Irrigation zone not found: "
                                        + request.getZoneId()
                        )
                );

        /*
         * STEP 2
         * Check zone status
         */
        if (zone.getStatus() != ZoneStatus.ACTIVE) {

            throw new IllegalStateException(
                    "Irrigation zone is not active"
            );
        }

        /*
         * STEP 3
         * Check idempotency
         */
        if (executionRepository.existsByIdempotencyKey(
                request.getIdempotencyKey())) {

            IrrigationExecution existing =
                    executionRepository
                            .findByIdempotencyKey(
                                    request.getIdempotencyKey()
                            )
                            .orElseThrow();

            return buildResponse(
                    existing,
                    "Duplicate command. "
                            + "Returning existing execution."
            );
        }

        /*
         * STEP 4
         * STOP command
         */
        if (request.getCommandType()
                == CommandType.STOP) {

            return executeStop(
                    zone,
                    request
            );
        }

        /*
         * STEP 5
         * Irrigation decision
         */
        IrrigationDecision decision =
                decisionEngine.evaluate(
                        zone.getId()
                );

        /*
         * STEP 6
         * Block irrigation
         */
        if (decision.getStatus()
                != DecisionStatus.IRRIGATION_REQUIRED) {

            IrrigationExecution execution =
                    createExecution(
                            zone,
                            request,
                            IrrigationStatus.CANCELLED
                    );

            execution.setFailureReason(
                    decision.getReason()
            );

            IrrigationExecution saved =
                    executionRepository.save(
                            execution
                    );

            return buildResponse(
                    saved,
                    decision.getReason()
            );
        }

        /*
         * STEP 7
         * Determine irrigation duration
         */
        Integer requestedDuration =
                request.getDurationMinutes();

        Integer recommendedDuration =
                decision
                        .getRecommendedDurationMinutes();

        int duration;

        if (requestedDuration != null) {

            duration = requestedDuration;

        } else if (recommendedDuration != null) {

            duration = recommendedDuration;

        } else {

            throw new IllegalStateException(
                    "Irrigation duration is not available"
            );
        }

        if (duration <= 0) {

            throw new IllegalArgumentException(
                    "Irrigation duration must be greater than zero"
            );
        }

        /*
         * STEP 8
         * Create execution
         */
        IrrigationExecution execution =
                createExecution(
                        zone,
                        request,
                        IrrigationStatus.RUNNING
                );

        execution.setStartedAt(
                OffsetDateTime.now()
        );

        execution.setRequestedDurationMinutes(
                duration
        );

        IrrigationExecution saved =
                executionRepository.save(
                        execution
                );

        /*
         * STEP 9
         * Start pump and valve
         */
        try {

            deviceClient.startPump(
                    zone.getPumpId(),
                    zone.getValveId()
            );

            saved.setStatus(
                    IrrigationStatus.RUNNING
            );

            executionRepository.save(saved);

            return buildResponse(
                    saved,
                    "Irrigation started successfully"
            );

        } catch (Exception ex) {

            saved.setStatus(
                    IrrigationStatus.FAILED
            );

            saved.setFailureReason(
                    ex.getMessage()
            );

            executionRepository.save(saved);

            throw new IllegalStateException(
                    "Unable to start irrigation device",
                    ex
            );
        }
    }

    private IrrigationCommandResponse executeStop(
            IrrigationZone zone,
            IrrigationCommandRequest request) {

        try {

            deviceClient.stopPump(
                    zone.getPumpId(),
                    zone.getValveId()
            );

            IrrigationExecution execution =
                    createExecution(
                            zone,
                            request,
                            IrrigationStatus.COMPLETED
                    );

            execution.setCompletedAt(
                    OffsetDateTime.now()
            );

            IrrigationExecution saved =
                    executionRepository.save(
                            execution
                    );

            return buildResponse(
                    saved,
                    "Irrigation stopped successfully"
            );

        } catch (Exception ex) {

            throw new IllegalStateException(
                    "Unable to stop irrigation device",
                    ex
            );
        }
    }

    private IrrigationExecution createExecution(
            IrrigationZone zone,
            IrrigationCommandRequest request,
            IrrigationStatus status) {

        IrrigationExecution execution =
                new IrrigationExecution();

        execution.setId(
                UUID.randomUUID()
        );

        execution.setZoneId(
                zone.getId()
        );

        execution.setCommandType(
                request.getCommandType()
        );

        execution.setStatus(status);

        execution.setIdempotencyKey(
                request.getIdempotencyKey()
        );

        execution.setCreatedAt(
                OffsetDateTime.now()
        );

        return execution;
    }

    private IrrigationCommandResponse buildResponse(
            IrrigationExecution execution,
            String message) {

        IrrigationCommandResponse response =
                new IrrigationCommandResponse();

        response.setExecutionId(
                execution.getId()
        );

        response.setZoneId(
                execution.getZoneId()
        );

        response.setStatus(
                execution.getStatus()
        );

        response.setMessage(message);

        return response;
    }
}