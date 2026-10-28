package com.agri.agent.voice;

public interface SpeechToTextService {

    String transcribe(
            byte[] audio,
            String fileName,
            String language
    );
}