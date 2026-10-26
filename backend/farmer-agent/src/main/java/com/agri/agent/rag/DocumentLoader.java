package com.agri.agent.rag;
import org.springframework.stereotype.Component;
@Component
public class DocumentLoader {
    public String load(String source){return source==null?"":source;}
}
