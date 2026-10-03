# ADR-001: Incremental Quran Android migration to KMP

Status: Proposed

## Context

The source is https://github.com/quran/quran_android at commit 99506c080. It already contains common, feature, pages, and build-logic modules. The requested result is a redesigned, modular KMP app with clean architecture. Android and iOS are the initial proposed targets.

## Decision

Keep the original Android app available during migration. Add a new multiplatform app composition root and migrate one working reading flow before extending to audio, downloads, translations, and bookmarks. Use Compose Multiplatform for shared UI, with Stitch supplying the visual design. Stitch HTML exports are design references; Kotlin implementation is still required.

Proposed modules (not yet created):

| Module | Responsibility |
| --- | --- |
| core:model | Validated Quran identifiers and immutable metadata |
| core:domain | Repository contracts and meaningful use cases; no UI or platform dependencies |
| core:data | Repository implementations, local persistence and content acquisition |
| core:designsystem | Shared Compose theme, typography and reusable components |
| feature:reader | Reader state and presentation |
| feature:library | Surah/Juz navigation, bookmarks, history |
| feature:translation | Translation and tafsir presentation |
| feature:playback | Reciter selection and playback state |
| feature:downloads | Offline content management |
| feature:settings | Preferences and accessibility settings |
| composeApp | Navigation, dependency assembly and platform entry points |
| iosApp | iOS host and native integrations |

Dependency rule: feature presentation depends on domain and design system; data implements domain contracts; the app assembles implementations. Features do not depend on each other's implementations. Start with one domain module; split it only when independent ownership or build costs justify it.

Platform adapters implement audio sessions, background playback/downloads, file storage and platform lifecycle. Do not move Android Context, Resources, Services, or RxAndroid dependencies into commonMain.

## Options and trade-offs

An immediate full rewrite provides a clean starting point but risks losing page accuracy, content variants and mature reading behavior. Sharing only business logic simplifies native integration but duplicates the redesign on each platform. Incremental migration with shared Compose UI is proposed to preserve the existing app while delivering a consistent design. It requires temporary parallel app paths and careful adapter boundaries.

## Delivery sequence

1. Generate the six-screen Stitch design; document tokens, RTL behavior and reader states.
2. Establish compatible KMP/Compose build tooling and Android/iOS hosts. Validate both before porting features.
3. Extract metadata and identifiers; test surah/ayah boundaries and page mapping against upstream behavior.
4. Implement library-to-reader navigation with verified content and offline reading.
5. Port bookmarks/preferences with migration tests, then translation/tafsir.
6. Implement native audio and download adapters; test interruption, resume, cancellation and corrupt downloads.
7. Validate RTL, large text, screen readers and dark mode on Android and iOS.

## Content and provenance

Preserve upstream LICENSE and contributor credits. Inventory source-specific permissions for page images, Quran text, translations and recitations before bundling or redistributing content. Keep Quran text out of AI generation and visual decoration. Do not automatically direct a new distribution's traffic to upstream volunteer-funded content servers.

## Current state

Repository cloned locally; no GitHub account fork has been created. No KMP modules, UI redesign or build validation have been completed. Stitch currently requires Google sign-in. This document records the proposed migration, not an implemented architecture.

## Revisit triggers

Reconsider shared UI if reader rendering accuracy or accessibility cannot meet requirements on either target. Reconsider module splitting if dependency cycles or build performance show concrete need.
