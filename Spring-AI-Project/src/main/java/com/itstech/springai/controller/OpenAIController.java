package com.itstech.springai.controller;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAIController {

    private OllamaChatModel chatModel;

    @Autowired
    public OpenAIController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/api/{message}")
    public String getAnswer(@PathVariable String message) {

        String response = chatModel.call(message);
        return response;
    }
}
