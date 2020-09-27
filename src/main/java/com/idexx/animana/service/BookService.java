package com.idexx.animana.service;

import com.idexx.animana.model.BookResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.time.Duration.ofSeconds;

@Service
@Slf4j
public class BookService {

    @Value("${endpoint.response.timeout}")
    private int endpointResponseTimeout;

    @Value("${search.limit}")
    private int searchLimit;

    @Value("${google.books.endpoint}")
    private String googleBookEndpoint;

    private final WebClient.Builder webClientBuilder;

    public BookService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<BookResult> getBooks(String searchTerm){
        log.debug("Call Google Service. SearchTerm: {}", searchTerm);
        return webClientBuilder.build()
                .get()
                .uri(googleBookEndpoint+"?q={term}&maxResults={limit}", searchTerm, searchLimit)
                .retrieve()
                .bodyToMono(BookResult.class)
                .timeout(ofSeconds(endpointResponseTimeout));
    }
}
