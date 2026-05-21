package com.itstech.openaiimagemodel.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OpenAIImageModelController {

    private ChatClient chatClient;
    private OpenAiImageModel openAiImageModel;

    public OpenAIImageModelController (OpenAiImageModel openAiImageModel, ChatClient.Builder builder) {
        this.openAiImageModel = openAiImageModel;
        this.chatClient = builder.build();
    }

    @GetMapping("/image/{query}")
    public String getImage(@PathVariable String query) {

        ImagePrompt prompt =
                new ImagePrompt(query, OpenAiImageOptions.builder()
                        .quality("hd")
                        .height(1024)
                        .width(1024)
                        .N(1)
                        .style("natural")
                        .build());
        ImageResponse response = openAiImageModel.call(prompt);
        return response.getResult().getOutput().getUrl();
    }

    @PostMapping("/image/describe")
    public String describeImage(@RequestParam String query, @RequestParam MultipartFile image) {
        //System.out.println(image.getOriginalFilename());
        //System.out.println(query);

        return chatClient.prompt()
                .user(us -> us.text(query)
                        .media(MimeTypeUtils.IMAGE_JPEG, image.getResource()))
                .call()
                .content();
    }

}
