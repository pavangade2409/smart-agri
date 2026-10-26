package com.agri.agent.ai;
import com.agri.agent.vision.*;
import org.springframework.stereotype.Service;
@Service
public class VisionAiService {
 private final CropVisionService service;
 public VisionAiService(CropVisionService s){service=s;}
 public ImageAnalysisResult analyze(byte[] image,String type,String crop,String stage,String question){return service.analyze(image,type,crop,stage,question);}
}