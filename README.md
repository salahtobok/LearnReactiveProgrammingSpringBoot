# ReactiveTutorial

This Java program showcases various features of [Project Reactor](https://projectreactor.io/) using `Mono` and `Flux`. It demonstrates key reactive programming techniques including transformations, buffering, merging, zipping, logging, and error handling.

---

## 🚀 Getting Started

Ensure your project has the following dependencies in your `pom.xml` (if using Maven):

```xml
<dependency>
    <groupId>io.projectreactor</groupId>
    <artifactId>reactor-core</artifactId>
    <version>3.6.0</version>
</dependency>
```

Or if you're using Gradle:

```groovy
implementation 'io.projectreactor:reactor-core:3.6.0'
```

---

## 📦 Package

```java
package com.webcodein.lrpsp;
```

---

## 📄 Overview

The `ReactiveTutorial` class is a comprehensive demonstration of:

- Creating simple Mono/Flux
- Transforming data using `map`, `flatMap`
- Delays and buffering
- Merging and zipping streams
- Logging and subscribing
- Error handling strategies

---

## ▶️ Running the Example

To run the tutorial:

```java
public static void main(String[] args) throws InterruptedException {
    ReactiveTutorial tutorial = new ReactiveTutorial();
    // Method calls to demonstrate Mono and Flux features
}
```

Some demonstrations involve `Thread.sleep(...)` to let asynchronous operations complete.

---

## 💡 Highlights

### ✅ Mono Examples

```java
tutorial.createHelloWorldMono().subscribe();
tutorial.createHelloWorldMonoWithLogging().subscribe();
tutorial.createNullMono().subscribe();
tutorial.createEmptyMono().subscribe();
tutorial.collectDelayedIntegersAsList().subscribe();
tutorial.collectImmediateIntegersAsList().block();
tutorial.createMapOfSquares().subscribe();
```

---

### 🔁 Flux Examples

```java
tutorial.getSampleStringFlux().subscribe();
tutorial.getProgrammingLanguagesFlux().subscribe();
tutorial.uppercaseProgrammingLanguages().subscribe();
tutorial.uppercaseSampleStringsUsingFlatMap().subscribe();
tutorial.skipFirstTwoSampleStrings().subscribe();
tutorial.delayLanguagesByOneSecond().subscribe();
tutorial.logDelayedLanguages().subscribe();
tutorial.skipItemsInFirstTwoSeconds().subscribe();
tutorial.skipUntilDivisibleByFive().subscribe();
tutorial.concatIntegerRanges().subscribe();
tutorial.mergeIntegerRanges().subscribe();
tutorial.zipTwoDelayedRanges().subscribe();
tutorial.bufferFullIntegerRange().subscribe();
tutorial.bufferEveryThreeItems().subscribe();
tutorial.bufferThreeItemsWithDelay().subscribe();
tutorial.bufferItemsEverySecond().subscribe();
tutorial.bufferItemsWithFourSecondDelay().subscribe();
```

---

### 📋 Logging and Signal Hooks

```java
tutorial.logEachSignal().subscribe();
tutorial.logSignalTypeAndCompletion().subscribe();
tutorial.logOnlyOnComplete().subscribe();
tutorial.logValuesWithDoOnNext().subscribe();
tutorial.logOnSubscribe().subscribe();
Disposable disposable = tutorial.logOnCancel().subscribe();
disposable.dispose(); // Trigger cancel event
```

---

### 🛠 Error Handling

```java
tutorial.errorWithoutRecovery().subscribe();
tutorial.errorHandlingWithOnErrorContinue().subscribe();
tutorial.errorHandlingWithOnErrorReturn().subscribe();
tutorial.errorHandlingWithFallbackFlux().subscribe();
tutorial.errorHandlingWithFallbackMono().subscribe();
tutorial.errorHandlingWithMappedError().subscribe();
```

---

## 📚 Purpose

This class is intended for educational use to better understand the capabilities of Project Reactor and how to leverage `Mono` and `Flux` in reactive programming with Java.

---

## 📜 License

This project is provided for learning and demonstration purposes. No explicit license attached.