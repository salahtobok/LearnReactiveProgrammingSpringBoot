package com.webcodein.lrpsp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class BackPresureTutorial {

    private Flux<Long> createNoOverflowFlux(){
        return Flux.range(1,Integer.MAX_VALUE)
                .log()
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)));
    }


    private Flux<Long> createOverflowFlux(){
        return Flux.interval(Duration.ofMillis(1))
                .log()
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)));
    }


    public static void main(String[] args) {
        BackPresureTutorial btp = new BackPresureTutorial();
        btp.createNoOverflowFlux()
                .blockLast();
    }
}
