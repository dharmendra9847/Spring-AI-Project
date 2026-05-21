package com.itstech.springai.model;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {

    private VectorStore vectorStore;

    @Autowired
    public DataInitializer(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void initData() {

        // Check if data already exists
        List<Document> existing = vectorStore.similaritySearch(
                SearchRequest.builder().query("product").topK(1).build()
        );
        if (!existing.isEmpty()) {
            System.out.println("Vector store already populated, skipping data load.");
            return;
        }

        TextReader textReader =
                new TextReader(
                    new ClassPathResource("product_details.txt"));

        // READ DOCUMENTS
        List<Document> documents = textReader.get();

        // SPLIT DOCUMENTS
        TokenTextSplitter splitter = TokenTextSplitter
                .builder()
                .withChunkSize(100)
                .withMinChunkSizeChars(30)
                .withMinChunkLengthToEmbed(5)
                .withMaxNumChunks(500)
                .withKeepSeparator(false)
                .build();

        List<Document> splitDocuments = splitter.apply(documents);

        // STORE IN VECTOR DB
        vectorStore.add(splitDocuments);

        System.out.println("Documents Loaded Into Vector Store");
    }
}
