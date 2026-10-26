package com.agri.agent.voice;

import org.springframework.stereotype.Service;

@Service
public class DefaultSpeechToTextService implements SpeechToTextService {

    @Override
    public String transcribe(
            byte[] audio,
            String fileName,
            String language) {

        if (audio == null || audio.length == 0) {
            return "No audio data received.";
        }

        return "Speech-to-text is not configured yet.";
    }
}