package com.agri.agent.ai;

import com.agri.agent.dto.FarmContext;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class FarmerAgentAiServiceTest {

    private final FarmerAiService service = new FarmerAiService(
            mock(ChatClient.class), new PromptService());

    @Test
    void irrigationQuestionGetsDirectIrrigationFallback() {
        String answer = service.directFallback("Should I irrigate my cotton farm today?");
        assertTrue(answer.toLowerCase().contains("soil moisture"));
        assertTrue(answer.toLowerCase().contains("irrigat"));
    }

    @Test
    void pestQuestionGetsCropInspectionFallback() {
        String answer = service.directFallback("What disease is affecting the leaves?");
        assertTrue(answer.toLowerCase().contains("inspect"));
        assertTrue(answer.toLowerCase().contains("disease"));
    }

    @Test
    void fertilizerQuestionWarnsAgainstUsingOldSoilData() {
        String answer = service.directFallback("Which fertilizer for my soil NPK values?");
        assertTrue(answer.toLowerCase().contains("old soil test"));
        assertTrue(answer.toLowerCase().contains("npk"));
    }

    @Test
    void weatherQuestionUsesWeatherConditions() {
        String answer = service.directFallback("What is the weather forecast?");
        assertTrue(answer.toLowerCase().contains("rainfall"));
        assertTrue(answer.toLowerCase().contains("forecast"));
    }

    @Test
    void unknownQuestionRequestsRelevantFarmDetails() {
        String answer = service.directFallback("Tell me about my farm");
        assertTrue(answer.toLowerCase().contains("crop"));
        assertTrue(answer.toLowerCase().contains("growth stage"));
    }

    @Test
    void promptIncludesTheActualCustomerQuestion() {
        PromptService prompts = new PromptService();
        FarmContext context = new FarmContext(1L, 10L, null,
                "farm data", "crop data", "sensor unavailable",
                "weather unavailable", "history");

        String prompt = prompts.user(context, "knowledge", "Should I irrigate today?");

        assertTrue(prompt.contains("FARMER QUESTION"));
        assertTrue(prompt.contains("Should I irrigate today?"));
        assertTrue(prompt.contains("Answer the farmer's question directly"));
    }

    @Test
    void systemPromptDoesNotAllowInventedMeasurements() {
        String system = new PromptService().system();

        assertTrue(system.contains("never substitute guesses"));
        assertTrue(system.contains("Do not claim that sensor readings"));
        assertTrue(system.contains("Never claim a pump or valve operated"));
        assertFalse(system.contains("guess available values"));
    }
}
