package com.agri.agent.ai;
import com.agri.agent.dto.TreatmentRecommendation;
import com.agri.agent.vision.ImageAnalysisResult;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
@Service
public class TreatmentReasoningService {
 private final ChatClient client;
 public TreatmentReasoningService(ChatClient.Builder b){client=b.build();}
 public String reason(ImageAnalysisResult a,TreatmentRecommendation r,String context,String rag,String language,String question){
  String p="Explain this agricultural result simply in language "+language+". "+
   "Never invent pesticide names, products or doses. Use treatment information only from trusted knowledge. "+
   "Clearly state uncertainty. IMAGE: crop="+a.crop()+", disease="+a.disease()+", pest="+a.pest()+
   ", symptoms="+a.symptoms()+", severity="+a.severity()+", confidence="+a.confidence()+", cause="+a.possibleCause()+
   ". FARM CONTEXT: "+context+". TRUSTED KNOWLEDGE: "+rag+". RECOMMENDATION: "+r+
   ". QUESTION: "+(question==null?"":question);
  return client.prompt().user(p).call().content();
 }
}