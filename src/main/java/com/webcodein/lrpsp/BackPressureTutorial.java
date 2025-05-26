package com.webcodein.lrpsp;

import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * Demonstrates different backpressure handling strategies using Project Reactor's Flux.
 *
 * This class simulates various publisher-subscriber scenarios, such as:
 * - Controlled emission to avoid backpressure
 * - Uncontrolled fast emission causing overflow
 * - Backpressure strategies: drop, buffer, and drop latest
 */
public class BackPressureTutorial {

    /**
     * Emits integers at a controlled rate (1 item every 100 ms).
     * This simulates a well-behaved publisher that avoids overwhelming the subscriber.
     *
     * @return Flux<Integer> stream with controlled backpressure
     */
    private Flux<Integer> controlledRateEmitter() {
        return Flux.range(1, Integer.MAX_VALUE)
                .log()
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)).thenReturn(x));
    }

    /**
     * Emits items at a very fast rate (1 ms), but each item takes 100 ms to process.
     * This simulates a situation where the publisher outpaces the subscriber, leading to overflow.
     *
     * @return Flux<Long> that overproduces and causes backpressure issues
     */
    private Flux<Long> fastEmitterWithSlowProcessing() {
        return Flux.interval(Duration.ofMillis(1))
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)).thenReturn(x));
    }

    /**
     * Emits items rapidly and applies the `onBackpressureDrop` strategy.
     * Items that cannot be processed in time are dropped.
     *
     * @return Flux<Long> using backpressure drop strategy
     */
    private Flux<Long> dropOverflowedItemsEmitter() {
        return Flux.interval(Duration.ofMillis(1))
                .onBackpressureDrop()
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)).thenReturn(x))
                .doOnNext(item -> System.out.println("Element kept by consumer: " + item));
    }

    /**
     * Emits items rapidly and buffers up to 50 items when under pressure.
     * This allows some overflow but limits memory usage with a fixed buffer.
     *
     * @return Flux<Long> using backpressure buffer strategy
     */
    private Flux<Long> bufferOverflowedItemsEmitter() {
        return Flux.interval(Duration.ofMillis(1))
                .onBackpressureBuffer(50)
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)).thenReturn(x))
                .doOnNext(item -> System.out.println("Element kept by consumer: " + item));
    }

    /**
     * Emits items rapidly and buffers up to 50 items, dropping the latest item on overflow.
     * This strategy prevents new items from filling the buffer when full.
     *
     * @return Flux<Long> using buffer strategy with DROP_LATEST overflow policy
     */
    private Flux<Long> bufferWithDropLatestStrategyEmitter() {
        return Flux.interval(Duration.ofMillis(1))
                .onBackpressureBuffer(50, BufferOverflowStrategy.DROP_LATEST)
                .concatMap(x -> Mono.delay(Duration.ofMillis(100)).thenReturn(x))
                .doOnNext(item -> System.out.println("Element kept by consumer: " + item));
    }

    /**
     * Executes various Flux-based backpressure tests and prints separators for clarity.
     */
    public static void main(String[] args) {
        BackPressureTutorial tutorial = new BackPressureTutorial();

        System.out.println("\n========== Test 1: Controlled Rate Emitter ==========");
        // Uncomment to run
        // tutorial.controlledRateEmitter().blockLast();
        System.out.println("========== End of Test 1 ==========\n");

        System.out.println("\n========== Test 2: Fast Emitter with Slow Processing ==========");
        // Uncomment to run
        // tutorial.fastEmitterWithSlowProcessing().blockLast();
        System.out.println("========== End of Test 2 ==========\n");

        System.out.println("\n========== Test 3: Drop Overflowed Items ==========");
        // Uncomment to run
        // tutorial.dropOverflowedItemsEmitter().blockLast();
        System.out.println("========== End of Test 3 ==========\n");

        System.out.println("\n========== Test 4: Buffer Overflowed Items ==========");
        // Uncomment to run
        // tutorial.bufferOverflowedItemsEmitter().blockLast();
        System.out.println("========== End of Test 4 ==========\n");

        System.out.println("\n========== Test 5: Buffer with DROP_LATEST Strategy ==========");
        tutorial.bufferWithDropLatestStrategyEmitter().blockLast();
        System.out.println("========== End of Test 5 ==========\n");
    }
}