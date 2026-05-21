package com.itstech.springairag.contraoller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RagController {

    private VectorStore vectorStore;
    private OllamaChatModel chatModel;

    private ChatClient chatClient;

    @Autowired
    public RagController(VectorStore vectorStore, OllamaChatModel chatModel) {
        this.vectorStore = vectorStore;
        this.chatModel = chatModel;
        this.chatClient = ChatClient.create(chatModel);
    }

    @PostMapping("/api/ask")
    public String getAnswerUsingRag(@RequestParam String query) {

        return chatClient
                .prompt()
                .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                .user(query)
                .call()
                .content();

    }
}
