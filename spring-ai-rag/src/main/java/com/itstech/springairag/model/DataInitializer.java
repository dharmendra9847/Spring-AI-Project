package com.itstech.springairag.model;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
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
        TextReader textReader = new TextReader(new ClassPathResource("product_details.txt"));

        TokenTextSplitter splitter = TokenTextSplitter
                .builder()
                .withChunkSize(100)
                .withMinChunkLengthToEmbed(30)
                .withMinChunkSizeChars(20)
                .withKeepSeparator(false)
                .build();

        List<Document> documents
                = splitter.split(textReader.get());

        vectorStore.add(documents);
    }
}
