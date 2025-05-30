# Project Reactor Tutorial

This project demonstrates various features of Project Reactor using `Mono` and `Flux` reactive types, including transformations, buffering, merging, zipping, logging, and error handling.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Mono Examples](#mono-examples)
- [Flux Examples](#flux-examples)
- [Error Handling](#error-handling)
- [Usage](#usage)

## Overview

The `ReactiveTutorial` class provides practical examples of reactive programming concepts using Project Reactor, a foundational library for building reactive applications on the JVM.

## Features

### Mono Examples
- Basic Mono creation
- Logging
- Empty/null handling
- Collection operations
- Map transformations

### Flux Examples
- Basic Flux creation
- Transformations (map, flatMap)
- Filtering (skip, skipUntil)
- Combining streams (concat, merge, zip)
- Buffering strategies
- Timing operations (delay, interval)
- Signal logging

### Error Handling
- Error recovery strategies
- Fallback mechanisms
- Error mapping

## Mono Examples

| Method | Description |
|--------|-------------|
| `createHelloWorldMono()` | Emits a simple "Hello World" Mono |
| `createHelloWorldMonoWithLogging()` | Emits "Hello World" with logging |
| `createNullMono()` | Demonstrates empty Mono from null |
| `createEmptyMono()` | Returns an explicitly empty Mono |
| `collectDelayedIntegersAsList()` | Collects delayed Flux into a list |
| `collectImmediateIntegersAsList()` | Collects immediate Flux into a list |
| `createMapOfSquares()` | Generates a map of integers and their squares |

## Flux Examples

| Method | Description |
|--------|-------------|
| `getSampleStringFlux()` | Emits sample string data |
| `getProgrammingLanguagesFlux()` | Emits programming languages |
| `uppercaseProgrammingLanguages()` | Converts languages to uppercase |
| `uppercaseSampleStringsUsingFlatMap()` | Uppercase conversion with flatMap |
| `skipFirstTwoSampleStrings()` | Skips first two items |
| `delayLanguagesByOneSecond()` | Delays emissions by 1 second |
| `logDelayedLanguages()` | Logs delayed language emissions |
| `skipItemsInFirstTwoSeconds()` | Skips items in first 2 seconds |
| `skipUntilDivisibleByFive()` | Skips until divisible by 5 |
| `concatIntegerRanges()` | Concatenates two Flux ranges |
| `mergeIntegerRanges()` | Merges two ranges concurrently |
| `zipTwoDelayedRanges()` | Zips two delayed ranges |
| `bufferFullIntegerRange()` | Buffers all integers into single list |
| `bufferEveryThreeItems()` | Buffers every 3 items |
| `bufferThreeItemsWithDelay()` | Buffers with delay |
| `bufferItemsEverySecond()` | Buffers items every second |
| `bufferItemsWithFourSecondDelay()` | Buffers with complex timing |
| Various logging methods | Different signal logging strategies |

## Error Handling

| Method | Description |
|--------|-------------|
| `errorWithoutRecovery()` | Unhandled error demonstration |
| `errorHandlingWithOnErrorContinue()` | Continues on error |
| `errorHandlingWithOnErrorReturn()` | Returns fallback value |
| `errorHandlingWithFallbackFlux()` | Switches to fallback Flux |
| `errorHandlingWithFallbackMono()` | Switches to fallback Mono |
| `errorHandlingWithMappedError()` | Maps error to new exception |

## Usage

1. Clone the repository (if applicable)
2. Ensure you have Java 8+ and Maven installed
3. Run the main method in `ReactiveTutorial` class
4. Observe the console output showing various reactive operations

Each example is clearly labeled with a separator showing which operation is being demonstrated. Many examples include timing operations, so the program may take several minutes to complete all demonstrations.

Note: The examples include `Thread.sleep()` calls to allow asynchronous operations to complete, so the total runtime is approximately 2-3 minutes.




# BackPressureTutorial

This project demonstrates different backpressure handling strategies using Project Reactor's `Flux`. It showcases various publisher-subscriber scenarios and how to handle situations where the publisher produces data faster than the subscriber can consume it.

## Overview

The `BackPressureTutorial` class provides examples of:
- Controlled emission to avoid backpressure
- Uncontrolled fast emission causing overflow
- Various backpressure handling strategies:
    - Dropping overflowed items
    - Buffering overflowed items
    - Buffering with a DROP_LATEST strategy

## Methods

### `controlledRateEmitter()`
- Emits integers at a controlled rate (1 item every 100ms)
- Simulates a well-behaved publisher that avoids overwhelming the subscriber

### `fastEmitterWithSlowProcessing()`
- Emits items at a very fast rate (1ms interval)
- Each item takes 100ms to process
- Simulates a publisher out-pacing the subscriber

### `dropOverflowedItemsEmitter()`
- Emits items rapidly (1ms interval)
- Uses `onBackpressureDrop()` strategy
- Drops items that cannot be processed in time

### `bufferOverflowedItemsEmitter()`
- Emits items rapidly (1ms interval)
- Buffers up to 50 items when under pressure
- Uses `onBackpressureBuffer()` strategy

### `bufferWithDropLatestStrategyEmitter()`
- Emits items rapidly (1ms interval)
- Buffers up to 50 items
- Drops the latest item on overflow using `DROP_LATEST` strategy

## Usage

1. Clone the repository or copy the `BackPressureTutorial` class
2. The class contains a `main()` method with 5 test scenarios
3. Uncomment the test you want to run (tests are commented by default to prevent simultaneous execution)
4. Run the class

Example test execution:
```java
public static void main(String[] args) {
    BackPressureTutorial tutorial = new BackPressureTutorial();
    
    System.out.println("\n========== Test 3: Drop Overflowed Items ==========");
    tutorial.dropOverflowedItemsEmitter().blockLast();
    System.out.println("========== End of Test 3 ==========\n");
}