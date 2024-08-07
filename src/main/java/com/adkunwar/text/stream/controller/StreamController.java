package com.adkunwar.text.stream.controller;

import com.adkunwar.text.stream.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/stream")
public class StreamController {
    private final ProviderService providerService;

    @Autowired
    public StreamController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping("/switch-provider")
    public Mono<Void> switchProvider(@RequestParam String provider) {
        return Mono.fromRunnable(() -> providerService.switchProvider(provider));
    }

    @GetMapping("/text")
    public Flux<String> streamText(@RequestParam String text) {
        return Flux.just(text)
                .map(providerService::processText)
                .delayElements(Duration.ofSeconds(1)); // Simulate streaming delay
    }
}
