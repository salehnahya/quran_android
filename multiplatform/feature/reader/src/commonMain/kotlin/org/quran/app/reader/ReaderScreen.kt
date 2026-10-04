package org.quran.app.reader

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.quran.app.designsystem.QuranStrings
import org.quran.app.designsystem.QuranColors
import org.quran.app.designsystem.QuranText
import org.quran.app.designsystem.QuranTextVariant
import org.quran.app.designsystem.appString
import org.quran.app.model.ReadingPreferences
import org.quran.app.model.Chapter
import org.quran.app.model.StudyProgress
import org.quran.app.model.Verse
import org.quran.app.model.VerseId

@Composable
fun ReaderScreen(
    chapter: Chapter,
    verses: List<Verse>,
    initialAyah: Int,
    progress: StudyProgress,
    translationSummary: @Composable () -> Unit,
    translationForVerse: @Composable (VerseId) -> Unit,
    onRead: (VerseId) -> Unit,
    onBookmark: (VerseId) -> Unit,
    onPractice: (VerseId) -> Unit,
    onStudy: (VerseId) -> Unit,
    readingPreferences: ReadingPreferences = ReadingPreferences(),
    listeningState: ReaderListeningState = ReaderListeningState(),
    reciterName: String = "",
    onListen: (VerseId) -> Unit = {},
    onTogglePlayback: () -> Unit = {},
    onStop: () -> Unit = {},
    onRetry: () -> Unit = {},
) {
    // The chapter header occupies index zero; retain it when opening from the start.
    val initialIndex = if (initialAyah <= 1) 0 else initialAyah.coerceAtMost(verses.size)
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)

    val pageColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.background else QuranColors.ReadingPage
    BoxWithConstraints(Modifier.fillMaxSize().background(pageColor)) {
        val audioHeight = maxHeight * 0.45f
        Column(Modifier.fillMaxSize()) {
            if (listeningState.verseId != null) ReaderAudioCard(
                state = listeningState,
                modifier = Modifier.heightIn(max = audioHeight).verticalScroll(rememberScrollState()),
                reciterName = reciterName,
                onTogglePlayback = onTogglePlayback,
                onStop = onStop,
                onRetry = onRetry,
            )
            LazyColumn(
                state = listState,
                modifier = Modifier.weight(1f).fillMaxWidth().testTag("reader_list"),
                verticalArrangement = Arrangement.spacedBy(0.dp),
                contentPadding = PaddingValues(bottom = 24.dp),
            ) {
                item {
                    ReaderChapterHeader(chapter)
                    androidx.compose.foundation.layout.Box(Modifier.padding(horizontal = 16.dp)) { translationSummary() }
                }
                items(verses, key = { it.id.ayah }) { verse ->
                    VerseReaderItem(
                        verse = verse,
                        progress = progress,
                        translationForVerse = translationForVerse,
                        onRead = onRead,
                        onBookmark = onBookmark,
                        onPractice = onPractice,
                        onStudy = onStudy,
                        readingPreferences = readingPreferences,
                        onListen = onListen,
                    )
                }
            }
        }
    }
}
