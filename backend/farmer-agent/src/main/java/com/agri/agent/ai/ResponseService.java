package com.agri.agent.ai;
import org.springframework.stereotype.Service;
@Service
public class ResponseService {
    public String clean(String response){return response==null?"No response generated.":response.trim();}
}
