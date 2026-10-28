package com.agri.agent.vision;
public interface CropVisionService {
 ImageAnalysisResult analyze(byte[] image,String contentType,String crop,String growthStage,String question);
}