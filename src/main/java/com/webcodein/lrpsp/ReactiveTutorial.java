package com.webcodein.lrpsp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates various usages of Project Reactor's Mono and Flux for reactive programming.
 * It includes creation, transformation, and processing of asynchronous data streams.
 */
public class ReactiveTutorial {

    /**
     * Creates a Mono that emits a simple greeting message.
     *
     * @return Mono emitting "Hello World"
     */
    public Mono<String> monoWithSimpleGreeting() {
        return Mono.just("Hello World");
    }

    /**
     * Creates a Mono with logging enabled to observe the reactive lifecycle.
     *
     * @return Logged Mono emitting "Hello World"
     */
    public Mono<String> monoWithLoggedGreeting() {
        return Mono.just("Hello World").log();
    }

    /**
     * Creates a Mono from a nullable value (null in this case).
     *
     * @return Empty Mono
     */
    public Mono<String> monoFromNullableValue() {
        return Mono.justOrEmpty(null);
    }

    /**
     * Creates an explicitly empty Mono.
     *
     * @return Empty Mono
     */
    public Mono<String> monoEmptyExplicit() {
        return Mono.empty();
    }

    /**
     * Creates a Flux from static sample data strings.
     *
     * @return Flux emitting sample strings
     */
    public Flux<String> fluxFromSampleData() {
        return Flux.just("DATA 01", "DATA 02", "DATA 03", "DATA 04", "DATA 05");
    }

    /**
     * Creates a Flux from a list of programming languages.
     *
     * @return Flux emitting programming language names
     */
    public Flux<String> fluxFromProgrammingLanguages() {
        List<String> languages = Arrays.asList("Java", "Scala", "Python", "C", "C++", "C#");
        return Flux.fromIterable(languages);
    }

    /**
     * Converts programming language names to uppercase.
     *
     * @return Flux emitting uppercased language names
     */
    public Flux<String> fluxWithUppercaseLanguages() {
        return fluxFromProgrammingLanguages().map(String::toUpperCase);
    }

    /**
     * Converts sample data to uppercase using flatMap.
     *
     * @return Flux emitting uppercased sample data
     */
    public Flux<String> fluxWithFlatMappedUppercaseData() {
        return fluxFromSampleData().flatMap(s -> Flux.just(s.toUpperCase()));
    }

    /**
     * Skips the first two elements from the sample data Flux.
     *
     * @return Flux emitting elements after skipping first two
     */
    public Flux<String> fluxWithSkippedSampleData() {
        return fluxFromSampleData().skip(2);
    }

    /**
     * Delays emission of each programming language string by 1 second.
     *
     * @return Delayed Flux emitting programming languages
     */
    public Flux<String> fluxWithDelayedProgrammingLanguages() {
        return Flux.just("Java", "Scala", "Python", "C", "C++", "C#")
                .delayElements(Duration.ofSeconds(1));
    }

    /**
     * Delays and logs each emitted programming language string.
     *
     * @return Delayed and logged Flux
     */
    public Flux<String> fluxWithDelayedLoggedLanguages() {
        return Flux.just("Java", "Scala", "Python", "C", "C++", "C#")
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    /**
     * Skips elements emitted during the first 2 seconds.
     *
     * @return Flux after skipping emissions for 2 seconds
     */
    public Flux<String> fluxSkippingElementsAfterDelay() {
        return Flux.just("Java", "Scala", "Python", "C", "C++", "C#")
                .delayElements(Duration.ofSeconds(1))
                .skip(Duration.ofSeconds(2));
    }

    /**
     * Demonstrates skipping values in a range until a multiple of 5 is found.
     *
     * @return Flux emitting integers starting from the first divisible by 5
     */
    public Flux<Integer> fluxSkipUntilDivisibleByFive() {
        Flux<Integer> integerFlux = Flux.range(1, 20);
        return integerFlux.skipUntil(integer -> integer % 5 == 0);
    }

    /**
     * Concatenates two Flux streams of integer ranges.
     *
     * @return Concatenated Flux of two integer ranges
     */
    public Flux<Integer> fluxWithConcatenatedRanges() {
        Flux<Integer> integerFlux1 = Flux.range(1, 20);
        Flux<Integer> integerFlux2 = Flux.range(40, 100);
        return integerFlux1.concatWith(integerFlux2);
    }

    /**
     * Prints a visual separator with a section title.
     *
     * @param title Section title to print
     */
    public static void printSeparator(String title) {
        System.out.println("\n===== " + title + " =====");
    }

    /**
     * Main method to demonstrate the reactive stream operations.
     *
     * @param args Command-line arguments
     * @throws InterruptedException For Thread.sleep operations
     */
    public static void main(String[] args) throws InterruptedException {
        ReactiveTutorial tutorial = new ReactiveTutorial();

        // MONO EXAMPLES
        printSeparator("Mono: Basic Hello World");
        tutorial.monoWithSimpleGreeting().subscribe(System.out::println);

        printSeparator("Mono: Logged Hello World");
        tutorial.monoWithLoggedGreeting().subscribe(System.out::println);

        printSeparator("Mono: Nullable Value Handling");
        tutorial.monoFromNullableValue().subscribe(System.out::println);

        printSeparator("Mono: Explicitly Empty");
        tutorial.monoEmptyExplicit().subscribe(System.out::println);

        // FLUX EXAMPLES
        printSeparator("Flux: Sample Data");
        tutorial.fluxFromSampleData().subscribe(System.out::println);

        printSeparator("Flux: Programming Languages");
        tutorial.fluxFromProgrammingLanguages().subscribe(System.out::println);

        printSeparator("Flux: Uppercased Programming Languages");
        tutorial.fluxWithUppercaseLanguages().subscribe(System.out::println);

        printSeparator("Flux: FlatMapped Uppercase Data");
        tutorial.fluxWithFlatMappedUppercaseData().subscribe(System.out::println);

        printSeparator("Flux: Skipping First Two Elements");
        tutorial.fluxWithSkippedSampleData().subscribe(System.out::println);

        printSeparator("Flux: Delayed Emission");
        tutorial.fluxWithDelayedProgrammingLanguages().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Delayed Emission With Logging");
        tutorial.fluxWithDelayedLoggedLanguages().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux: Skip Elements After Delay");
        tutorial.fluxSkippingElementsAfterDelay().subscribe(System.out::println);
        Thread.sleep(10_000);

        // ADDITIONAL EXAMPLES
        printSeparator("Flux: Skip Until Divisible by 5");
        tutorial.fluxSkipUntilDivisibleByFive().subscribe(System.out::println);

        printSeparator("Flux: Concatenated Integer Ranges");
        tutorial.fluxWithConcatenatedRanges().subscribe(System.out::println);
    }
}