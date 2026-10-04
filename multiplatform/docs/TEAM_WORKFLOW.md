# Delivery workflow

Use a small team: GPT-6.1 Sol for technical leadership and review, plus one GPT-6 Luna implementation agent for a bounded task. The coordinating lead integrates and verifies. Bring in an independent reviewer at PR checkpoints; end that review assignment once its findings are delivered. Do not keep separate permanent architecture, QA, security, and design agents or duplicate investigations across agents.

Use ECC Android clean architecture, Compose Multiplatform, Kotlin testing, and design-system skills where relevant. Navigation 3 remains required. Domain contracts remain framework independent; platform adapters and networking belong in data. Keep models and reusable components in focused files.

Finish the combined edits before a build, as requested. Tests cover user-visible behavior, cancellation, persistence, source integrity, and platform lifecycle; no framework churn merely to mirror a skill example.

PR acceptance requires substantive independent review and passing Android/tests, iOS framework, and Android runtime checks for the current head. Never merge drafts or bypass review protections. AI integration remains deferred.

## ECC ownership and token discipline

Use ECC team-agent-orchestration contracts and code-reviewer checks. The lead owns architecture, host wiring, integration and evidence. One Luna worker owns at most one explicitly bounded file group; design-system and Compose skills guide implementation. A temporary Sol 6.1 reviewer inspects the combined diff and native evidence, then ends the assignment. No permanent separate designer/architect/QA/security squad.

Pass a short task contract with paths, acceptance criteria and forbidden edits; avoid full-history forks and duplicate repository scans. Use graph discovery first, compact outputs, and one build after combined edits. Rebuild only for concrete failures or material changes. Preserve substantive independent review and required CI gates.

| Work item | Owner | State | Scope / gate |
| --- | --- | --- | --- |
| Original native index styling | Luna worker | Review | Three index component files; labels/RTL/48dp preserved |
| Original native reading/design integration | Lead | Running | Palette, scaffold, reader, overflow, behavior/visual verification |
| Combined native-direction review | Temporary Sol 6.1 | Pending | Independent current-head review and CI screenshots; usage availability required |
