package com.webcodein.lrpsp;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class FluxDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Concat Demo ===");
        fluxWithConcatenatedRanges()
                .subscribe(i -> System.out.println("Concat: " + i));

        // Wait enough time for concat to finish
        Thread.sleep(30000);

        System.out.println("=== Merge Demo ===");
        fluxWithMergedRanges()
                .subscribe(i -> System.out.println("Merge: " + i));

        // Wait enough time for merge to finish
        Thread.sleep(10000);
    }

    public static Flux<Integer> fluxWithConcatenatedRanges() {
        Flux<Integer> flux1 = Flux.range(1, 5)
                .delayElements(Duration.ofMillis(5000))
                .subscribeOn(Schedulers.parallel());

        Flux<Integer> flux2 = Flux.range(10, 5)
                .delayElements(Duration.ofMillis(200))
                .subscribeOn(Schedulers.parallel());

        return Flux.concat(flux1, flux2);
    }

    public static Flux<Integer> fluxWithMergedRanges() {
        Flux<Integer> flux1 = Flux.range(1, 5)
                .delayElements(Duration.ofMillis(5000))
                .subscribeOn(Schedulers.parallel());

        Flux<Integer> flux2 = Flux.range(10, 5)
                .delayElements(Duration.ofMillis(200))
                .subscribeOn(Schedulers.parallel());

        return Flux.merge(flux1, flux2);
    }
}