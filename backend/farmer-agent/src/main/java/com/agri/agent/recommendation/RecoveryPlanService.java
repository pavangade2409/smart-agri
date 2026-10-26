package com.agri.agent.recommendation;

import com.agri.agent.vision.ImageAnalysisResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecoveryPlanService {

    public List<String> plan(ImageAnalysisResult analysis) {

        if (analysis == null || analysis.confidence() < 0.70) {

            return List.of(
                    "Take a clearer crop image",
                    "Photograph affected leaves and complete plant",
                    "Inspect underside of leaves",
                    "Monitor more plants"
            );
        }

        return List.of(
                "Follow approved agricultural guidance",
                "Monitor new symptoms",
                "Upload a follow-up image",
                "Compare crop condition after treatment"
        );
    }
}