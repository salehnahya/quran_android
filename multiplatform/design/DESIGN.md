# Original Quran Android design direction

The user explicitly prefers the original native app over the Stitch redesign. The original repository is the visual authority. Stitch concepts remain historical references and must not override this decision.

Palette: use the original neutral #FAF8F7 surface, #212529 text and #00838F teal accent; dark mode uses #212121 and #B2DFDB. The reader uses the original #FFF4CB paper color in light mode. Source: app/src/main/res/values/colors.xml and values-night/colors.xml.

Index: fixed Surah/Juz/Bookmarks tabs with an underline, compact continue-reading row, plain numbered 72dp-minimum rows, verse counts and dividers. No oversized hero card, colored number tile or repeated offline badge. Source: quran_index.xml and index_sura_row.xml. Canonical metadata is unchanged; page numbers are not invented.

Reader: full-width page, compact centered Arabic chapter header, Arabic text before translations, compact ayah actions and unobtrusive dividers. Global navigation moves to a localized overflow menu while reading/practicing/studying, retaining Navigation 3 routes and Back behavior. Main sections retain their bottom navigation.

Components: real EN/AR XML labels, minimum 48dp touch actions, RTL layout, flexible text wrapping, focused files, shared scaffold/buttons/text/bottom sheets. Reduced corner radii and system interface typography preserve a restrained native appearance. Quran Arabic typography and corpus remain unchanged.

The KMP reader is a text reader. It does not reproduce the original certified image Mushaf page layout yet; page assets, coordinate metadata and rendering require a separate migration. Do not describe these changes as pixel-identical native parity.

Evidence: source compared to the original resources. Combined build and current-head native screenshot matrix are required after this batch. design-preview.html is an interactive component mockup, not native screenshot acceptance. Prior screenshot evidence applies to the earlier design only.
