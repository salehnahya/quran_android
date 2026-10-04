package org.quran.app.reader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.quran.app.designsystem.QuranStrings
import org.quran.app.designsystem.QuranText
import org.quran.app.designsystem.QuranTextButton
import org.quran.app.designsystem.QuranTextVariant
import org.quran.app.designsystem.appString
import org.quran.app.model.VerseId

@Composable
internal fun ContinueReadingCard(lastRead: VerseId, onOpen: (Int, Int) -> Unit) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().heightIn(min = 72.dp).padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                QuranText(appString(QuranStrings.continueReading), variant = QuranTextVariant.Body)
                QuranText("${lastRead.surah}:${lastRead.ayah}", variant = QuranTextVariant.Supporting)
            }
            QuranTextButton(
                text = appString(QuranStrings.openLastRead),
                onClick = { onOpen(lastRead.surah, lastRead.ayah) },
            )
        }
        HorizontalDivider()
    }
}
