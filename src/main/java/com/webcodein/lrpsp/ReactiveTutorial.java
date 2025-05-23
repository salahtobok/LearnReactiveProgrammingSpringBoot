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
 * Demonstrates various Reactor features using Mono and Flux in a tutorial style.
 */
public class ReactiveTutorial {

    /**
     * Prints a formatted section separator with a given title.
     *
     * @param title the section title
     */
    public static void printSeparator(String title) {
        System.out.println("\n===== [ " + title + " ] =====");
    }

    public static void main(String[] args) throws InterruptedException {
        ReactiveTutorial tutorial = new ReactiveTutorial();

        printSeparator("Mono: Emit Hello World");
        tutorial.emitHelloWorldMono().subscribe(System.out::println);

        printSeparator("Mono: Emit Hello World with Logging");
        tutorial.emitHelloWorldMonoWithLog().subscribe(System.out::println);

        printSeparator("Mono: Emit Null Mono");
        tutorial.emitNullMono().subscribe(System.out::println);

        printSeparator("Mono: Emit Empty Mono");
        tutorial.emitEmptyMono().subscribe(System.out::println);

        printSeparator("Flux: Emit Sample Strings");
        tutorial.emitSampleStringFlux().subscribe(System.out::println);

        printSeparator("Flux: Emit Programming Languages");
        tutorial.emitProgrammingLanguagesFlux().subscribe(System.out::println);

        printSeparator("Flux: Convert Languages to Uppercase");
        tutorial.convertLanguagesToUppercase().subscribe(System.out::println);

        printSeparator("Flux: Uppercase Sample Strings with FlatMap");
        tutorial.uppercaseSampleStringsWithFlatMap().subscribe(System.out::println);

        printSeparator("Flux: Skip First Two Items");
        tutorial.skipFirstTwoSampleItems().subscribe(System.out::println);

        printSeparator("Flux: Delay Languages by 1 Second");
        tutorial.delayLanguagesByOneSecond().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Log Each Delayed Language");
        tutorial.logEachDelayedLanguage().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Skip Languages in First 2 Seconds");
        tutorial.skipLanguagesEmittedInFirstTwoSeconds().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Skip Until Value Divisible by 5");
        tutorial.skipUntilValueDivisibleByFive().subscribe(System.out::println);

        printSeparator("Flux: Concatenate Integer Ranges");
        tutorial.concatTwoIntegerRanges().subscribe(System.out::println);

        printSeparator("Flux: Merge Integer Ranges Concurrently");
        tutorial.mergeIntegerRangesConcurrently().subscribe(System.out::println);

        printSeparator("Flux: Zip Two Delayed Integer Ranges");
        tutorial.zipTwoDelayedIntegerRanges().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Mono: Collect Delayed Integers to List");
        tutorial.collectDelayedIntegersToList().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Mono: Collect Immediate Integers to List");
        List<Integer> list = tutorial.collectImmediateIntegersToList().block();
        System.out.println(list);

        printSeparator("Flux: Buffer Entire Integer Range");
        tutorial.bufferEntireIntegerRange().subscribe(System.out::println);

        printSeparator("Flux: Buffer Every 3 Items");
        tutorial.bufferEveryThreeItems().subscribe(System.out::println);

        printSeparator("Flux: Buffer 3 Items with Delay");
        tutorial.bufferThreeItemsWithDelay().subscribe(System.out::println);

        printSeparator("Flux: Buffer Items Every Second");
        tutorial.bufferItemsEverySecond().subscribe(System.out::println);

        printSeparator("Flux: Buffer Items Every 4 Seconds with Delay");
        tutorial.bufferItemsFourSecondWindowWithDelay().subscribe(System.out::println);
        Thread.sleep(25_000);

        printSeparator("Mono: Map of Squares from Integers");
        tutorial.generateSquareMapFromIntegerRange().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Log All Signals");
        tutorial.logAllFluxSignals().subscribe();
        Thread.sleep(10_000);

        printSeparator("Flux: Log Signals and Completion");
        tutorial.logSignalsAndOnComplete().subscribe();
        Thread.sleep(10_000);

        printSeparator("Flux: Log Only Completion");
        tutorial.logOnCompleteOnly().subscribe();
        Thread.sleep(10_000);

        printSeparator("Flux: Do On Next Example");
        tutorial.logWithDoOnNext().subscribe();
        Thread.sleep(10_000);

        printSeparator("Flux: Do On Subscribe Example");
        tutorial.logWithDoOnSubscribe().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Do On Cancel Example");
        Disposable disposable = tutorial.logWithDoOnCancel().subscribe(System.out::println);
        Thread.sleep(3_500);
        disposable.dispose();
    }

    // ===================== MONO EXAMPLES =====================

    public Mono<String> emitHelloWorldMono() {
        return Mono.just("Hello World");
    }

    public Mono<String> emitHelloWorldMonoWithLog() {
        return Mono.just("Hello World").log();
    }

    public Mono<String> emitNullMono() {
        return Mono.justOrEmpty(null);
    }

    public Mono<String> emitEmptyMono() {
        return Mono.empty();
    }

    public Mono<List<Integer>> collectDelayedIntegersToList() {
        return Flux.range(1, 20).delayElements(Duration.ofMillis(1)).collectList();
    }

    public Mono<List<Integer>> collectImmediateIntegersToList() {
        return Flux.range(1, 20).collectList();
    }

    public Mono<Map<Integer, Integer>> generateSquareMapFromIntegerRange() {
        return Flux.range(1, 20).collectMap(i -> i, i -> i * i);
    }

    // ===================== FLUX EXAMPLES =====================

    public Flux<String> emitSampleStringFlux() {
        return Flux.just("DATA 01", "DATA 02", "DATA 03", "DATA 04", "DATA 05");
    }

    public Flux<String> emitProgrammingLanguagesFlux() {
        return Flux.fromIterable(Arrays.asList("Java", "Scala", "Python", "C", "C++", "C#"));
    }

    public Flux<String> convertLanguagesToUppercase() {
        return emitProgrammingLanguagesFlux().map(String::toUpperCase);
    }

    public Flux<String> uppercaseSampleStringsWithFlatMap() {
        return emitSampleStringFlux().flatMap(item -> Flux.just(item.toUpperCase()));
    }

    public Flux<String> skipFirstTwoSampleItems() {
        return emitSampleStringFlux().skip(2);
    }

    public Flux<String> delayLanguagesByOneSecond() {
        return emitProgrammingLanguagesFlux().delayElements(Duration.ofSeconds(1));
    }

    public Flux<String> logEachDelayedLanguage() {
        return emitProgrammingLanguagesFlux().delayElements(Duration.ofSeconds(1)).log();
    }

    public Flux<String> skipLanguagesEmittedInFirstTwoSeconds() {
        return emitProgrammingLanguagesFlux().delayElements(Duration.ofSeconds(1)).skip(Duration.ofSeconds(2));
    }

    public Flux<Integer> skipUntilValueDivisibleByFive() {
        return Flux.range(1, 20).skipUntil(i -> i % 5 == 0);
    }

    public Flux<Integer> concatTwoIntegerRanges() {
        return Flux.concat(Flux.range(1, 20), Flux.range(10, 100));
    }

    public Flux<Integer> mergeIntegerRangesConcurrently() {
        return Flux.merge(Flux.range(1, 20), Flux.range(10, 100));
    }

    public Flux<Tuple2<Integer, Integer>> zipTwoDelayedIntegerRanges() {
        Flux<Integer> first = Flux.range(1, 20).delayElements(Duration.ofMillis(500));
        Flux<Integer> second = Flux.range(10, 100).delayElements(Duration.ofMillis(500));
        return Flux.zip(first, second);
    }

    public Flux<List<Integer>> bufferEntireIntegerRange() {
        return Flux.range(1, 20).buffer();
    }

    public Flux<List<Integer>> bufferEveryThreeItems() {
        return Flux.range(1, 20).buffer(3);
    }

    public Flux<List<Integer>> bufferThreeItemsWithDelay() {
        return Flux.range(1, 20).buffer(3).delayElements(Duration.ofSeconds(1));
    }

    public Flux<List<Integer>> bufferItemsEverySecond() {
        return Flux.range(1, 20).buffer(Duration.ofSeconds(1));
    }

    public Flux<List<Integer>> bufferItemsFourSecondWindowWithDelay() {
        return Flux.range(1, 20).delayElements(Duration.ofSeconds(1)).buffer(Duration.ofSeconds(4));
    }

    public Flux<Integer> logAllFluxSignals() {
        return Flux.range(1, 20).doOnEach(signal -> System.out.println("Signal: " + signal));
    }

    public Flux<Integer> logSignalsAndOnComplete() {
        return Flux.range(1, 20).doOnEach(signal -> {
            if (signal.getType() == SignalType.ON_COMPLETE) {
                System.out.println("I'm complete");
            } else {
                System.out.println("Internal signal = " + signal);
            }
        });
    }

    public Flux<Integer> logOnCompleteOnly() {
        return Flux.range(1, 20).doOnComplete(() -> System.out.println("I'm complete"));
    }

    public Flux<Integer> logWithDoOnNext() {
        return Flux.range(1, 20).doOnNext(i -> System.out.println("DoOnNext: " + i));
    }

    public Flux<Integer> logWithDoOnSubscribe() {
        return Flux.range(1, 20).doOnSubscribe(subscription -> System.out.println("Subscribed !!"));
    }

    public Flux<Integer> logWithDoOnCancel() {
        return Flux.range(1, 20).delayElements(Duration.ofSeconds(1)).doOnCancel(() -> System.out.println("Cancelled !!"));
    }
}
