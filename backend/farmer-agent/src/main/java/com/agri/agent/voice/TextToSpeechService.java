package com.agri.agent.voice;

public interface TextToSpeechService {

    byte[] synthesize(
            String text,
            String language
    );
}