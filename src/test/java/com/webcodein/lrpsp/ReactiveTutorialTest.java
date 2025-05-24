package com.webcodein.lrpsp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.Map;

class ReactiveTutorialTest {

    private ReactiveTutorial tutorial;

    @BeforeEach
    void setUp() {
        tutorial = new ReactiveTutorial();
    }

    @Test
    void testCreateHelloWorldMono() {
        StepVerifier.create(tutorial.createHelloWorldMono())
                .expectNext("Hello World")
                .verifyComplete();
    }

    @Test
    void testCreateNullMono() {
        StepVerifier.create(tutorial.createNullMono())
                .verifyComplete();
    }

    @Test
    void testCreateEmptyMono() {
        StepVerifier.create(tutorial.createEmptyMono())
                .verifyComplete();
    }

    @Test
    void testGetSampleStringFlux() {
        StepVerifier.create(tutorial.getSampleStringFlux())
                .expectNext("DATA 01", "DATA 02", "DATA 03", "DATA 04", "DATA 05")
                .verifyComplete();
    }

    @Test
    void testUppercaseProgrammingLanguages() {
        StepVerifier.create(tutorial.uppercaseProgrammingLanguages())
                .expectNext("JAVA", "SCALA", "PYTHON", "C", "C++", "C#")
                .verifyComplete();
    }

    @Test
    void testSkipFirstTwoSampleStrings() {
        StepVerifier.create(tutorial.skipFirstTwoSampleStrings())
                .expectNext("DATA 03", "DATA 04", "DATA 05")
                .verifyComplete();
    }

    @Test
    void testConcatIntegerRanges() {
        StepVerifier.create(tutorial.concatIntegerRanges())
                .expectNextCount(119) // 20 + 99
                .verifyComplete();
    }

    @Test
    void testMergeIntegerRanges() {
        StepVerifier.create(tutorial.mergeIntegerRanges())
                .expectNextCount(119)
                .verifyComplete();
    }

    @Test
    void testZipTwoDelayedRanges() {
        StepVerifier.withVirtualTime(() -> tutorial.zipTwoDelayedRanges())
                .thenAwait(Duration.ofSeconds(10)) // Adjust based on zip behavior
                .expectNextCount(20)
                .verifyComplete();
    }

    @Test
    void testCreateMapOfSquares() {
        StepVerifier.create(tutorial.createMapOfSquares())
                .assertNext(map -> {
                    for (int i = 1; i <= 19; i++) {
                        assert map.get(i) == i * i;
                    }
                })
                .verifyComplete();
    }

    @Test
    void testErrorHandlingWithOnErrorReturn() {
        StepVerifier.create(tutorial.errorHandlingWithOnErrorReturn())
                .expectNext(1, 2, 3, 4, -1)
                .verifyComplete();
    }

    @Test
    void testErrorHandlingWithFallbackMono() {
        StepVerifier.create(tutorial.errorHandlingWithFallbackMono())
                .expectNext(1, 2, 3, 4, 5)
                .verifyComplete();
    }

    @Test
    void testErrorHandlingWithMappedError() {
        StepVerifier.create(tutorial.errorHandlingWithMappedError())
                .expectNext(1, 2, 3, 4)
                .expectError(UnsupportedOperationException.class)
                .verify();
    }
}