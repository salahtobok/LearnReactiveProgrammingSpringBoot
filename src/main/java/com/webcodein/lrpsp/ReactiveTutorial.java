package com.webcodein.lrpsp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ReactiveTutorial demonstrates the use of Mono and Flux in Project Reactor
 * with various operations including transformation, filtering, buffering, delaying,
 * merging, zipping, and collecting.
 */
public class ReactiveTutorial {

    /**
     * Demonstrates how to collect a Flux of integers into a map where key = integer and value = square of integer.
     */
    public Mono<Map<Integer, Integer>> collectIntegersToSquareMap() {
        Flux<Integer> numbers = Flux.range(1, 20);
        return numbers.collectMap(i -> i, i -> i * i);
    }

    /**
     * Prints a formatted section separator with a provided test title.
     *
     * @param title the title to display between test sections
     */
    public static void printSeparator(String title) {
        System.out.println("\n===== [ " + title + " ] =====");
    }

    // MONO EXAMPLES

    public static void main(String[] args) throws InterruptedException {
        ReactiveTutorial tutorial = new ReactiveTutorial();

        printSeparator("Mono: Emit Static String");
        tutorial.emitHelloWorldMono().subscribe(System.out::println);

        printSeparator("Mono: Emit Logged Static String");
        tutorial.emitLoggedHelloWorldMono().subscribe(System.out::println);

        printSeparator("Mono: Emit From Nullable (Null Value)");
        tutorial.emitMonoFromNull().subscribe(System.out::println);

        printSeparator("Mono: Emit Explicit Empty Mono");
        tutorial.emitEmptyMono().subscribe(System.out::println);

        // FLUX EXAMPLES
        printSeparator("Flux: Emit Sample Data List");
        tutorial.emitSampleData().subscribe(System.out::println);

        printSeparator("Flux: Emit Programming Language List");
        tutorial.emitProgrammingLanguages().subscribe(System.out::println);

        printSeparator("Flux: Convert Languages to Uppercase");
        tutorial.emitUppercaseLanguages().subscribe(System.out::println);

        printSeparator("Flux: Convert Sample Data to Uppercase using FlatMap");
        tutorial.emitSampleDataUppercaseFlatMap().subscribe(System.out::println);

        printSeparator("Flux: Skip First Two Items in Sample Data");
        tutorial.skipFirstTwoSampleItems().subscribe(System.out::println);

        printSeparator("Flux: Emit Languages with 1-Second Delay");
        tutorial.emitLanguagesWithDelay().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Log Languages with Delay");
        tutorial.logLanguagesWithDelay().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Skip Languages Emitted in First 2 Seconds");
        tutorial.skipLanguagesInFirstTwoSeconds().subscribe(System.out::println);
        Thread.sleep(10_000);

        // ADVANCED FLUX EXAMPLES
        printSeparator("Flux: Skip Until Number Divisible by 5");
        tutorial.skipUntilDivisibleByFive().subscribe(System.out::println);

        printSeparator("Flux: Concatenate Two Integer Ranges");
        tutorial.concatIntegerRanges().subscribe(System.out::println);

        printSeparator("Flux: Merge Two Integer Ranges");
        tutorial.mergeIntegerRanges().subscribe(System.out::println);

        printSeparator("Flux: Zip Two Delayed Integer Ranges");
        tutorial.zipDelayedIntegerRanges().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Mono: Collect Delayed Integer Range to List");
        tutorial.collectDelayedRangeToList().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Mono: Collect Immediate Integer Range to List");
        List<Integer> list = tutorial.collectImmediateRangeToList().block();
        System.out.println(list);
        Thread.sleep(20_000);

        printSeparator("Flux: Buffer Entire Range");
        tutorial.bufferAllItems().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Flux: Buffer Items in Groups of 3");
        tutorial.bufferItemsInChunksOfThree().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Flux: Delay Buffer of 3 Items Each");
        tutorial.bufferWithDelayPerChunk().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Flux: Buffer Items Every Second");
        tutorial.bufferItemsEverySecond().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Flux: Buffer with 4-Second Time Window");
        tutorial.bufferWithFourSecondWindow().subscribe(System.out::println);
        Thread.sleep(25_000);

        printSeparator("Mono: Collect Integer Square Map");
        tutorial.collectIntegersToSquareMap().subscribe(System.out::println);
        Thread.sleep(20_000);
    }

    // MONO EXAMPLES

    /**
     * Emits a Mono containing "Hello World".
     */
    public Mono<String> emitHelloWorldMono() {
        return Mono.just("Hello World");
    }

    /**
     * Emits a Mono containing "Hello World" with logging.
     */
    public Mono<String> emitLoggedHelloWorldMono() {
        return Mono.just("Hello World").log();
    }

    /**
     * Emits a Mono from a nullable value (null).
     */
    public Mono<String> emitMonoFromNull() {
        return Mono.justOrEmpty(null);
    }

    /**
     * Emits an explicitly empty Mono.
     */
    public Mono<String> emitEmptyMono() {
        return Mono.empty();
    }

    // FLUX EXAMPLES

    /**
     * Emits a predefined list of sample data strings.
     */
    public Flux<String> emitSampleData() {
        return Flux.just("DATA 01", "DATA 02", "DATA 03", "DATA 04", "DATA 05");
    }

    /**
     * Emits a list of common programming languages.
     */
    public Flux<String> emitProgrammingLanguages() {
        return Flux.fromIterable(Arrays.asList("Java", "Scala", "Python", "C", "C++", "C#"));
    }

    /**
     * Converts each programming language name to uppercase.
     */
    public Flux<String> emitUppercaseLanguages() {
        return emitProgrammingLanguages().map(String::toUpperCase);
    }

    /**
     * Uses flatMap to convert sample data items to uppercase.
     */
    public Flux<String> emitSampleDataUppercaseFlatMap() {
        return emitSampleData().flatMap(item -> Flux.just(item.toUpperCase()));
    }

    /**
     * Skips the first two sample data items.
     */
    public Flux<String> skipFirstTwoSampleItems() {
        return emitSampleData().skip(2);
    }

    /**
     * Delays each language item by 1 second.
     */
    public Flux<String> emitLanguagesWithDelay() {
        return emitProgrammingLanguages().delayElements(Duration.ofSeconds(1));
    }

    /**
     * Delays and logs each programming language emission.
     */
    public Flux<String> logLanguagesWithDelay() {
        return emitProgrammingLanguages().delayElements(Duration.ofSeconds(1)).log();
    }

    /**
     * Skips languages emitted within the first 2 seconds.
     */
    public Flux<String> skipLanguagesInFirstTwoSeconds() {
        return emitProgrammingLanguages().delayElements(Duration.ofSeconds(1)).skip(Duration.ofSeconds(2));
    }

    /**
     * Skips integers until an integer divisible by 5 is encountered.
     */
    public Flux<Integer> skipUntilDivisibleByFive() {
        return Flux.range(1, 20).skipUntil(i -> i % 5 == 0);
    }

    /**
     * Concatenates two Flux ranges: 1–20 and 10–100.
     */
    public Flux<Integer> concatIntegerRanges() {
        return Flux.concat(Flux.range(1, 20), Flux.range(10, 100));
    }

    /**
     * Merges two Flux ranges: 1–20 and 10–100.
     */
    public Flux<Integer> mergeIntegerRanges() {
        return Flux.merge(Flux.range(1, 20), Flux.range(10, 100));
    }

    /**
     * Zips two delayed ranges into a Flux of Tuples.
     */
    public Flux<Tuple2<Integer, Integer>> zipDelayedIntegerRanges() {
        Flux<Integer> first = Flux.range(1, 20).delayElements(Duration.ofMillis(500));
        Flux<Integer> second = Flux.range(10, 100).delayElements(Duration.ofMillis(500));
        return Flux.zip(first, second);
    }

    /**
     * Collects a delayed Flux range to a list.
     */
    public Mono<List<Integer>> collectDelayedRangeToList() {
        return Flux.range(1, 20).delayElements(Duration.ofMillis(1)).collectList();
    }

    /**
     * Collects a Flux range without delay to a list.
     */
    public Mono<List<Integer>> collectImmediateRangeToList() {
        return Flux.range(1, 20).collectList();
    }

    /**
     * Buffers the entire Flux into a single list.
     */
    public Flux<List<Integer>> bufferAllItems() {
        return Flux.range(1, 20).buffer();
    }

    /**
     * Buffers integers into chunks of 3.
     */
    public Flux<List<Integer>> bufferItemsInChunksOfThree() {
        return Flux.range(1, 20).buffer(3);
    }

    /**
     * Buffers items in groups of 3 and delays each chunk by 1 second.
     */
    public Flux<List<Integer>> bufferWithDelayPerChunk() {
        return Flux.range(1, 20).buffer(3).delayElements(Duration.ofSeconds(1));
    }

    /**
     * Buffers items emitted every 1 second.
     */
    public Flux<List<Integer>> bufferItemsEverySecond() {
        return Flux.range(1, 20).buffer(Duration.ofSeconds(1));
    }

    /**
     * Buffers items over a 4-second window while introducing a 1-second delay per item.
     */
    public Flux<List<Integer>> bufferWithFourSecondWindow() {
        return Flux.range(1, 20).delayElements(Duration.ofSeconds(1)).buffer(Duration.ofSeconds(4));
    }
}
