package org.quran.app.reader

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.quran.app.designsystem.QuranStrings
import org.quran.app.designsystem.QuranText
import org.quran.app.designsystem.appString

@Composable
internal fun LibraryTabs(section: LibrarySection, onSelect: (LibrarySection) -> Unit) {
    val sections = LibrarySection.entries
    val selectedIndex = sections.indexOf(section).coerceAtLeast(0)
    Column(Modifier.fillMaxWidth()) {
        ScrollableTabRow(
            selectedTabIndex = selectedIndex,
            edgePadding = 0.dp,
            divider = {},
        ) {
            for (item in sections) {
                val title = when (item) {
                    LibrarySection.SURAHS -> appString(QuranStrings.surahTab)
                    LibrarySection.JUZ -> appString(QuranStrings.juzTab)
                    LibrarySection.BOOKMARKS -> appString(QuranStrings.bookmarksTab)
                }
                Tab(
                    selected = section == item,
                    onClick = { onSelect(item) },
                    modifier = Modifier.heightIn(min = 48.dp),
                    text = { QuranText(title) },
                )
            }
        }
        HorizontalDivider()
    }
}
