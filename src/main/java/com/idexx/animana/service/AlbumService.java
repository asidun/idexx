package com.idexx.animana.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static java.time.Duration.ofSeconds;

@Service
@Slf4j
public class AlbumService {

    private static final String TYPE = "album";

    @Value("${endpoint.response.timeout}")
    private int endpointResponseTimeout;

    @Value("${search.limit}")
    private int searchLimit;

    @Value("${itunes.album.endpoint}")
    private String itunesAlbumEndpoint;

    private final WebClient.Builder webClientBuilder;

    public AlbumService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<String> getAlbums(String searchTerm){
        log.debug("Call Itunes Service. SearchTerm: {}", searchTerm);
       return webClientBuilder.build()
                .get()
                .uri(itunesAlbumEndpoint +"?term={term}&limit={limit}&entity={type}", searchTerm, searchLimit, TYPE)
                .accept(MediaType.ALL)
                .retrieve()
                .bodyToMono(String.class)
                .timeout(ofSeconds(endpointResponseTimeout));
    }
}
