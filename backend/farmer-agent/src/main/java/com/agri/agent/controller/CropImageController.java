package com.agri.agent.controller;

import com.agri.agent.ai.TreatmentReasoningService;
import com.agri.agent.ai.VisionAiService;
import com.agri.agent.dto.CropImageResponse;
import com.agri.agent.dto.TreatmentRecommendation;
import com.agri.agent.recommendation.TreatmentRecommendationService;
import com.agri.agent.vision.ImageAnalysisResult;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/farmer-agent")
public class CropImageController {

    private final VisionAiService vision;
    private final TreatmentRecommendationService treatment;
    private final TreatmentReasoningService reasoning;

    public CropImageController(
            VisionAiService vision,
            TreatmentRecommendationService treatment,
            TreatmentReasoningService reasoning) {

        this.vision = vision;
        this.treatment = treatment;
        this.reasoning = reasoning;
    }

    @PostMapping(
            value = "/analyze",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public CropImageResponse analyze(
            @RequestPart("image") MultipartFile image,

            @RequestParam String crop,

            @RequestParam(required = false)
            String cropVariety,

            @RequestParam(required = false)
            String growthStage,

            @RequestParam(required = false)
            Double latitude,

            @RequestParam(required = false)
            Double longitude,

            @RequestParam(required = false)
            String observationDate,

            @RequestParam(defaultValue = "en")
            String language,

            @RequestParam(required = false)
            String question

    ) throws IOException {

        validateImage(image);

        // 1. Analyze crop image using Vision AI
        ImageAnalysisResult analysis =
                vision.analyze(
                        image.getBytes(),
                        image.getContentType(),
                        crop,
                        growthStage,
                        question
                );

        // 2. Generate treatment recommendation
        TreatmentRecommendation recommendation =
                treatment.recommend(
                        analysis,
                        crop,
                        growthStage
                );

        // 3. Build current farm context
        String farmContext = String.format(
                """
                Crop: %s
                Crop Variety: %s
                Growth Stage: %s
                Latitude: %s
                Longitude: %s
                Observation Date: %s
                """,
                crop,
                cropVariety,
                growthStage,
                latitude,
                longitude,
                observationDate == null
                        ? LocalDate.now()
                        : observationDate
        );

        /*
         * RAG will be connected after the existing
         * KnowledgeService API is matched.
         */
        String ragContext = "";

        // 4. Generate farmer-friendly AI explanation
        String answer =
                reasoning.reason(
                        analysis,
                        recommendation,
                        farmContext,
                        ragContext,
                        language,
                        question
                );

        // 5. Return response
        return new CropImageResponse(
                UUID.randomUUID(),
                crop,
                growthStage,
                analysis,
                recommendation,
                answer,
                null
        );
    }

    private void validateImage(MultipartFile image) {

        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException(
                    "Crop image is required"
            );
        }

        if (image.getSize() > 10 * 1024 * 1024) {
            throw new IllegalArgumentException(
                    "Image must be <= 10 MB"
            );
        }

        String contentType = image.getContentType();

        if (contentType == null) {
            throw new IllegalArgumentException(
                    "Image content type is missing"
            );
        }

        boolean valid =
                contentType.equals("image/jpeg")
                        || contentType.equals("image/png")
                        || contentType.equals("image/webp");

        if (!valid) {
            throw new IllegalArgumentException(
                    "Only JPEG, PNG and WEBP images are supported"
            );
        }
    }
}