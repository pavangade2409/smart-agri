package com.agri.agent.dto;
import java.util.List;
public record TreatmentRecommendation(String problem,String cause,List<String> immediateActions,String activeIngredient,String product,String dose,String applicationMethod,String safetyInstructions,List<String> recoveryPlan) {}