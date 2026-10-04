package org.quran.app

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavKey
import org.quran.app.designsystem.QuranIconButton
import org.quran.app.designsystem.QuranStrings
import org.quran.app.designsystem.QuranText
import org.quran.app.designsystem.appString

/** Secondary destinations stay available without occupying the reading page. */
@Composable
internal fun AppNavigationMenu(onNavigate: (NavKey) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        QuranIconButton(appString(QuranStrings.navigationOptions), { expanded = true }) {
            QuranText("⋮")
        }
        DropdownMenu(expanded, onDismissRequest = { expanded = false }) {
            listOf(
                Library to appString(QuranStrings.library),
                Qibla to appString(QuranStrings.qibla),
                Settings to appString(QuranStrings.settings),
            ).forEach { (route, label) ->
                DropdownMenuItem(
                    text = { QuranText(label) },
                    onClick = { expanded = false; onNavigate(route) },
                )
            }
        }
    }
}
