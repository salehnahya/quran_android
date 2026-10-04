package org.quran.app

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule

/** Root destinations use the bottom bar on the index and overflow while reading. */
internal fun ComposeTestRule.navigateToRoot(label: String) {
    val destination = hasText(label) and hasClickAction()
    if (onAllNodes(destination).fetchSemanticsNodes().isEmpty()) {
        onNode(hasContentDescription("More options") or hasContentDescription("المزيد من الخيارات")).performClick()
    }
    onNode(destination).performClick()
}
