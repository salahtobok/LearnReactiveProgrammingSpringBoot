package com.webcodein.lrpsp;

import org.springframework.boot.SpringApplication;
import reactor.core.publisher.Mono;

public class ReactiveTutorial {

    // 01 : First example
    private Mono<String> toMono(){
        return Mono.just("Hello World");
    }

    // 01 : Second example
    private Mono<String> toMonoWithLog(){
        return Mono.just("Hello World")
                .log();
    }

    public static void main(String[] args) {
        ReactiveTutorial reactiveTutorial = new ReactiveTutorial();

        // 01 : First example
        // 01 : First example | It won't display anything
        reactiveTutorial.toMono();
        // 01 : First example | It won't display anything
        reactiveTutorial.toMono().subscribe();
        // 01 : First example | It'll print " Hello World "
        reactiveTutorial.toMono().subscribe(System.out::println);

        // 02 : Second example
        // 02 : Second example | It'll print " Hello World " with log
        reactiveTutorial.toMonoWithLog().subscribe(System.out::println);
    }

}
