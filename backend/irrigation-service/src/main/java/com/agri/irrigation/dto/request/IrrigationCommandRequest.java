package com.agri.irrigation.dto.request;

import java.util.UUID;

import com.agri.irrigation.enums.CommandType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class IrrigationCommandRequest {

    @NotNull
    private UUID zoneId;

    @NotNull
    private CommandType commandType;

    @NotBlank
    private String idempotencyKey;

    private Integer durationMinutes;

    public IrrigationCommandRequest() {
    }

    public UUID getZoneId() {
        return zoneId;
    }

    public void setZoneId(UUID zoneId) {
        this.zoneId = zoneId;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(
            String idempotencyKey) {

        this.idempotencyKey = idempotencyKey;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(
            Integer durationMinutes) {

        this.durationMinutes = durationMinutes;
    }
}