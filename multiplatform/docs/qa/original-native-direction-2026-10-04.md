# Original-native-direction checkpoint — 2026-10-04

The user prefers the original Quran Android design. This batch uses its checked-in quran_index.xml, index_sura_row.xml and light/dark colors.xml as the authority. Neutral/teal surfaces, flat numbered index rows and underline tabs replace the rejected decorative design. Tabs stay visible above the scrollable index. The reader uses original paper color, a compact chapter header, full-width verse content and localized overflow destinations instead of persistent reading bottom navigation. The shared toolbar grows with its heading.

ECC design-system, Compose Multiplatform, team-agent-orchestration and code-reviewer guidance were applied using one bounded Luna worker and temporary Sol 6.1 review. File ownership and review gates are in TEAM_WORKFLOW.md. Interface labels are actual EN/AR XML; source text, reading typography, persisted progress and Navigation 3 routes are unchanged.

Combined verification passed: 109 JVM tests with zero failures/errors, shared UI compilation and Android app/test APK assembly. An invalid RowScope weight import was caught and repaired. Review identified missing long-title/three-digit index coverage; a native matrix extension now checks canonical row 114 and each locale's longest chapter title in light/dark/150% font cases. Toolbar adaptation and that extension compiled successfully. Independent source review cleared the final delta.

Fresh native runtime/screenshots and iOS CI remain pending at publication. Existing screenshots validate the prior design only. The HTML preview is a component mockup, not native evidence. The shared reader remains verified text, not a migrated image Mushaf renderer. Physical screen-reader output, compass and iOS runtime remain separate requirements.
