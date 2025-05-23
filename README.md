# ReactiveTutorial – Project Reactor Demo

This Java application demonstrates the usage of **Project Reactor**'s `Mono` and `Flux` types for reactive programming. The class includes multiple examples showing how to use transformation, filtering, buffering, delaying, merging, zipping, and collecting operations using the Reactor Core library.

---

## 🧪 Overview

- **Mono Examples** – Working with 0 or 1 elements.
- **Flux Examples** – Working with multiple elements.
- **Advanced Flux Examples** – Combining, buffering, and transforming streams.

---

## 🚀 How to Run

To run the project:

1. Ensure you have Java and Maven installed.
2. Clone this repository and navigate into the directory.
3. Run the main class:

```bash
mvn compile exec:java -Dexec.mainClass="com.webcodein.lrpsp.ReactiveTutorial"
```

---

## 🔹 Mono Examples

```java
// Emit a simple string
Mono<String> emitHelloWorldMono();

// Emit a string with logging
Mono<String> emitLoggedHelloWorldMono();

// Emit a Mono from a null value
Mono<String> emitMonoFromNull();

// Emit an explicitly empty Mono
Mono<String> emitEmptyMono();
```

---

## 🔸 Flux Examples

```java
// Emit static data
Flux<String> emitSampleData();

// Emit a list of programming languages
Flux<String> emitProgrammingLanguages();

// Convert each item to uppercase
Flux<String> emitUppercaseLanguages();
Flux<String> emitSampleDataUppercaseFlatMap();

// Skip first N items
Flux<String> skipFirstTwoSampleItems();
```

---

## ⏱️ Delayed Emission and Filtering

```java
// Delay items
Flux<String> emitLanguagesWithDelay();
Flux<String> logLanguagesWithDelay();

// Skip items based on time
Flux<String> skipLanguagesInFirstTwoSeconds();
```

---

## 🔁 Advanced Flux Operations

```java
// Skip until condition is met
Flux<Integer> skipUntilDivisibleByFive();

// Concatenate and merge streams
Flux<Integer> concatIntegerRanges();
Flux<Integer> mergeIntegerRanges();

// Zip two streams together with delay
Flux<Tuple2<Integer, Integer>> zipDelayedIntegerRanges();
```

---

## 📥 Collecting and Buffering

```java
// Collect a delayed Flux into a List
Mono<List<Integer>> collectDelayedRangeToList();

// Collect an immediate Flux into a List
Mono<List<Integer>> collectImmediateRangeToList();

// Collect into Map: number -> square
Mono<Map<Integer, Integer>> collectIntegersToSquareMap();
```

---

## 📦 Buffering Strategies

```java
// Buffer all into one list
Flux<List<Integer>> bufferAllItems();

// Buffer in chunks
Flux<List<Integer>> bufferItemsInChunksOfThree();

// Buffer and delay each chunk
Flux<List<Integer>> bufferWithDelayPerChunk();

// Buffer items emitted every 1s
Flux<List<Integer>> bufferItemsEverySecond();

// Buffer over time window
Flux<List<Integer>> bufferWithFourSecondWindow();
```

---

## 📚 Requirements

- Java 8 or above
- Maven
- Project Reactor (reactor-core)

You can add the following dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>io.projectreactor</groupId>
  <artifactId>reactor-core</artifactId>
  <version>3.6.5</version>
</dependency>
```

---

## 🙌 Author

Created by TOBOK SALAH EDDINE – Demonstrating reactive programming with Reactor.

---

## 📝 License

This project is open source and available under the MIT License.
