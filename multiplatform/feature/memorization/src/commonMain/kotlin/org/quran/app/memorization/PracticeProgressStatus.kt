package org.quran.app.memorization

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import org.quran.app.designsystem.QuranText

@Composable
internal fun PracticeProgressStatus(
    text: String,
    testTag: String,
) {
    QuranText(
        text,
        modifier = Modifier
            .testTag(testTag)
            .semantics { liveRegion = LiveRegionMode.Polite },
    )
}
