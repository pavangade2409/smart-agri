package com.agri.agent.agent;
import org.springframework.stereotype.Component;
@Component
public class AgentExecutor {
    public String executePlan(String plan){return "Plan selected: "+plan;}
}
