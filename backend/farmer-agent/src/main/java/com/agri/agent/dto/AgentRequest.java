package com.agri.agent.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AgentRequest(

        @NotNull(message = "farmerId is required")
        Long farmerId,

        @NotNull(message = "farmId is required")
        Long farmId,

        Long plotId,

        String conversationId,

        @NotBlank(message = "message is required")
        String message
) {
}