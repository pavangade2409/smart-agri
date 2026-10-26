package com.agri.agent.dto;

public record AgentResponse(

        String conversationId,

        String answer,

        String recommendation,

        String source
) {
}