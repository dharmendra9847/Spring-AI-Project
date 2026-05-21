package com.itstech.openaiaudiomodels.controller;

import com.openai.models.audio.AudioResponseFormat;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OpenAIAudioGenController {

    private OpenAiAudioTranscriptionModel audioModel;
    private OpenAiAudioSpeechModel speechModel;

    public OpenAIAudioGenController(OpenAiAudioTranscriptionModel audioModel, OpenAiAudioSpeechModel speechModel) {
        this.audioModel = audioModel;
        this.speechModel = speechModel;
    }

    @PostMapping("api/stt")
    public String speechToText(@RequestParam MultipartFile file) {

        OpenAiAudioTranscriptionOptions options = OpenAiAudioTranscriptionOptions.builder()
                .language("es")
                .responseFormat(AudioResponseFormat.VTT)
                .build();

        AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(file.getResource(), options);

        return audioModel.call(prompt).getResult().getOutput();
    }

    @PostMapping("api/tts")
    public byte[] textToSpeech(@RequestParam String text) {

        OpenAiAudioSpeechOptions options = OpenAiAudioSpeechOptions.builder()
                .speed(1.25d)
                .voice(OpenAiAudioSpeechOptions.Voice.NOVA)
                .build();

        TextToSpeechPrompt prompt = new TextToSpeechPrompt(text, options);

        return speechModel.call(prompt).getResult().getOutput();
    }
}
