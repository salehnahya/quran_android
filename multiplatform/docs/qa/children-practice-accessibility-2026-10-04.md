# Children practice accessibility checkpoint — 2026-10-04

Localized range position and completed repetition counts now expose polite live-region semantics through a separate PracticeProgressStatus component. This changes accessibility metadata only; child defaults, canonical Arabic, range bounds, repetitions and explicit memorization remain unchanged.

A native presentation test exercises Arabic locale, RTL, 150% Compose text scale and a constrained 240dp viewport. It hides/reveals canonical text, checks hidden text is absent from semantics, changes range, manually repeats and explicitly marks one ayah. It checks updated position/repetition text and polite semantics; it does not establish actual spoken TalkBack/VoiceOver output or audio playback.

The initial combined build passed 109 JVM tests, shared UI compilation and Android app/test APK assembly in 6 seconds. Independent Sol review cleared production changes but found that the native fixture could construct its Android cache before platform initialization and leak partial setup. Explicit initialization and nested finally cleanup now address that finding. Repair compilation passed, including Android app/test APK assembly; final independent re-review and native CI remain required before acceptance. The reviewer hit its usage limit before re-review, so that gate remains outstanding.
