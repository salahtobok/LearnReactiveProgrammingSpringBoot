package com.webcodein.lrpsp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

public class ReactiveTutorialTest {

    private ReactiveTutorial tutorial;

    @BeforeEach
    void setUp() {
        tutorial = new ReactiveTutorial();
    }

    @Test
    void testMonoWithSimpleGreeting() {
        Mono<String> mono = tutorial.monoWithSimpleGreeting();
        StepVerifier.create(mono)
                .expectNext("Hello World")
                .verifyComplete();
    }

    @Test
    void testMonoWithLoggedGreeting() {
        StepVerifier.create(tutorial.monoWithLoggedGreeting())
                .expectNext("Hello World")
                .verifyComplete();
    }

    @Test
    void testMonoFromNullableValue() {
        StepVerifier.create(tutorial.monoFromNullableValue())
                .verifyComplete(); // No element expected
    }

    @Test
    void testMonoEmptyExplicit() {
        StepVerifier.create(tutorial.monoEmptyExplicit())
                .verifyComplete(); // No element expected
    }

    @Test
    void testFluxFromSampleData() {
        StepVerifier.create(tutorial.fluxFromSampleData())
                .expectNext("DATA 01", "DATA 02", "DATA 03", "DATA 04", "DATA 05")
                .verifyComplete();
    }

    @Test
    void testFluxFromProgrammingLanguages() {
        StepVerifier.create(tutorial.fluxFromProgrammingLanguages())
                .expectNext("Java", "Scala", "Python", "C", "C++", "C#")
                .verifyComplete();
    }

    @Test
    void testFluxWithUppercaseLanguages() {
        StepVerifier.create(tutorial.fluxWithUppercaseLanguages())
                .expectNext("JAVA", "SCALA", "PYTHON", "C", "C++", "C#")
                .verifyComplete();
    }

    @Test
    void testFluxWithFlatMappedUppercaseData() {
        StepVerifier.create(tutorial.fluxWithFlatMappedUppercaseData())
                .expectNext("DATA 01", "DATA 02", "DATA 03", "DATA 04", "DATA 05")
                .verifyComplete();
    }

    @Test
    void testFluxWithSkippedSampleData() {
        StepVerifier.create(tutorial.fluxWithSkippedSampleData())
                .expectNext("DATA 03", "DATA 04", "DATA 05")
                .verifyComplete();
    }

    @Test
    void testFluxWithDelayedProgrammingLanguages() {
        StepVerifier.withVirtualTime(() -> tutorial.fluxWithDelayedProgrammingLanguages())
                .thenAwait(Duration.ofSeconds(6)) // 6 elements with 1s delay each
                .expectNext("Java", "Scala", "Python", "C", "C++", "C#")
                .verifyComplete();
    }

    @Test
    void testFluxWithDelayedLoggedLanguages() {
        StepVerifier.withVirtualTime(() -> tutorial.fluxWithDelayedLoggedLanguages())
                .thenAwait(Duration.ofSeconds(6))
                .expectNext("Java", "Scala", "Python", "C", "C++", "C#")
                .verifyComplete();
    }

    @Test
    void testFluxSkippingElementsAfterDelay() {
        StepVerifier.withVirtualTime(() -> tutorial.fluxSkippingElementsAfterDelay())
                .thenAwait(Duration.ofSeconds(7))
                .expectNext("Python", "C", "C++", "C#")
                .verifyComplete();
    }

    @Test
    void testFluxSkipUntilDivisibleByFive() {
        StepVerifier.create(tutorial.fluxSkipUntilDivisibleByFive())
                .expectNext(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
                .verifyComplete();
    }

    @Test
    void testFluxWithConcatenatedRanges() {
        StepVerifier.create(tutorial.fluxWithConcatenatedRanges())
                .expectNextCount(20 + 100) // 20 from 1–20, 100 from 40–139 (inclusive)
                .verifyComplete();
    }
}
