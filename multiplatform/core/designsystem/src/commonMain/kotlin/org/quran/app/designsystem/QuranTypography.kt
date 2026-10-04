package org.quran.app.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp

val QuranTypography = Typography(
    displayLarge = TextStyle(fontSize = 32.sp, lineHeight = 40.sp),
    headlineLarge = TextStyle(fontSize = 24.sp, lineHeight = 32.sp),
    headlineMedium = TextStyle(fontSize = 22.sp, lineHeight = 30.sp),
    titleLarge = TextStyle(fontSize = 22.sp, lineHeight = 30.sp, fontWeight = FontWeight.SemiBold),
    titleMedium = TextStyle(fontSize = 18.sp, lineHeight = 26.sp, fontWeight = FontWeight.SemiBold),
    bodyLarge = TextStyle(fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontSize = 14.sp, lineHeight = 22.sp),
    bodySmall = TextStyle(fontSize = 12.sp, lineHeight = 18.sp),
    labelLarge = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.Medium),
    labelSmall = TextStyle(fontSize = 12.sp, lineHeight = 18.sp, fontWeight = FontWeight.Medium),
)

val QuranArabicTextStyle = TextStyle(
    fontFamily = FontFamily.Serif,
    fontSize = 28.sp,
    lineHeight = 48.sp,
    textDirection = TextDirection.Rtl,
)
val QuranLargeArabicTextStyle = QuranArabicTextStyle.copy(fontSize = 34.sp, lineHeight = 56.sp)
