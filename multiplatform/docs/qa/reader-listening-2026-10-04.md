# Reader listening checkpoint — 2026-10-04

A verse action now prepares and listens to exactly the selected canonical ayah using the shared trusted reciter selection and storage module. A separate reader presentation state and audio card expose preparing, playing, ready, failure/retry, pause and stop. Listening once does not mutate reading or memorization progress. Replay reuses the prepared source; pause retains its lease, while stop/source changes/disposal unload native media before releasing protection.

ReaderEntry orchestrates existing queue and playback controllers. The reader feature receives state and callbacks and has no dependency on the memorization feature. The active audio panel is bounded and scrollable to keep at least half a constrained reader viewport available; inactive reader layout is preserved. Labels use real English/Arabic XML resources, shared buttons/text, EveryAyah attribution, and a polite status live region.

Eight meaningful common controller tests cover exact source keys, one-ayah completion/replay, late source/completion suppression, explicit failure recovery, cancellation/disposal, protection ordering and leaving an unowned native player alone. Two native presentation tests are authored for exact 1:2 selection and controls plus a constrained 240dp viewport at 150% text scale. Their state fixtures exercise UI presentation and do not establish actual provider playback.

Combined verification passed: 109 JVM tests, shared JVM UI compilation, Android app and instrumentation APK assembly (final incremental repair run 3 seconds). Compilation initially caught missing instrumentation dependencies and an incorrect locale import; these were repaired without changing production behavior. Independent Sol 6.1 source review cleared the slice. Native presentation/runtime and iOS CI acceptance remain pending; no local native result is claimed.

This slice is explicitly one-ayah listening. Continuous chapter queues, native notification/interruption behavior, external-control state synchronization, physical-device audio and iOS runtime acceptance remain product work. AI is deferred.

## Accepted CI checkpoint

PR #9 merged as f72f634add215ac4ff7e5553e158a522564638b3 after run 37183422856 passed Android/tests, Android runtime and iOS framework/simulator app at reviewed head 83dbbea289c0dcf65c55c61d03c9d6e4146df59e. Independent artifact inspection of 11295419718 confirmed 15 native tests, zero failures/skips, including both new reader presentation tests; all 25 screenshots were preserved. The PR was already merged on final refresh; no duplicate merge was attempted. Post-merge authored COMMENT 5404699404 is an acceptance record, not formal GitHub approval.
