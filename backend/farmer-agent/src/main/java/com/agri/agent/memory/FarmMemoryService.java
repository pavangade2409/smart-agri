package com.agri.agent.memory;

import com.agri.agent.repository.*;
import org.springframework.stereotype.Service;

@Service
public class FarmMemoryService {
    private final FarmEventRepository events;
    private final ConversationRepository conversations;

    public FarmMemoryService(FarmEventRepository events, ConversationRepository conversations){
        this.events=events; this.conversations=conversations;
    }

    public void saveEvent(Long farmerId, Long farmId, Long plotId, String type, String data){
        FarmEvent e=new FarmEvent();
        e.setFarmerId(farmerId); e.setFarmId(farmId); e.setPlotId(plotId);
        e.setEventType(type); e.setEventData(data);
        events.save(e);
    }

    public void saveConversation(String id, Long farmerId, Long farmId){
        if(conversations.findByConversationId(id).isPresent()) return;
        Conversation c=new Conversation();
        c.setConversationId(id); c.setFarmerId(farmerId); c.setFarmId(farmId);
        conversations.save(c);
    }
}
