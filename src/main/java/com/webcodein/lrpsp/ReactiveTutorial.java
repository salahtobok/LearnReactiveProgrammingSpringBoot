package com.webcodein.lrpsp;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Demonstrates various features of Project Reactor using Mono and Flux,
 * including transformations, buffering, merging, zipping, logging, and error handling.
 */
public class ReactiveTutorial {

    /**
     * Prints a visual separator with a given section title to the console.
     *
     * @param title Title of the section to be displayed.
     */
    public static void printSeparator(String title) {
        System.out.println("\n===== [ " + title + " ] =====");
    }

    public static void main(String[] args) throws InterruptedException {
        ReactiveTutorial tutorial = new ReactiveTutorial();

        printSeparator("Mono: Emit Hello World");
        tutorial.createHelloWorldMono().subscribe(System.out::println);

        printSeparator("Mono: Hello World with Logging");
        tutorial.createHelloWorldMonoWithLogging().subscribe(System.out::println);

        printSeparator("Mono: Null Mono");
        tutorial.createNullMono().subscribe(System.out::println);

        printSeparator("Mono: Empty Mono");
        tutorial.createEmptyMono().subscribe(System.out::println);

        printSeparator("Flux: Sample String Values");
        tutorial.getSampleStringFlux().subscribe(System.out::println);

        printSeparator("Flux: Programming Languages");
        tutorial.getProgrammingLanguagesFlux().subscribe(System.out::println);

        printSeparator("Flux: Convert Languages to Uppercase");
        tutorial.uppercaseProgrammingLanguages().subscribe(System.out::println);

        printSeparator("Flux: Uppercase Sample Strings with flatMap");
        tutorial.uppercaseSampleStringsUsingFlatMap().subscribe(System.out::println);

        printSeparator("Flux: Skip First Two Sample Strings");
        tutorial.skipFirstTwoSampleStrings().subscribe(System.out::println);

        printSeparator("Flux: Delay Languages by 1 Second");
        tutorial.delayLanguagesByOneSecond().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Log Delayed Languages");
        tutorial.logDelayedLanguages().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Skip Items in First 2 Seconds");
        tutorial.skipItemsInFirstTwoSeconds().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Skip Until Divisible by 5");
        tutorial.skipUntilDivisibleByFive().subscribe(System.out::println);

        printSeparator("Flux: Concatenate Two Ranges");
        tutorial.concatIntegerRanges().subscribe(System.out::println);

        printSeparator("Flux: Merge Two Ranges Concurrently");
        tutorial.mergeIntegerRanges().subscribe(System.out::println);

        printSeparator("Flux: Zip Two Delayed Ranges");
        tutorial.zipTwoDelayedRanges().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Mono: Collect Delayed Integers to List");
        tutorial.collectDelayedIntegersAsList().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Mono: Collect Immediate Integers to List");
        List<Integer> immediateList = tutorial.collectImmediateIntegersAsList().block();
        System.out.println(immediateList);

        printSeparator("Flux: Buffer Entire Range");
        tutorial.bufferFullIntegerRange().subscribe(System.out::println);

        printSeparator("Flux: Buffer Every 3 Items");
        tutorial.bufferEveryThreeItems().subscribe(System.out::println);

        printSeparator("Flux: Buffer 3 Items with Delay");
        tutorial.bufferThreeItemsWithDelay().subscribe(System.out::println);

        printSeparator("Flux: Buffer Items Every 1 Second");
        tutorial.bufferItemsEverySecond().subscribe(System.out::println);

        printSeparator("Flux: Buffer Items Every 4 Seconds with Delay");
        tutorial.bufferItemsWithFourSecondDelay().subscribe(System.out::println);
        Thread.sleep(25_000);

        printSeparator("Mono: Generate Map of Squares");
        tutorial.createMapOfSquares().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Log All Signals");
        tutorial.logEachSignal().subscribe();
        Thread.sleep(10_000);

        printSeparator("Flux: Log Signal Type and Completion");
        tutorial.logSignalTypeAndCompletion().subscribe();
        Thread.sleep(10_000);

        printSeparator("Flux: Log Completion Only");
        tutorial.logOnlyOnComplete().subscribe();
        Thread.sleep(10_000);

        printSeparator("Flux: Log with DoOnNext");
        tutorial.logValuesWithDoOnNext().subscribe();
        Thread.sleep(10_000);

        printSeparator("Flux: Log on Subscribe");
        tutorial.logOnSubscribe().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Log on Cancel");
        Disposable disposable = tutorial.logOnCancel().subscribe(System.out::println);
        Thread.sleep(4_500);
        disposable.dispose();

        printSeparator("Flux: Error Handling - No Recovery");
        tutorial.errorWithoutRecovery().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Error Handling - onErrorContinue");
        tutorial.errorHandlingWithOnErrorContinue().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Error Handling - onErrorReturn");
        tutorial.errorHandlingWithOnErrorReturn().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Error Handling - onErrorResume with Fallback Flux");
        tutorial.errorHandlingWithFallbackFlux().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Error Handling - onErrorResume with Mono");
        tutorial.errorHandlingWithFallbackMono().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Error Handling - onErrorMap");
        tutorial.errorHandlingWithMappedError().subscribe(System.out::println);
        Thread.sleep(10_000);
    }

    // ===================== MONO EXAMPLES =====================

    /** Emits a simple "Hello World" Mono */
    public Mono<String> createHelloWorldMono() {
        return Mono.just("Hello World");
    }

    /** Emits a "Hello World" Mono with logging enabled */
    public Mono<String> createHelloWorldMonoWithLogging() {
        return Mono.just("Hello World").log();
    }

    /** Emits an empty Mono due to null value */
    public Mono<String> createNullMono() {
        return Mono.justOrEmpty(null);
    }

    /** Returns an explicitly empty Mono */
    public Mono<String> createEmptyMono() {
        return Mono.empty();
    }

    /** Collects delayed Flux into a list */
    public Mono<List<Integer>> collectDelayedIntegersAsList() {
        return Flux.range(1, 20).delayElements(Duration.ofMillis(1)).collectList();
    }

    /** Collects immediate Flux into a list */
    public Mono<List<Integer>> collectImmediateIntegersAsList() {
        return Flux.range(1, 20).collectList();
    }

    /** Generates a map of integers and their squares */
    public Mono<Map<Integer, Integer>> createMapOfSquares() {
        return Flux.range(1, 20).collectMap(i -> i, i -> i * i);
    }

    // ===================== FLUX EXAMPLES =====================

    /** Emits a sample set of string data */
    public Flux<String> getSampleStringFlux() {
        return Flux.just("DATA 01", "DATA 02", "DATA 03", "DATA 04", "DATA 05");
    }

    /** Emits common programming languages */
    public Flux<String> getProgrammingLanguagesFlux() {
        return Flux.fromIterable(Arrays.asList("Java", "Scala", "Python", "C", "C++", "C#"));
    }

    /** Converts programming language strings to uppercase */
    public Flux<String> uppercaseProgrammingLanguages() {
        return getProgrammingLanguagesFlux().map(String::toUpperCase);
    }

    /** Converts sample strings to uppercase using flatMap */
    public Flux<String> uppercaseSampleStringsUsingFlatMap() {
        return getSampleStringFlux().flatMap(item -> Flux.just(item.toUpperCase()));
    }

    /** Skips the first two sample string items */
    public Flux<String> skipFirstTwoSampleStrings() {
        return getSampleStringFlux().skip(2);
    }

    /** Delays emission of programming languages by 1 second */
    public Flux<String> delayLanguagesByOneSecond() {
        return getProgrammingLanguagesFlux().delayElements(Duration.ofSeconds(1));
    }

    /** Logs each delayed programming language */
    public Flux<String> logDelayedLanguages() {
        return getProgrammingLanguagesFlux().delayElements(Duration.ofSeconds(1)).log();
    }

    /** Skips items emitted during the first 2 seconds */
    public Flux<String> skipItemsInFirstTwoSeconds() {
        return getProgrammingLanguagesFlux().delayElements(Duration.ofSeconds(1)).skip(Duration.ofSeconds(2));
    }

    /** Skips until the value is divisible by 5 */
    public Flux<Integer> skipUntilDivisibleByFive() {
        return Flux.range(1, 20).skipUntil(i -> i % 5 == 0);
    }

    /** Concatenates two Flux ranges */
    public Flux<Integer> concatIntegerRanges() {
        return Flux.concat(Flux.range(1, 20), Flux.range(10, 100));
    }

    /** Merges two Flux ranges concurrently */
    public Flux<Integer> mergeIntegerRanges() {
        return Flux.merge(Flux.range(1, 20), Flux.range(10, 100));
    }

    /** Zips two delayed integer ranges */
    public Flux<Tuple2<Integer, Integer>> zipTwoDelayedRanges() {
        Flux<Integer> first = Flux.range(1, 20).delayElements(Duration.ofMillis(500));
        Flux<Integer> second = Flux.range(10, 100).delayElements(Duration.ofMillis(500));
        return Flux.zip(first, second);
    }

    /** Buffers all integers into a single list */
    public Flux<List<Integer>> bufferFullIntegerRange() {
        return Flux.range(1, 20).buffer();
    }

    /** Buffers every 3 emitted integers */
    public Flux<List<Integer>> bufferEveryThreeItems() {
        return Flux.range(1, 20).buffer(3);
    }

    /** Buffers every 3 items and delays each buffer by 1 second */
    public Flux<List<Integer>> bufferThreeItemsWithDelay() {
        return Flux.range(1, 20).buffer(3).delayElements(Duration.ofSeconds(1));
    }

    /** Buffers emitted items every 1 second */
    public Flux<List<Integer>> bufferItemsEverySecond() {
        return Flux.range(1, 20).buffer(Duration.ofSeconds(1));
    }

    /** Buffers items every 4 seconds after applying delay to each element */
    public Flux<List<Integer>> bufferItemsWithFourSecondDelay() {
        return Flux.range(1, 20).delayElements(Duration.ofSeconds(1)).buffer(Duration.ofSeconds(4));
    }

    /** Logs each signal in the stream */
    public Flux<Integer> logEachSignal() {
        return Flux.range(1, 20).doOnEach(signal -> System.out.println("Signal: " + signal));
    }

    /** Logs each signal type and "I'm complete" on finish */
    public Flux<Integer> logSignalTypeAndCompletion() {
        return Flux.range(1, 20).doOnEach(signal -> {
            if (signal.getType() == SignalType.ON_COMPLETE) {
                System.out.println("I'm complete");
            } else {
                System.out.println("Internal signal = " + signal);
            }
        });
    }

    /** Logs only when stream completes */
    public Flux<Integer> logOnlyOnComplete() {
        return Flux.range(1, 20).doOnComplete(() -> System.out.println("I'm complete"));
    }

    /** Logs each value with doOnNext */
    public Flux<Integer> logValuesWithDoOnNext() {
        return Flux.range(1, 20).doOnNext(i -> System.out.println("DoOnNext : " + i));
    }

    /** Logs subscription */
    public Flux<Integer> logOnSubscribe() {
        return Flux.range(1, 20).doOnSubscribe(subscription -> System.out.println("Subscribed !!"));
    }

    /** Logs on cancel event */
    public Flux<Integer> logOnCancel() {
        return Flux.range(1, 20).delayElements(Duration.ofSeconds(1)).doOnCancel(() -> System.out.println("Cancelled !!"));
    }

    // ===================== ERROR HANDLING =====================

    /** Emits an error at value 5 without handling */
    public Flux<Integer> errorWithoutRecovery() {
        return Flux.range(1, 20).map(i -> {
            if (i == 5) throw new RuntimeException("Error");
            return i;
        });
    }

    /** Continues stream execution when an error occurs */
    public Flux<Integer> errorHandlingWithOnErrorContinue() {
        return Flux.range(1, 20).map(i -> {
            if (i == 5) throw new RuntimeException("Error");
            return i;
        }).onErrorContinue((e, o) -> System.out.println("Don't worry about = " + o));
    }

    /** Returns fallback value when error occurs */
    public Flux<Integer> errorHandlingWithOnErrorReturn() {
        return Flux.range(1, 20).map(i -> {
            if (i == 5) throw new RuntimeException("Unexpected number!");
            return i;
        }).onErrorReturn(-1);
    }

    /** Switches to fallback Flux on error */
    public Flux<Integer> errorHandlingWithFallbackFlux() {
        return Flux.range(1, 20).map(i -> {
            if (i == 5) throw new RuntimeException("Unexpected number!");
            return i;
        }).onErrorResume(e -> Flux.range(100, 5));
    }

    /** Switches to fallback Mono on error */
    public Flux<Integer> errorHandlingWithFallbackMono() {
        return Flux.range(1, 20).map(i -> {
            if (i == 5) throw new RuntimeException("Unexpected number!");
            return i;
        }).onErrorResume(e -> Mono.just(5));
    }

    /** Maps error into another exception */
    public Flux<Integer> errorHandlingWithMappedError() {
        return Flux.range(1, 20).map(i -> {
            if (i == 5) throw new RuntimeException("Unexpected number!");
            return i;
        }).onErrorMap(e -> new UnsupportedOperationException(e.getMessage()));
    }
}