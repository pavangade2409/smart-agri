package com.agri.agent.rag;
import org.springframework.ai.document.Document;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class VectorSearchService {
    private final KnowledgeService service;
    public VectorSearchService(KnowledgeService service){this.service=service;}
    public List<Document> search(String query){return service.search(query);}
}
