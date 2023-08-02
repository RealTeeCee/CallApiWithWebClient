package com.feignclient.feignclientapp.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.feignclient.feignclientapp.model.BaseDto;
import com.feignclient.feignclientapp.model.TagDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {
    private final WebClient webClient;

    public WebClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Flux<TagDto> findAll() {
        return webClient.get()
                .uri("/tag")
                .retrieve()
                .bodyToFlux(TagDto.class);
    }

    public Mono<BaseDto> create(TagDto tagDto) {
        return webClient.post()
                .uri("/tag")
                .bodyValue(tagDto)
                .retrieve().bodyToMono(BaseDto.class);
               

    }
};