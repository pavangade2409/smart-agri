package com.agri.agent.voice;

import org.springframework.stereotype.Service;

@Service
public class DefaultTextToSpeechService implements TextToSpeechService {

    @Override
    public byte[] synthesize(
            String text,
            String language) {

        if (text == null || text.isBlank()) {
            return new byte[0];
        }

        return new byte[0];
    }
}