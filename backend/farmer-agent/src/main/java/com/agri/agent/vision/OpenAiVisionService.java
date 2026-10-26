package com.agri.agent.vision;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.content.Media;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class OpenAiVisionService implements CropVisionService {

    private final ChatClient client;
    private final ObjectMapper mapper;

    public OpenAiVisionService(
            ChatClient.Builder builder,
            ObjectMapper mapper) {

        this.client = builder.build();
        this.mapper = mapper;
    }

    @Override
    public ImageAnalysisResult analyze(
            byte[] image,
            String contentType,
            String crop,
            String growthStage,
            String question) {

        String prompt = """
                You are an agricultural crop-image analysis AI.

                Crop: %s
                Growth Stage: %s
                Farmer Question: %s

                Analyze the uploaded crop image.

                Identify:
                1. Crop
                2. Disease
                3. Pest
                4. Visible symptoms
                5. Severity
                6. Confidence from 0.0 to 1.0
                7. Possible cause

                Severity must be one of:
                LOW, MEDIUM, HIGH, UNKNOWN

                If the image is unclear, use UNKNOWN.

                Do NOT recommend pesticides.
                Do NOT provide chemical names.
                Do NOT provide pesticide doses.

                Return ONLY valid JSON:

                {
                  "crop": "...",
                  "disease": "...",
                  "pest": "...",
                  "symptoms": "...",
                  "severity": "LOW",
                  "confidence": 0.0,
                  "possibleCause": "..."
                }
                """.formatted(
                crop,
                growthStage == null ? "Unknown" : growthStage,
                question == null ? "" : question
        );

        ByteArrayResource imageResource =
                new ByteArrayResource(image) {
                    @Override
                    public String getFilename() {
                        return "crop-image";
                    }
                };

        Media media = new Media(
                MimeTypeUtils.parseMimeType(contentType),
                imageResource
        );

        UserMessage message = UserMessage.builder()
                .text(prompt)
                .media(media)
                .build();

        try {

            String response = client
                    .prompt()
                    .messages(message)
                    .call()
                    .content();

            return mapper.readValue(
                    cleanJson(response),
                    ImageAnalysisResult.class
            );

        } catch (Exception e) {

            throw new IllegalStateException(
                    "Failed to analyze crop image using Vision AI",
                    e
            );
        }
    }

    private String cleanJson(String response) {

        if (response == null) {
            throw new IllegalStateException(
                    "Vision AI returned an empty response"
            );
        }

        String result = response.trim();

        if (result.startsWith("```json")) {
            result = result.substring(7);
        } else if (result.startsWith("```")) {
            result = result.substring(3);
        }

        if (result.endsWith("```")) {
            result = result.substring(
                    0,
                    result.length() - 3
            );
        }

        return result.trim();
    }
}