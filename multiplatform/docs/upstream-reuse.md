# Upstream reuse and adaptation

Upstream: https://github.com/quran/quran_android. Keep its source and GNU GPL version 3 license intact in the repository. This KMP app is an adjacent migration slice, not a replacement of every upstream capability.

The existing code was inspected through its indexed code graph and source snippets:

| Upstream source | Observed behavior | Reuse boundary |
|---|---|---|
| `app/.../presenter/audio/service/AudioQueue.kt` | Verse/range repetitions, previous/next verse, bounds, basmallah flag, local audio path and extension lookup | Port deterministic queue decisions to common domain. Keep file resolution in data adapters. Existing repeat values mean additional repeats, whereas new `RepeatSession` uses total completed recitations. Adapt explicitly. |
| `app/.../service/AudioService.kt:makeOrResetExoPlayer` | Media3 ExoPlayer, audio-only renderer, back buffer, local/network data source, network wake mode, audio-focus attributes and media-session activation | Android adapter can reuse Media3 and the existing service lifecycle. These Android classes cannot compile in common KMP or run on iOS. |
| `app/.../service/AudioService.kt:onPlayerCompleted` | Completion advances `AudioQueue`, handles gapless seek timings and basmallah before stopping | Preserve behavior and add regression coverage when migrating the full reciter/downloader stack. Local per-verse recording playback is a narrower first slice. |
| `app/.../ui/QuranActivity.kt:launchTranslationActivity` | Android Intent launches `TranslationManagerActivity` | Navigation 3 destinations replace Activity launches in shared UI. Existing Android-only translation management remains in the legacy app. |
| `common/data/.../QuranInfo.kt:getNumberOfAyahs` | Chapter count lookup and invalid chapter sentinel | Canonical validated `VerseId` and `QuranCanon` in `core:model` enforce the same chapter/verse boundaries with explicit errors. |

Media service migration is incremental. The shared `AudioPlayer` port receives a selected local media URI, invokes completion only after media playback ends, and offers play/pause/release. Android uses Media3; iOS uses AVFoundation. Background notifications, lock-screen controls, downloads, gapless reciter metadata and basmallah behavior should be integrated through platform services and verified on devices before parity is claimed.

Keep legacy data identifiers stable for migration. Quran source text requires corpus checksums, verse counts and provenance. New visual components can be reused across features without rewriting sacred text or altering recitation identifiers.
