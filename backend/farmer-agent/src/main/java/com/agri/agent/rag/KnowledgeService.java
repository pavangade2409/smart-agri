package com.agri.agent.rag;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KnowledgeService {
    private final VectorStore vectorStore;
    public KnowledgeService(VectorStore vectorStore){this.vectorStore=vectorStore;}
    public List<Document> search(String query){
        return vectorStore.similaritySearch(SearchRequest.builder()
            .query(query).topK(5).similarityThreshold(0.70).build());
    }
    public String searchAsText(String query){
        List<Document> docs=search(query);
        if(docs==null || docs.isEmpty()) return "No matching agricultural knowledge found.";
        return docs.stream().map(Document::getText).reduce("",(a,b)->a+"\n---\n"+b);
    }
}
