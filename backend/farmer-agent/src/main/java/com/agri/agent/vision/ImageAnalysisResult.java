package com.agri.agent.vision;
public record ImageAnalysisResult(String crop,String disease,String pest,String symptoms,String severity,double confidence,String possibleCause) {}