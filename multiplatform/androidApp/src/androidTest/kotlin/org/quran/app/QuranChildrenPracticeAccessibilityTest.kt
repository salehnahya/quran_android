package org.quran.app

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.quran.app.data.BundledQuranRepository
import org.quran.app.data.RecitationDataModule
import org.quran.app.data.platformAudioPlayer
import org.quran.app.data.initializePlatform
import org.quran.app.domain.AudioPlayer
import org.quran.app.designsystem.QuranTheme
import org.quran.app.memorization.MemorizationScreen
import org.quran.app.model.AppLanguage
import org.quran.app.model.StudyProgress
import org.quran.app.model.VerseId

/** Verifies native semantics and reachable controls, not screen-reader spoken output. */
@RunWith(AndroidJUnit4::class)
class QuranChildrenPracticeAccessibilityTest {
    @get:Rule val compose = createEmptyComposeRule()

    @Test fun arabicChildPracticeAnnouncesProgressAndKeepsRevealReachableAtLargeFont() {
        val quran = BundledQuranRepository()
        val verses = quran.verses(1)
        val progress = mutableStateOf(StudyProgress(childMode = true, language = AppLanguage.ARABIC))
        initializePlatform(InstrumentationRegistry.getInstrumentation().targetContext)
        var ownedModule: RecitationDataModule? = null
        var ownedScenario: ActivityScenario<MainActivity>? = null
        var ownedPlayer: AudioPlayer? = null
        try {
            val module = RecitationDataModule().also { ownedModule = it }
            val scenario = ActivityScenario.launch(MainActivity::class.java).also { ownedScenario = it }
            val player = compose.runOnIdle { platformAudioPlayer() }.also { ownedPlayer = it }
            scenario.onActivity { activity ->
                activity.setContent {
                    val density = LocalDensity.current
                    QuranTheme {
                        CompositionLocalProvider(
                            AppLocale provides AppLanguage.ARABIC,
                            LocalLayoutDirection provides LayoutDirection.Rtl,
                            LocalDensity provides Density(density.density, 1.5f),
                        ) {
                            Box(Modifier.height(240.dp).fillMaxWidth()) {
                                MemorizationScreen(
                                    verse = verses.first(), chapterVerses = verses,
                                    progress = progress.value,
                                    onMemorized = { progress.value = progress.value.copy(memorized = progress.value.memorized + it) },
                                    audioPlayer = player, onImport = {},
                                    recitationRepository = module.repository,
                                    recitationStorage = module.storage,
                                    selectedReciterId = "alafasy", onReciterSelected = {},
                                )
                            }
                        }
                    }
                }
            }
            val polite = SemanticsMatcher.expectValue(SemanticsProperties.LiveRegion, LiveRegionMode.Polite)
            compose.onNodeWithText("إخفاء الآية").performScrollTo().performClick()
            compose.onNodeWithText(verses.first().arabic).assertDoesNotExist()
            compose.onNodeWithText("اقرأ من الذاكرة ثم أظهر الآية للمراجعة.").assertIsDisplayed()
            compose.onNodeWithText("إظهار الآية").performScrollTo().performClick()
            compose.onNodeWithText(verses.first().arabic).performScrollTo().assertIsDisplayed()
            compose.onNodeWithContentDescription("إضافة الآية التالية إلى النطاق").performScrollTo().performClick()
            compose.onNodeWithTag("practice_position").performScrollTo()
                .assert(polite).assertTextEquals("الآية 1 من 2 في هذا النطاق")
            compose.onNodeWithText("كررت هذه الآية").performScrollTo().performClick()
            compose.onNodeWithTag("practice_repetitions").performScrollTo()
                .assert(polite).assertTextEquals("التكرارات: 1")
            compose.onNodeWithText("حفظت هذه الآية").performScrollTo().performClick()
            compose.onNodeWithTag("practice_position").performScrollTo()
                .assert(polite).assertTextEquals("الآية 2 من 2 في هذا النطاق")
            compose.onNodeWithTag("practice_repetitions").performScrollTo()
                .assert(polite).assertTextEquals("التكرارات: 0")
            compose.runOnIdle {
                org.junit.Assert.assertEquals(setOf(VerseId(1, 1)), progress.value.memorized)
            }
        } finally {
            try {
                ownedScenario?.close()
            } finally {
                try {
                    InstrumentationRegistry.getInstrumentation().runOnMainSync { ownedPlayer?.release() }
                } finally {
                    ownedModule?.close()
                }
            }
        }
    }
}
