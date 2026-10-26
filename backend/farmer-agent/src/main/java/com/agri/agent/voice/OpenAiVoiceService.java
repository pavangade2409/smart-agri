package com.agri.agent.voice;

import org.springframework.stereotype.Service;

@Service
public class OpenAiVoiceService {

    private final SpeechToTextService stt;
    private final TextToSpeechService tts;

    public OpenAiVoiceService(
            SpeechToTextService stt,
            TextToSpeechService tts) {

        this.stt = stt;
        this.tts = tts;
    }

    public String transcribe(
            byte[] audio,
            String fileName,
            String language) {

        return stt.transcribe(
                audio,
                fileName,
                language
        );
    }

    public byte[] speak(
            String text,
            String language) {

        return tts.synthesize(
                text,
                language
        );
    }
}