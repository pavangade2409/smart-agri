package com.agri.agent.memory;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ConversationMemoryService {
    private final Map<String,StringBuilder> memory=new ConcurrentHashMap<>();
    public void add(String id,String role,String message){
        memory.computeIfAbsent(id,k->new StringBuilder())
            .append(role).append(": ").append(message).append("\n");
    }
    public String get(String id){
        return memory.getOrDefault(id,new StringBuilder()).toString();
    }
}
