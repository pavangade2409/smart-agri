package com.agri.agent.rag;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.stereotype.Service;
@Service
public class EmbeddingService {
    private final EmbeddingModel model;
    public EmbeddingService(EmbeddingModel model){this.model=model;}
    public float[] embed(String text){return model.embed(text);}
}
