package com.itstech.soc.controller;

import com.itstech.soc.model.Movie;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    private ChatClient chatClient;

    public MovieController(OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @PostMapping("/api/recommend")
    public String recommend(@RequestParam String type, @RequestParam String year, @RequestParam String lang) {

        String temp = """
                I want to watch a {type} movie tonight with good rating, 
                looking  for movies around this year {year}. 
                The  language im looking for is {lang}.
                Suggest one specific movie and tell me the cast and length of the movie.
               
                response format should be:
                1. Movie Name
                2. basic plot
                3. cast
                4. length
                5. IMDB rating
               
                """;
        PromptTemplate promptTemplate = new PromptTemplate(temp);


        Prompt prompt = promptTemplate.create(Map.of(
                "type", type,
                "year", year,
                "lang", lang
        ));


        String response = chatClient
                .prompt(prompt)
                .call()
                .content();
        return response;
    }

    @GetMapping("movies")
    public List<String> getMovies(@RequestParam String name) {
        String message = """
                    List Top 5 movies of {name}
                    {format}
                """;

        ListOutputConverter converter = new ListOutputConverter(new DefaultConversionService());

        PromptTemplate template =
                new PromptTemplate(message,
                        Map.of("name", name, "format", converter.getFormat()));
        Prompt prompt = template.create();
        List<String> movies = converter.convert(chatClient.prompt(prompt).call().content());

        return movies;
    }

    @GetMapping("/movie")
    public Movie getMovieData(@RequestParam String name) {

        String message = """
                    Get me the best movie of {name}
                    {format}
                """;
        BeanOutputConverter<Movie> converter = new BeanOutputConverter<>(Movie.class);
        PromptTemplate template =
                new PromptTemplate(message,
                        Map.of("name", name, "format", converter.getFormat()));
        Prompt prompt = template.create();
        Movie movie = converter.convert(chatClient.prompt(prompt).call().content());
        return movie;
    }

    @GetMapping("/movieslist")
    public List<Movie> getMoviesList(@RequestParam String name) {

        String message = """
                    Top 5 movies of {name}
                    {format}
                """;
        BeanOutputConverter<List<Movie>> converter = new BeanOutputConverter<>(
                new ParameterizedTypeReference<List<Movie>>() {}
        );
        PromptTemplate template =
                new PromptTemplate(message,
                        Map.of("name", name, "format", converter.getFormat()));
        Prompt prompt = template.create();
        List<Movie> movieList = converter.convert(chatClient.prompt(prompt).call().content());
        return movieList;
    }
}
