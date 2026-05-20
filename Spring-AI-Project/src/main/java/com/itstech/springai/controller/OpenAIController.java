package com.itstech.springai.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OpenAIController {

    private final EmbeddingModel embeddingModel;
    private OllamaChatModel chatModel;
    private VectorStore vectorStore;

    @Autowired
    public OpenAIController(OllamaChatModel chatModel, EmbeddingModel embeddingModel, VectorStore vectorStore) {
        this.chatModel = chatModel;
        this.embeddingModel = embeddingModel;
        this.vectorStore = vectorStore;
    }

    @GetMapping("/api/{message}")
    public String getAnswer(@PathVariable String message) {

        String response = chatModel.call(message);
        return response;
    }

    @PostMapping("/api/similarity")
    public double getSimilarity(@RequestParam String text1, @RequestParam String text2) {

        float[] embed1 = embeddingModel.embed(text1);
        float[] embed2 = embeddingModel.embed(text2);

        // FIND SIMILARITY FORMULA using Embedding Model
        double dotProduct = 0;
        double norm1 = 0;
        double norm2 = 0;

        for (int i = 0; i < embed1.length; i++) {
            dotProduct += embed1[i] * embed2[i];
            norm1 += Math.pow(embed1[i], 2);
            norm2 += Math.pow(embed2[i], 2);
        }
        return dotProduct / Math.sqrt(norm1) * Math.sqrt(norm2);
    }

    @PostMapping("/api/product")
    public List<Document> getProduct(@RequestParam String text) {
        //return vectorStore.similaritySearch(text);
        return vectorStore.similaritySearch(SearchRequest.builder().query(text).topK(2).build());
    }
}
