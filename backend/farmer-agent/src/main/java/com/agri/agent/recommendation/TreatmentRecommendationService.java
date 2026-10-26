package com.agri.agent.recommendation;

import com.agri.agent.dto.TreatmentRecommendation;
import com.agri.agent.vision.ImageAnalysisResult;

public interface TreatmentRecommendationService {

    TreatmentRecommendation recommend(
            ImageAnalysisResult analysis,
            String crop,
            String growthStage
    );
}