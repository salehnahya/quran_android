package org.quran.app.reader

import androidx.compose.runtime.setValue

import androidx.compose.runtime.getValue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import org.quran.app.designsystem.QuranBottomSheet
import org.quran.app.designsystem.QuranTextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import org.quran.app.designsystem.ArabicVerse
import org.quran.app.designsystem.QuranStrings
import org.quran.app.designsystem.appString
import org.quran.app.model.ReadingPreferences
import org.quran.app.model.StudyProgress
import org.quran.app.model.Verse
import org.quran.app.model.VerseId

@Composable
internal fun VerseReaderItem(
    verse: Verse,
    progress: StudyProgress,
    translationForVerse: @Composable (VerseId) -> Unit,
    onRead: (VerseId) -> Unit,
    onBookmark: (VerseId) -> Unit,
    onPractice: (VerseId) -> Unit,
    onStudy: (VerseId) -> Unit,
    readingPreferences: ReadingPreferences = ReadingPreferences(),
    onListen: (VerseId) -> Unit = {},
) {
    var actionsVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().testTag("reader_verse_${verse.id.surah}_${verse.id.ayah}").padding(horizontal = 16.dp, vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        FlowRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("${verse.id.surah}:${verse.id.ayah}", style = MaterialTheme.typography.labelLarge)
            QuranTextButton(appString(QuranStrings.moreActions), modifier = Modifier.testTag("verse_actions_${verse.id.surah}_${verse.id.ayah}"), onClick = { actionsVisible = true })
        }
        ArabicVerse(verse.arabic, progress.childMode, textSize = readingPreferences.arabicTextSize)
        translationForVerse(verse.id)
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    }

    if (actionsVisible) {
        QuranBottomSheet(
            title = "${verse.id.surah}:${verse.id.ayah}",
            onDismiss = { actionsVisible = false },
        ) {
            QuranTextButton(appString(QuranStrings.markAsRead), onClick = { onRead(verse.id); actionsVisible = false })
            QuranTextButton(
                appString(if (verse.id in progress.bookmarks) QuranStrings.removeBookmark else QuranStrings.saveBookmark),
                onClick = { onBookmark(verse.id); actionsVisible = false },
            )
            QuranTextButton(appString(QuranStrings.practice), onClick = { onPractice(verse.id); actionsVisible = false })
            QuranTextButton(appString(QuranStrings.readerAudioListen), onClick = { onListen(verse.id); actionsVisible = false })
            QuranTextButton(appString(QuranStrings.study), onClick = { onStudy(verse.id); actionsVisible = false })
            QuranTextButton(appString(QuranStrings.back), onClick = { actionsVisible = false })
        }
    }
}
