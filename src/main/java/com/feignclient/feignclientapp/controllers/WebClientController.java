package com.feignclient.feignclientapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.feignclient.feignclientapp.api.TagApiDelegate;
import com.feignclient.feignclientapp.model.BaseDto;
import com.feignclient.feignclientapp.model.TagDto;
import com.feignclient.feignclientapp.services.WebClientService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class WebClientController implements TagApiDelegate {
    private final WebClientService webClientService;

    @Override
    public Mono<ResponseEntity<BaseDto>> create(Mono<TagDto> tagDto, ServerWebExchange exchange) {
        return tagDto.flatMap(tag -> webClientService.create(tag).flatMap(res -> Mono.just(ResponseEntity.ok(res))));
        
    }

    @Override
    public Mono<ResponseEntity<Flux<TagDto>>> getAllTags(ServerWebExchange exchange) {
        Flux<TagDto> findAll = webClientService.findAll();
        return Mono.just(ResponseEntity.ok(findAll));
    }

}
