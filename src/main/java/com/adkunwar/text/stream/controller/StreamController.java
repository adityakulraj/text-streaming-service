package com.adkunwar.text.stream.controller;

import com.adkunwar.text.stream.SwitchStrategy;
import com.adkunwar.text.stream.impl.HttpHeaderSwitchStrategy;
import com.adkunwar.text.stream.service.ProviderService;
import com.adkunwar.text.stream.SwitchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/api/stream")
public class StreamController {
    private final ProviderService providerService;


    @Autowired
    public StreamController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @PostMapping("/switch-provider")
    public Mono<Void> switchProvider(@RequestParam String provider, @RequestHeader Map<String, String> headers) {
        return Mono.fromRunnable(() -> providerService.switchProvider(provider));
    }

    @GetMapping("/text")
    public Flux<String> streamText(@RequestParam String text, @RequestHeader Map<String, String> headers) {

        Tuple2<String, Map<String, String>> combined = Tuples.of(text, headers);

        return Flux.just(combined)
                .map(tuple -> providerService.processText(tuple.getT1(), tuple.getT2()))
                .delayElements(Duration.ofSeconds(1));
    }
}
