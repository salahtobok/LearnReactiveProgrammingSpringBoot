package com.webcodein.lrpsp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates various usages of Project Reactor's Mono and Flux
 * for reactive programming with detailed examples.
 */
public class ReactiveTutorial {

    /**
     * Prints a formatted separator with a title for readability in console output.
     *
     * @param title the title to display within the separator
     */
    public static void printSeparator(String title) {
        System.out.println("\n===== " + title + " =====");
    }

    /**
     * Entry point for running all reactive examples.
     */
    public static void main(String[] args) throws InterruptedException {
        ReactiveTutorial tutorial = new ReactiveTutorial();

        // MONO EXAMPLES
        printSeparator("Mono: Basic Hello World");
        tutorial.basicHelloWorldMono().subscribe(System.out::println);

        printSeparator("Mono: Logged Hello World");
        tutorial.loggedHelloWorldMono().subscribe(System.out::println);

        printSeparator("Mono: Nullable Value Handling");
        tutorial.nullableToMonoExample().subscribe(System.out::println);

        printSeparator("Mono: Explicitly Empty");
        tutorial.explicitEmptyMono().subscribe(System.out::println);

        // FLUX EXAMPLES
        printSeparator("Flux: Sample Data");
        tutorial.sampleDataFlux().subscribe(System.out::println);

        printSeparator("Flux: Programming Languages");
        tutorial.programmingLanguagesFlux().subscribe(System.out::println);

        printSeparator("Flux: Uppercased Programming Languages");
        tutorial.uppercaseProgrammingLanguagesFlux().subscribe(System.out::println);

        printSeparator("Flux: FlatMapped Uppercase Data");
        tutorial.flatMappedUppercaseSampleDataFlux().subscribe(System.out::println);

        printSeparator("Flux: Skipping First Two Elements");
        tutorial.skipFirstTwoSampleDataFlux().subscribe(System.out::println);

        printSeparator("Flux: Delayed Emission");
        tutorial.delayedProgrammingLanguagesFlux().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Delayed Emission With Logging");
        tutorial.loggedDelayedProgrammingLanguagesFlux().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Skip Elements After Delay");
        tutorial.skipAfterDelayProgrammingLanguagesFlux().subscribe(System.out::println);
        Thread.sleep(10_000);

        // ADDITIONAL EXAMPLES
        printSeparator("Flux: Skip Until Divisible by 5");
        tutorial.skipUntilDivisibleByFiveFlux().subscribe(System.out::println);

        printSeparator("Flux: Concatenated Integer Ranges");
        tutorial.concatenateIntegerRangesFlux().subscribe(System.out::println);

        printSeparator("Flux: Merged Integer Ranges");
        tutorial.mergeIntegerRangesFlux().subscribe(System.out::println);

        printSeparator("Flux: Zipped Integer Ranges");
        tutorial.zipDelayedIntegerRangesFlux().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Mono: Collected List from Delayed Flux");
        tutorial.collectToListWithDelay().subscribe(System.out::println);
        Thread.sleep(20_000);

        printSeparator("Mono: Collected List from Immediate Flux");
        List<Integer> integerList = tutorial.collectToListImmediately().block();
        System.out.println("integerList = " + integerList);
        Thread.sleep(20_000);

        printSeparator("Flux: Buffered List");
        tutorial.bufferedIntegerFlux().subscribe(System.out::println);
        Thread.sleep(20_000);
    }

    // =====================
    // MONO EXAMPLES
    // =====================

    /**
     * Returns a Mono with a simple greeting.
     */
    public Mono<String> basicHelloWorldMono() {
        return Mono.just("Hello World");
    }

    /**
     * Returns a logged Mono for observing its lifecycle.
     */
    public Mono<String> loggedHelloWorldMono() {
        return Mono.just("Hello World").log();
    }

    /**
     * Returns a Mono from a nullable value (null in this case).
     */
    public Mono<String> nullableToMonoExample() {
        return Mono.justOrEmpty(null);
    }

    /**
     * Returns an explicitly empty Mono.
     */
    public Mono<String> explicitEmptyMono() {
        return Mono.empty();
    }

    // =====================
    // FLUX EXAMPLES
    // =====================

    /**
     * Returns a Flux emitting static sample string data.
     */
    public Flux<String> sampleDataFlux() {
        return Flux.just("DATA 01", "DATA 02", "DATA 03", "DATA 04", "DATA 05");
    }

    /**
     * Returns a Flux emitting a list of programming language names.
     */
    public Flux<String> programmingLanguagesFlux() {
        List<String> languages = Arrays.asList("Java", "Scala", "Python", "C", "C++", "C#");
        return Flux.fromIterable(languages);
    }

    /**
     * Transforms the programming language names to uppercase.
     */
    public Flux<String> uppercaseProgrammingLanguagesFlux() {
        return programmingLanguagesFlux().map(String::toUpperCase);
    }

    /**
     * Flat maps each sample data item to its uppercase variant.
     */
    public Flux<String> flatMappedUppercaseSampleDataFlux() {
        return sampleDataFlux().flatMap(s -> Flux.just(s.toUpperCase()));
    }

    /**
     * Skips the first two elements in the sample data stream.
     */
    public Flux<String> skipFirstTwoSampleDataFlux() {
        return sampleDataFlux().skip(2);
    }

    /**
     * Delays the emission of each programming language by 1 second.
     */
    public Flux<String> delayedProgrammingLanguagesFlux() {
        return programmingLanguagesFlux().delayElements(Duration.ofSeconds(1));
    }

    /**
     * Logs each programming language emitted after delay.
     */
    public Flux<String> loggedDelayedProgrammingLanguagesFlux() {
        return programmingLanguagesFlux()
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    /**
     * Skips any items emitted within the first 2 seconds.
     */
    public Flux<String> skipAfterDelayProgrammingLanguagesFlux() {
        return programmingLanguagesFlux()
                .delayElements(Duration.ofSeconds(1))
                .skip(Duration.ofSeconds(2));
    }

    // =====================
    // EXTRA EXAMPLES
    // =====================

    /**
     * Emits a range of integers, skipping values until the first divisible by 5.
     */
    public Flux<Integer> skipUntilDivisibleByFiveFlux() {
        return Flux.range(1, 20).skipUntil(i -> i % 5 == 0);
    }

    /**
     * Concatenates two ranges of integers into a single Flux.
     */
    public Flux<Integer> concatenateIntegerRangesFlux() {
        Flux<Integer> range1 = Flux.range(1, 20);
        Flux<Integer> range2 = Flux.range(10, 100);
        return Flux.concat(range1, range2);
    }

    /**
     * Merges two ranges of integers into a single Flux (interleaved).
     */
    public Flux<Integer> mergeIntegerRangesFlux() {
        Flux<Integer> range1 = Flux.range(1, 20);
        Flux<Integer> range2 = Flux.range(10, 100);
        return Flux.merge(range1, range2);
    }

    /**
     * Zips two delayed integer ranges and emits paired tuples.
     */
    public Flux<Tuple2<Integer, Integer>> zipDelayedIntegerRangesFlux() {
        Flux<Integer> range1 = Flux.range(1, 20).delayElements(Duration.ofMillis(500));
        Flux<Integer> range2 = Flux.range(10, 100).delayElements(Duration.ofMillis(500));
        return Flux.zip(range1, range2);
    }

    // =====================
    // COLLECT / BUFFER EXAMPLES
    // =====================

    /**
     * Collects elements into a list after applying a delay.
     */
    public Mono<List<Integer>> collectToListWithDelay() {
        return Flux.range(1, 20)
                .delayElements(Duration.ofMillis(1))
                .collectList();
    }

    /**
     * Collects all elements into a list immediately.
     */
    public Mono<List<Integer>> collectToListImmediately() {
        return Flux.range(1, 20).collectList();
    }

    /**
     * Buffers the emitted integers into batches (lists).
     */
    public Flux<List<Integer>> bufferedIntegerFlux() {
        return Flux.range(1, 20).buffer();
    }
}
