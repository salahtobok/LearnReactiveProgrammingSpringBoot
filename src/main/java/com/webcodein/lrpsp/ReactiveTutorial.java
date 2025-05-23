package com.webcodein.lrpsp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ReactiveTutorial demonstrates various Project Reactor features using Mono and Flux.
 */
public class ReactiveTutorial {

    /**
     * Collects integers 1–20 into a map where key = number and value = its square.
     */
    public Mono<Map<Integer, Integer>> createSquareMapFromRange() {
        return Flux.range(1, 20).collectMap(i -> i, i -> i * i);
    }

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

        printSeparator("Mono: Emit Static String");
        tutorial.monoEmitHelloWorld().subscribe(System.out::println);

        printSeparator("Mono: Emit Logged Static String");
        tutorial.monoEmitHelloWorldLogged().subscribe(System.out::println);

        printSeparator("Mono: Emit Mono from Null");
        tutorial.monoEmitNull().subscribe(System.out::println);

        printSeparator("Mono: Emit Explicit Empty Mono");
        tutorial.monoEmitEmpty().subscribe(System.out::println);

        printSeparator("Flux: Emit Sample Strings");
        tutorial.fluxEmitSampleStrings().subscribe(System.out::println);

        printSeparator("Flux: Emit Programming Languages");
        tutorial.fluxEmitProgrammingLanguages().subscribe(System.out::println);

        printSeparator("Flux: Languages to Uppercase");
        tutorial.fluxLanguagesToUppercase().subscribe(System.out::println);

        printSeparator("Flux: Sample Strings to Uppercase using FlatMap");
        tutorial.fluxSampleStringsToUppercaseFlatMap().subscribe(System.out::println);

        printSeparator("Flux: Skip First Two Sample Items");
        tutorial.fluxSkipFirstTwoSamples().subscribe(System.out::println);

        printSeparator("Flux: Delay Each Language by 1 Second");
        tutorial.fluxDelayLanguagesOneSecond().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Log Each Delayed Language");
        tutorial.fluxLogDelayedLanguages().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Skip Languages in First 2 Seconds");
        tutorial.fluxSkipLanguagesInFirstTwoSeconds().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Skip Until Number Divisible by 5");
        tutorial.fluxSkipUntilDivisibleByFive().subscribe(System.out::println);

        printSeparator("Flux: Concatenate Integer Ranges");
        tutorial.fluxConcatIntegerRanges().subscribe(System.out::println);

        printSeparator("Flux: Merge Integer Ranges");
        tutorial.fluxMergeIntegerRanges().subscribe(System.out::println);

        printSeparator("Flux: Zip Two Delayed Integer Ranges");
        tutorial.fluxZipDelayedRanges().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Mono: Collect Delayed Integers to List");
        tutorial.monoCollectDelayedRangeToList().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Mono: Collect Immediate Integers to List");
        List<Integer> list = tutorial.monoCollectImmediateRangeToList().block();
        System.out.println(list);

        printSeparator("Flux: Buffer Entire Integer Range");
        tutorial.fluxBufferEntireRange().subscribe(System.out::println);

        printSeparator("Flux: Buffer Every 3 Integers");
        tutorial.fluxBufferEveryThreeItems().subscribe(System.out::println);

        printSeparator("Flux: Buffer Every 3 Items with Delay");
        tutorial.fluxBufferThreeWithDelay().subscribe(System.out::println);

        printSeparator("Flux: Buffer Items Every Second");
        tutorial.fluxBufferItemsEverySecond().subscribe(System.out::println);

        printSeparator("Flux: Buffer Items Every 4 Seconds with Delay");
        tutorial.fluxBufferItemsFourSecondWindow().subscribe(System.out::println);
        Thread.sleep(25_000);

        printSeparator("Mono: Map of Squares from Integers");
        tutorial.createSquareMapFromRange().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Log All Signals");
        tutorial.fluxLogAllSignals().subscribe();
        Thread.sleep(10_000);

        printSeparator("Flux: Log Signals and Completion");
        tutorial.fluxLogSignalsWithCompletion().subscribe();
        Thread.sleep(10_000);

        printSeparator("Flux: Log Completion Only");
        tutorial.fluxLogOnComplete().subscribe();
        Thread.sleep(10_000);
    }

    // MONO EXAMPLES

    /** Emits a simple Mono with "Hello World". */
    public Mono<String> monoEmitHelloWorld() {
        return Mono.just("Hello World");
    }

    /** Emits a Mono with "Hello World" and logs signals. */
    public Mono<String> monoEmitHelloWorldLogged() {
        return Mono.just("Hello World").log();
    }

    /** Emits a Mono from a null value (results in Mono.empty). */
    public Mono<String> monoEmitNull() {
        return Mono.justOrEmpty(null);
    }

    /** Emits an explicitly empty Mono. */
    public Mono<String> monoEmitEmpty() {
        return Mono.empty();
    }

    // FLUX EXAMPLES

    /** Emits a predefined list of sample strings. */
    public Flux<String> fluxEmitSampleStrings() {
        return Flux.just("DATA 01", "DATA 02", "DATA 03", "DATA 04", "DATA 05");
    }

    /** Emits a list of programming languages. */
    public Flux<String> fluxEmitProgrammingLanguages() {
        return Flux.fromIterable(Arrays.asList("Java", "Scala", "Python", "C", "C++", "C#"));
    }

    /** Converts each language name to uppercase. */
    public Flux<String> fluxLanguagesToUppercase() {
        return fluxEmitProgrammingLanguages().map(String::toUpperCase);
    }

    /** Converts sample strings to uppercase using flatMap. */
    public Flux<String> fluxSampleStringsToUppercaseFlatMap() {
        return fluxEmitSampleStrings().flatMap(item -> Flux.just(item.toUpperCase()));
    }

    /** Skips the first two sample strings. */
    public Flux<String> fluxSkipFirstTwoSamples() {
        return fluxEmitSampleStrings().skip(2);
    }

    /** Emits languages with a delay of 1 second per item. */
    public Flux<String> fluxDelayLanguagesOneSecond() {
        return fluxEmitProgrammingLanguages().delayElements(Duration.ofSeconds(1));
    }

    /** Emits delayed languages and logs each emission. */
    public Flux<String> fluxLogDelayedLanguages() {
        return fluxEmitProgrammingLanguages().delayElements(Duration.ofSeconds(1)).log();
    }

    /** Skips items emitted within the first 2 seconds. */
    public Flux<String> fluxSkipLanguagesInFirstTwoSeconds() {
        return fluxEmitProgrammingLanguages().delayElements(Duration.ofSeconds(1)).skip(Duration.ofSeconds(2));
    }

    /** Skips values until a value divisible by 5 appears. */
    public Flux<Integer> fluxSkipUntilDivisibleByFive() {
        return Flux.range(1, 20).skipUntil(i -> i % 5 == 0);
    }

    /** Concatenates two ranges: 1–20 and 10–100. */
    public Flux<Integer> fluxConcatIntegerRanges() {
        return Flux.concat(Flux.range(1, 20), Flux.range(10, 100));
    }

    /** Merges two integer ranges concurrently. */
    public Flux<Integer> fluxMergeIntegerRanges() {
        return Flux.merge(Flux.range(1, 20), Flux.range(10, 100));
    }

    /** Zips two delayed integer Fluxes together. */
    public Flux<Tuple2<Integer, Integer>> fluxZipDelayedRanges() {
        Flux<Integer> first = Flux.range(1, 20).delayElements(Duration.ofMillis(500));
        Flux<Integer> second = Flux.range(10, 100).delayElements(Duration.ofMillis(500));
        return Flux.zip(first, second);
    }

    /** Collects a delayed integer Flux into a List. */
    public Mono<List<Integer>> monoCollectDelayedRangeToList() {
        return Flux.range(1, 20).delayElements(Duration.ofMillis(1)).collectList();
    }

    /** Collects a range of integers into a List immediately. */
    public Mono<List<Integer>> monoCollectImmediateRangeToList() {
        return Flux.range(1, 20).collectList();
    }

    /** Buffers the entire integer Flux into a single list. */
    public Flux<List<Integer>> fluxBufferEntireRange() {
        return Flux.range(1, 20).buffer();
    }

    /** Buffers every 3 integers into a list. */
    public Flux<List<Integer>> fluxBufferEveryThreeItems() {
        return Flux.range(1, 20).buffer(3);
    }

    /** Buffers groups of 3 integers with a 1-second delay between each group. */
    public Flux<List<Integer>> fluxBufferThreeWithDelay() {
        return Flux.range(1, 20).buffer(3).delayElements(Duration.ofSeconds(1));
    }

    /** Buffers items based on a 1-second time span. */
    public Flux<List<Integer>> fluxBufferItemsEverySecond() {
        return Flux.range(1, 20).buffer(Duration.ofSeconds(1));
    }

    /** Buffers items over a 4-second time window with 1-second delay per item. */
    public Flux<List<Integer>> fluxBufferItemsFourSecondWindow() {
        return Flux.range(1, 20).delayElements(Duration.ofSeconds(1)).buffer(Duration.ofSeconds(4));
    }

    /** Logs each signal emitted by the Flux (next, complete, etc.). */
    public Flux<Integer> fluxLogAllSignals() {
        return Flux.range(1, 20).doOnEach(signal -> System.out.println("Signal: " + signal));
    }

    /** Logs signals and explicitly logs onComplete message. */
    public Flux<Integer> fluxLogSignalsWithCompletion() {
        return Flux.range(1, 20).doOnEach(signal -> {
            if (signal.getType() == SignalType.ON_COMPLETE) {
                System.out.println("I'm complete");
            } else {
                System.out.println("Internal signal = " + signal);
            }
        });
    }

    /** Logs only when the Flux completes. */
    public Flux<Integer> fluxLogOnComplete() {
        return Flux.range(1, 20).doOnComplete(() -> System.out.println("I'm complete"));
    }
}