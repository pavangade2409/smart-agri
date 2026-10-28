package com.agri.agent.controller;
import com.agri.agent.voice.OpenAiVoiceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@RestController
@RequestMapping("/api/farmer-agent")
public class VoiceController {
 private final OpenAiVoiceService voice;
 public VoiceController(OpenAiVoiceService v){voice=v;}
 @PostMapping(value="/voice/transcribe",consumes="multipart/form-data")
 public String transcribe(@RequestPart("audio") MultipartFile audio,@RequestParam(defaultValue="en") String language)throws IOException{
  if(audio.isEmpty())throw new IllegalArgumentException("Audio is required");
  return voice.transcribe(audio.getBytes(),audio.getOriginalFilename(),language);
 }
}