package com.webcodein.lrpsp;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates basic usage of Project Reactor (a reactive programming library for Java).
 * This tutorial showcases examples of Mono (0 or 1 element) and Flux (0 to many elements),
 * including creation, transformation, and subscription to reactive streams.
 */
public class ReactiveTutorial {

    /**
     * Returns a Mono that emits a single "Hello World" string.
     * This is the simplest form of Mono creation using Mono.just().
     *
     * @return a Mono emitting "Hello World"
     */
    private Mono<String> createHelloWorldMono() {
        return Mono.just("Hello World");
    }

    /**
     * Returns a Mono that emits "Hello World" and logs the signals/events it goes through.
     * Useful for debugging or understanding the reactive lifecycle.
     *
     * @return a logged Mono
     */
    private Mono<String> createLoggedHelloWorldMono() {
        return Mono.just("Hello World").log();
    }

    /**
     * Demonstrates handling nullable values safely using Mono.justOrEmpty().
     * If the input is null, returns Mono.empty().
     *
     * @return an empty Mono since the input is null
     */
    private Mono<String> createMonoFromNullableValue() {
        return Mono.justOrEmpty(null);
    }

    /**
     * Returns an explicitly empty Mono that completes without emitting any item.
     *
     * @return Mono.empty()
     */
    private Mono<String> createEmptyMono() {
        return Mono.empty();
    }

    /**
     * Creates a Flux emitting several hardcoded data strings.
     * This is a static set of data.
     *
     * @return Flux of predefined data strings
     */
    private Flux<String> createSampleDataFlux() {
        return Flux.just("DATA 01", "DATA 02", "DATA 03", "DATA 04", "DATA 05");
    }

    /**
     * Creates a Flux from a list of programming languages using Flux.fromIterable().
     *
     * @return Flux emitting programming language names
     */
    private Flux<String> createProgrammingLanguagesFlux() {
        List<String> programmingLanguages = new ArrayList<>();
        programmingLanguages.add("Java");
        programmingLanguages.add("Scala");
        programmingLanguages.add("Python");
        programmingLanguages.add("C");
        programmingLanguages.add("C++");
        programmingLanguages.add("C#");

        return Flux.fromIterable(programmingLanguages);
    }

    /**
     * Transforms the values from the programming languages Flux to uppercase using map().
     *
     * @return Flux with uppercased language names
     */
    private Flux<String> createUppercaseProgrammingLanguagesFlux() {
        return createProgrammingLanguagesFlux().map(String::toUpperCase);
    }

    /**
     * Demonstrates flatMap by transforming each string in the Flux to uppercase and
     * wrapping it in another Flux (simulating async operations).
     *
     * @return Flattened Flux with uppercase values
     */
    private Flux<String> createFlatMap() {
        return createSampleDataFlux().flatMap(s -> Flux.just(s.toUpperCase()));
    }

    /**
     * Main entry point to test and demonstrate all the defined reactive methods.
     * It subscribes to the Monos and Fluxes to trigger their execution and print output.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        ReactiveTutorial tutorial = new ReactiveTutorial();

        // Mono examples
        tutorial.createHelloWorldMono(); // Mono created but not subscribed: no execution
        tutorial.createHelloWorldMono().subscribe(); // Subscribed but no consumer: still no output
        tutorial.createHelloWorldMono().subscribe(System.out::println); // Prints: Hello World

        tutorial.createLoggedHelloWorldMono().subscribe(System.out::println); // Prints value with log

        tutorial.createMonoFromNullableValue().subscribe(System.out::println); // No output (empty)

        tutorial.createEmptyMono().subscribe(System.out::println); // No output (also empty)

        // Flux examples
        tutorial.createSampleDataFlux().subscribe(System.out::println); // Prints: DATA 01 to DATA 05
        tutorial.createProgrammingLanguagesFlux().subscribe(System.out::println); // Prints programming languages
        tutorial.createUppercaseProgrammingLanguagesFlux().subscribe(System.out::println); // Uppercased languages

        tutorial.createFlatMap().subscribe(System.out::println); // Same as map, but demonstrates flatMap use
    }
}