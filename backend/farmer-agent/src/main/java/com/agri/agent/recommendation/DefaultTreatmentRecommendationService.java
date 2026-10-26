package com.agri.agent.recommendation;

import com.agri.agent.dto.TreatmentRecommendation;
import com.agri.agent.vision.ImageAnalysisResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultTreatmentRecommendationService
        implements TreatmentRecommendationService {

    private final SafetyPolicyService safetyPolicy;
    private final RecoveryPlanService recoveryPlan;

    public DefaultTreatmentRecommendationService(
            SafetyPolicyService safetyPolicy,
            RecoveryPlanService recoveryPlan) {

        this.safetyPolicy = safetyPolicy;
        this.recoveryPlan = recoveryPlan;
    }

    @Override
    public TreatmentRecommendation recommend(
            ImageAnalysisResult analysis,
            String crop,
            String growthStage) {

        if (analysis == null
                || !safetyPolicy.allowed(
                        analysis.disease(),
                        analysis.confidence())) {

            return new TreatmentRecommendation(
                    "Uncertain diagnosis",
                    "Image confidence is below the safe threshold",
                    List.of(
                            "Take a clearer crop image",
                            "Inspect more plants"
                    ),
                    null,
                    null,
                    null,
                    null,
                    safetyPolicy.message(),
                    recoveryPlan.plan(analysis)
            );
        }

        return new TreatmentRecommendation(
                analysis.disease(),
                analysis.possibleCause(),
                List.of(
                        "Inspect additional affected plants",
                        "Check disease or pest severity",
                        "Review weather and crop conditions",
                        "Use approved agricultural guidance"
                ),
                null,
                null,
                null,
                null,
                safetyPolicy.message(),
                recoveryPlan.plan(analysis)
        );
    }
}