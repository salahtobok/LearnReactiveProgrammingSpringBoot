package com.webcodein.lrpsp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstrates basic usage of Project Reactor (a reactive programming library for Java).
 * This tutorial showcases examples of Mono (0 or 1 element) and Flux (0 to many elements),
 * including creation, transformation, and subscription to reactive streams.
 */
public class ReactiveTutorial {

    /**
     * Creates a Mono that emits a simple greeting message.
     *
     * @return Mono emitting "Hello World"
     */
    private Mono<String> getGreetingMono() {
        return Mono.just("Hello World");
    }

    /**
     * Creates a Mono that emits a greeting message with signal logging.
     *
     * @return Logged Mono emitting "Hello World"
     */
    private Mono<String> getLoggedGreetingMono() {
        return Mono.just("Hello World").log();
    }

    /**
     * Demonstrates safe handling of nullable values using Mono.justOrEmpty.
     * Will return an empty Mono if value is null.
     *
     * @return Empty Mono
     */
    private Mono<String> getNullableMono() {
        return Mono.justOrEmpty(null);
    }

    /**
     * Explicitly creates an empty Mono.
     *
     * @return Empty Mono
     */
    private Mono<String> getEmptyMono() {
        return Mono.empty();
    }

    /**
     * Creates a Flux emitting hardcoded data strings.
     *
     * @return Flux with data strings
     */
    private Flux<String> getSampleDataFlux() {
        return Flux.just("DATA 01", "DATA 02", "DATA 03", "DATA 04", "DATA 05");
    }

    /**
     * Creates a Flux from a list of programming languages.
     *
     * @return Flux with programming languages
     */
    private Flux<String> getProgrammingLanguagesFlux() {
        List<String> languages = Arrays.asList("Java", "Scala", "Python", "C", "C++", "C#");
        return Flux.fromIterable(languages);
    }

    /**
     * Converts the programming language names to uppercase.
     *
     * @return Flux with uppercased language names
     */
    private Flux<String> getUppercasedLanguagesFlux() {
        return getProgrammingLanguagesFlux().map(String::toUpperCase);
    }

    /**
     * Uses flatMap to simulate async transformation of strings to uppercase.
     *
     * @return Flattened Flux with uppercase values
     */
    private Flux<String> getFlatMappedDataFlux() {
        return getSampleDataFlux().flatMap(s -> Flux.just(s.toUpperCase()));
    }

    /**
     * Skips the first two elements in a sample data Flux.
     *
     * @return Flux without the first two elements
     */
    private Flux<String> getSampleDataFluxSkippingFirstTwo() {
        return getSampleDataFlux().skip(2);
    }

    private Flux<String> getSampleDataFluxSkippingDelay() {
        Flux<String> programmingLanguagesFlux = Flux.just("Java", "Scala", "Python", "C", "C++", "C#");
        return programmingLanguagesFlux.delayElements(Duration.ofSeconds(1));
    }


    private Flux<String> getSampleDataFluxSkippingDelayWithLog() {
        Flux<String> programmingLanguagesFlux = Flux.just("Java", "Scala", "Python", "C", "C++", "C#");
        return programmingLanguagesFlux.delayElements(Duration.ofSeconds(1)).log();
    }

    private Flux<String> getSampleDataFluxSkippingDelayWithLog23() {
        Flux<String> programmingLanguagesFlux = Flux.just("Java", "Scala", "Python", "C", "C++", "C#").delayElements(Duration.ofSeconds(1));
        return programmingLanguagesFlux.skip(Duration.ofSeconds(2));
    }

    /**
     * Helper to print a visual separator between test outputs.
     *
     * @param title Label for the section
     */
    private static void printSeparator(String title) {
        System.out.println("\n===== " + title + " =====");
    }

    /**
     * Entry point for running and testing the reactive methods.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        ReactiveTutorial tutorial = new ReactiveTutorial();

        // Mono Tests
        printSeparator("Mono - Basic Hello World");
        tutorial.getGreetingMono().subscribe(System.out::println);

        printSeparator("Mono - Logged Hello World");
        tutorial.getLoggedGreetingMono().subscribe(System.out::println);

        printSeparator("Mono - Nullable Value (null)");
        tutorial.getNullableMono().subscribe(System.out::println);

        printSeparator("Mono - Empty Mono");
        tutorial.getEmptyMono().subscribe(System.out::println);

        // Flux Tests
        printSeparator("Flux - Sample Data");
        tutorial.getSampleDataFlux().subscribe(System.out::println);

        printSeparator("Flux - Programming Languages");
        tutorial.getProgrammingLanguagesFlux().subscribe(System.out::println);

        printSeparator("Flux - Uppercased Languages");
        tutorial.getUppercasedLanguagesFlux().subscribe(System.out::println);

        printSeparator("Flux - FlatMapped Uppercase Data");
        tutorial.getFlatMappedDataFlux().subscribe(System.out::println);

        printSeparator("Flux - Skip First Two Elements");
        tutorial.getSampleDataFluxSkippingFirstTwo().subscribe(System.out::println);

        printSeparator("Flux - Skip First Two Elements");
        tutorial.getSampleDataFluxSkippingDelay().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux - Skip First Two Elements");
        tutorial.getSampleDataFluxSkippingDelayWithLog().subscribe(System.out::println);
        Thread.sleep(10_000);

        printSeparator("Flux - Skip First Two Elements");
        tutorial.getSampleDataFluxSkippingDelayWithLog23().subscribe(System.out::println);
        Thread.sleep(10_000);
    }
}