package com.agri.agent.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class FarmerAiService {
    private final ChatClient client;
    private final PromptService prompts;

    public FarmerAiService(ChatClient client, PromptService prompts) {
        this.client = client;
        this.prompts = prompts;
    }

    public String ask(String prompt, String question) {
        try {
            String answer = client.prompt()
                    .system(prompts.system())
                    .user(prompt)
                    .call()
                    .content();
            if (answer != null && !answer.isBlank()) {
                return answer.trim();
            }
        } catch (RuntimeException ignored) {
            // Provide a helpful response instead of failing the whole request.
        }
        return directFallback(question);
    }

    String directFallback(String question) {
        String lower = question == null ? "" : question.toLowerCase();
        if (lower.contains("irrigat") || lower.contains("water") || lower.contains("pump") || lower.contains("rain")) {
            return "Check the soil moisture in the root zone before irrigating. If the soil is dry, irrigate evenly and water early in the day; avoid irrigation if significant rain is expected. Use the latest farm sensor and weather readings for a final decision.";
        }
        if (lower.contains("pest") || lower.contains("insect") || lower.contains("disease") || lower.contains("leaf") || lower.contains("crop health")) {
            return "Inspect affected leaves, stems, and the underside of leaves, then compare the symptoms across the healthy and affected plants. Avoid spraying without identifying the pest or disease; isolate severely affected plants and use the crop-specific recommended treatment when available.";
        }
        if (lower.contains("fertiliz") || lower.contains("npk") || lower.contains("nutrient") || lower.contains("soil")) {
            return "Do not apply fertilizer based only on symptoms or an old soil test. Check the crop type, soil test date, source, sampling stage, units, pH, and measured NPK/EC values before choosing a treatment.";
        }
        if (lower.contains("weather")) {
            return "Use the latest temperature, rainfall, humidity, wind, and forecast data before scheduling field work or irrigation. Avoid spraying or harvesting during unsuitable wind, heat, or rain conditions.";
        }
        return "I can help with that agricultural question. Please provide the crop, farm location or weather context, crop growth stage, symptoms or measurements, and the timing of the issue so I can give a specific answer.";
    }
}
