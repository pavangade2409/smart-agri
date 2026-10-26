package com.agri.agent.dto;

import com.agri.agent.vision.ImageAnalysisResult;

import java.util.UUID;

public record CropImageResponse(
        UUID requestId,
        String crop,
        String growthStage,
        ImageAnalysisResult imageAnalysis,
        TreatmentRecommendation recommendation,
        String answer,
        String audioUrl
) {
}