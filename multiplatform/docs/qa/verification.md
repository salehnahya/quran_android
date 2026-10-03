# Verification evidence

Validation is specific to the new KMP application. The legacy upstream application's feature parity is not established by these tests.

## TDD and domain/data checks

Preserved JUnit XML in `red-domain/` captures 10 failing tests out of 11 before implementation. `red-data/` captures 4 failing persistence tests before implementation. The failures originate in explicitly unimplemented model/domain/data functions; some repetition tests initially fail at verse validation during construction. These demonstrate a preimplementation failing run, not isolated coverage of every later UI behavior.

The independently rerun JVM suites on 2026-10-03 pass all 18 tests: 3 canonical model tests, 4 repetition tests, 4 Qibla tests, 4 persistence tests and 3 bundled-corpus tests. Copies of the actual XML are in `green-domain/` and `green-data/`. Corpus checks visit all 114 chapters and 6,236 canonical verse identities, check contiguous verse numbers, unique identities and nonblank Arabic/source fields, and confirm changing interface language preserves Arabic text. These structural checks do not replace checking the corpus against its authoritative source.

`red-lifecycle/compile.log` captures the playback lifecycle regression tests failing to compile before the presentation controller exists. The added tests cover stale completion callbacks after pause/resume, stopping old playback before explicit memorization, reset/disposal cancellation, manual progress double-counting and fixed-count completion. A failing compilation is the initial RED stage; implementation and a passing test run are still required.

## Platform validation

Local tools: Corretto JDK 17.0.17, Gradle 8.13, Android SDK 36 and Xcode 15.4 (15F31d). The initial Android build exposes actual Navigation 3 composition-root compilation errors; Android assembly and lint must be rerun after repair. iOS framework compilation is in progress.

Kotlin's [official compatibility table](https://kotlinlang.org/docs/multiplatform/multiplatform-compatibility-guide.html) lists Xcode 26.0 for Kotlin 2.3.20. This machine's Xcode 15.4 is outside that supported combination. A successful native compilation/link and actual Swift host build are required before iOS is reported verified. Compiler suppression or a JVM test pass must not be reported as native validation.

An existing Pixel 8 Pro API 33 Android emulator was started for UI verification. Starting the emulator is not evidence that the app was installed or exercised.
