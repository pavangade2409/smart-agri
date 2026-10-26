package com.agri.agent.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class KnowledgeIngestionService {
    private final VectorStore vectorStore;
    public KnowledgeIngestionService(VectorStore vectorStore){this.vectorStore=vectorStore;}

    @PostConstruct
    public void loadInitialKnowledge(){
        try {
            vectorStore.add(List.of(
                new Document("Cotton irrigation should consider crop stage, soil moisture, rainfall forecast and field condition.",
                    Map.of("crop","cotton","category","irrigation")),
                new Document("Cotton whitefly risk can increase under suitable warm and dry conditions. Monitor leaves and use integrated pest management.",
                    Map.of("crop","cotton","category","pest")),
                new Document("Avoid irrigation when sufficient rainfall is expected and soil moisture is already adequate. Confirm sensor data before automation.",
                    Map.of("crop","general","category","water-management"))
            ));
        } catch(Exception ignored) {}
    }
}
