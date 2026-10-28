package com.agri.agent.agent;
import org.springframework.stereotype.Component;
@Component
public class AgentPlanner {
    public String plan(String message){
        String s=message==null?"":message.toLowerCase();
        if(s.contains("water")||s.contains("irrigation")||s.contains("pump")) return "IRRIGATION_ANALYSIS";
        if(s.contains("pest")||s.contains("insect")||s.contains("disease")) return "CROP_HEALTH_ANALYSIS";
        if(s.contains("fertilizer")||s.contains("fertigation")) return "FERTILIZER_ANALYSIS";
        return "GENERAL_FARM_ANALYSIS";
    }
}
