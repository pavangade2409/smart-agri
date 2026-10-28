package com.agri.agent.agent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgentPlannerTest {
    private final AgentPlanner planner = new AgentPlanner();

    @Test
    void irrigationQuestionSelectsIrrigationAnalysis() {
        assertEquals("IRRIGATION_ANALYSIS", planner.plan("Should I water the cotton field?"));
    }

    @Test
    void cropDiseaseQuestionSelectsCropHealthAnalysis() {
        assertEquals("CROP_HEALTH_ANALYSIS", planner.plan("What disease affects the leaves?"));
    }

    @Test
    void fertilizerQuestionSelectsFertilizerAnalysis() {
        assertEquals("FERTILIZER_ANALYSIS", planner.plan("Which fertilizer should I apply?"));
    }

    @Test
    void unrelatedFarmQuestionUsesGeneralAnalysis() {
        assertEquals("GENERAL_FARM_ANALYSIS", planner.plan("When should I harvest this crop?"));
    }
}
