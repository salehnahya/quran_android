package org.quran.app.designsystem

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

/** Palette taken from the original Android app's values/colors.xml and values-night/colors.xml. */
object QuranColors {
    val Accent = Color(0xFF00838F)
    val ReadingPage = Color(0xFFFFF4CB)
    val Light = lightColorScheme(
        primary = Accent, onPrimary = Color.White,
        primaryContainer = Color(0xFFE0F2F1), onPrimaryContainer = Color(0xFF004D40),
        secondary = Accent, onSecondary = Color.White,
        secondaryContainer = Color(0xFFE8F4F8), onSecondaryContainer = Color(0xFF212529),
        background = Color(0xFFFAF8F7), onBackground = Color(0xFF212529),
        surface = Color(0xFFFAF8F7), onSurface = Color(0xFF212529),
        surfaceVariant = Color(0xFFF2F2F2), onSurfaceVariant = Color(0xFF6C757D),
        outline = Color(0xFF6C757D), outlineVariant = Color(0xFFE8E8E8),
        surfaceContainerLowest = Color.White, surfaceContainerLow = Color(0xFFFAF8F7),
        surfaceContainer = Color(0xFFFAF8F7), surfaceContainerHigh = Color(0xFFF2F2F2),
        surfaceContainerHighest = Color(0xFFDEE2E6), surfaceTint = Accent,
    )
    val Dark = darkColorScheme(
        primary = Color(0xFFB2DFDB), onPrimary = Color(0xFF00363B),
        primaryContainer = Color(0xFF37474F), onPrimaryContainer = Color(0xFFB2DFDB),
        secondary = Color(0xFFB2DFDB), onSecondary = Color(0xFF00363B),
        secondaryContainer = Color(0xFF303030), onSecondaryContainer = Color.White,
        background = Color(0xFF212121), onBackground = Color.White,
        surface = Color(0xFF212121), onSurface = Color.White,
        surfaceVariant = Color(0xFF333333), onSurfaceVariant = Color(0xFFB3B3B3),
        outline = Color(0xFFB3B3B3), outlineVariant = Color(0xFF424242),
        surfaceContainerLowest = Color(0xFF1A1A1A), surfaceContainerLow = Color(0xFF212121),
        surfaceContainer = Color(0xFF212121), surfaceContainerHigh = Color(0xFF303030),
        surfaceContainerHighest = Color(0xFF424242), surfaceTint = Color(0xFFB2DFDB),
    )
    val Forest = Accent
    val Ivory = ReadingPage
    val Brass = Accent
}

val Forest = QuranColors.Forest
val Ivory = QuranColors.Ivory
val Brass = QuranColors.Brass
