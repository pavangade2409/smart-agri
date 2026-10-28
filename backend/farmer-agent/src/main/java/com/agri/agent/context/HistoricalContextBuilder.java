package com.agri.agent.context;

import com.agri.agent.repository.FarmEventRepository;
import org.springframework.stereotype.Component;

@Component
public class HistoricalContextBuilder {
    private final FarmEventRepository repository;
    public HistoricalContextBuilder(FarmEventRepository repository){this.repository=repository;}
    public String build(Long farmId){
        return repository.findTop20ByFarmIdOrderByEventTimeDesc(farmId).stream()
            .map(e -> e.getEventTime()+" | "+e.getEventType()+" | "+e.getEventData())
            .reduce("", (a,b)->a+"\n"+b);
    }
}
