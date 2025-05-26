Here's a `README.md` file for both `BackPressureTutorial` and `ReactiveTutorial`, combined in a single block:

```markdown
# Project Reactor Tutorial

This repository demonstrates reactive programming concepts using [Project Reactor](https://projectreactor.io/). It contains two core classes:

- `ReactiveTutorial`: Showcases various `Mono` and `Flux` operators.
- `BackPressureTutorial`: Demonstrates handling backpressure in reactive streams.

---

## 📘 Class: `ReactiveTutorial`

This class provides a comprehensive demonstration of common `Mono` and `Flux` operations such as:

### Mono Examples

- `createHelloWorldMono()`  
  Emits a simple "Hello World" string.

- `createHelloWorldMonoWithLogging()`  
  Same as above, but with `.log()` for signal tracing.

- `createNullMono()`  
  Emits an empty Mono from a `null` value.

- `createEmptyMono()`  
  Emits a deliberately empty Mono.

- `collectImmediateIntegersAsList()` / `collectDelayedIntegersAsList()`  
  Collects emitted values into a list.

- `createMapOfSquares()`  
  Emits a `Map<Integer, Integer>` of numbers and their squares.

### Flux Examples

- `getSampleStringFlux()`, `getProgrammingLanguagesFlux()`  
  Basic Flux streams emitting strings.

- Transformation:
  - `uppercaseProgrammingLanguages()`
  - `uppercaseSampleStringsUsingFlatMap()`

- Filtering:
  - `skipFirstTwoSampleStrings()`
  - `skipUntilDivisibleByFive()`

- Time-based operations:
  - `delayLanguagesByOneSecond()`
  - `skipItemsInFirstTwoSeconds()`

- Merging and Zipping:
  - `concatIntegerRanges()`
  - `mergeIntegerRanges()`
  - `zipTwoDelayedRanges()`

- Buffering:
  - `bufferFullIntegerRange()`
  - `bufferEveryThreeItems()`
  - `bufferThreeItemsWithDelay()`
  - `bufferItemsEverySecond()`
  - `bufferItemsWithFourSecondDelay()`

- Logging and Signals:
  - `logEachSignal()`
  - `logSignalTypeAndCompletion()`
  - `logOnlyOnComplete()`
  - `logValuesWithDoOnNext()`
  - `logOnSubscribe()`
  - `logOnCancel()`

- Error Handling:
  - `errorWithoutRecovery()`
  - `errorHandlingWithOnErrorContinue()`
  - `errorHandlingWithOnErrorReturn()`
  - `errorHandlingWithFallbackFlux()`
  - `errorHandlingWithFallbackMono()`
  - `errorHandlingWithMappedError()`

---

## ⚙️ Class: `BackPressureTutorial`

This class demonstrates how to handle situations where the data publisher overwhelms the subscriber:

### Backpressure Strategies

- `controlledRateEmitter()`  
  Emits items every 100 ms — simulates a well-behaved publisher.

- `fastEmitterWithSlowProcessing()`  
  Emits items every 1 ms, but processing takes 100 ms — simulates overflow.

- `dropOverflowedItemsEmitter()`  
  Uses `onBackpressureDrop()` to drop unprocessed items.

- `bufferOverflowedItemsEmitter()`  
  Uses `onBackpressureBuffer(50)` to buffer up to 50 items.

- `bufferWithDropLatestStrategyEmitter()`  
  Uses `onBackpressureBuffer(50, DROP_LATEST)` to drop the most recent item when full.

### Execution

Each method can be individually tested by uncommenting the corresponding line in the `main()` method. Each test is wrapped in a clearly labeled section.

---

## 🧪 How to Run

1. Clone the repo.
2. Import it into your favorite Java IDE.
3. Run either `ReactiveTutorial.java` or `BackPressureTutorial.java`.
4. Watch the console output for visual demonstration of reactive behaviors.

---

## 🛠 Technologies Used

- Java 17+
- [Project Reactor](https://projectreactor.io/)
- Maven or Gradle (for dependency management)

---

## 📚 Learning Goals

- Understand core concepts of reactive streams (`Mono` and `Flux`)
- Learn different backpressure handling strategies
- Gain familiarity with transformation, buffering, merging, zipping, and error handling in reactive programming

---

## 🧾 License

This project is open-source and available under the MIT License.
```

Let me know if you'd like this exported to a file or want badges, diagrams, or GIFs added!
