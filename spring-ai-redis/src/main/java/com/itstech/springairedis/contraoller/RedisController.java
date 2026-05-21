package com.itstech.springairedis.contraoller;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RedisController {

    private VectorStore vectorStore;

    @Autowired
    public RedisController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostMapping("/api/product")
    public List<Document> getProducts(@RequestParam String text) {

        return vectorStore.similaritySearch(text);
    }

}
