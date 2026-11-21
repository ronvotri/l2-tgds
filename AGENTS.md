# AGENTS.md — Instructions for Codex / assistants

## Latest verified release — 1.0.33 / Living Worlds v0.4.10
[OBSERVED 2026-11-21] Main/demo installed, startup 6 modules clean, 928 scripts compile, native loot 23 cases, HTML fallback and config UI pass; cumulative installer 1.0–1.0.32 plus rollback tested. Preserve immutable baseline and previous patches. See [docs/RELEASE-SHARE-1.0.33.md](docs/RELEASE-SHARE-1.0.33.md). Live gameplay/soak not run; AI wiki not exhaustive.


## Mission
Maintain this Vietnamese research-and-handoff repository about Lineage II Living Worlds, the owner's Vietnamese localization, AI bots, and client/mod compatibility. **Documentation-first** until actual server/client files and a safe test environment are provided.

## Non-negotiable rules
1. **Do not invent installed versions, code paths or tested results.** Clearly mark [UPSTREAM], [OBSERVED], [PLAN], [UNKNOWN], [ALPHA].
2. Never mutate or reset a production server, save, character database or translation files without an independently verified backup and rollback path.
3. Read `README.md`, `HANDOFF.md`, `docs/SOURCES-AND-CONTRIBUTING.md` and relevant `docs/QA-AND-BACKLOG.md` sections before coding.
4. **Separate version lines**: Living Worlds v0.4.2, Interlude+ v0.4.6 alpha, client protocol 737/746, Fermata and Mod Manager are different projects/version schemes.
5. Do not overwrite `FakePlayers.ini` or living-population `module.ini` with an old file; merge changed settings against new defaults. Never bulk-copy Interlude 737 `system` into 746.
6. Do not commit copyrighted client/server binaries, save databases, credentials, API keys, personal logs, external IPs or user data. Sanitize diagnostics.
7. Prefer staged, reversible changes and a concrete test plan. Maintain source citations/links in documentation.

## Workflow for future work
- Inspect repository and any user-provided backup or code first.
- Identify baseline server build, client protocol, module version and scope of Vietnamese translation.
- Create an issue/branch for a scoped change; record proposed files and risk.
- Implement only after identifying actual code/file format; avoid assumptions about private source files.
- Test in staging, preserve logs/metrics, add [OBSERVED] findings and update handoff + backlog.
- When work is incomplete, state blockers explicitly and leave a reproducible checkpoint.

## Current priorities
- [Issue #1](https://github.com/ronvotri/l2-tgds/issues/1): translation file manifest and verified backup.
- [Issue #2](https://github.com/ronvotri/l2-tgds/issues/2): fresh-world / level-progression benchmark.
- [Issue #3](https://github.com/ronvotri/l2-tgds/issues/3): 737/746/Fermata client compatibility.
- [Issue #4](https://github.com/ronvotri/l2-tgds/issues/4): Ollama latency/VRAM benchmark.
- [Issue #5](https://github.com/ronvotri/l2-tgds/issues/5): Interlude+ alpha sandbox evaluation.

## Definition of done
User-visible artifact or commit; docs reflect actual changes; no lost translations; build/version/log recorded; rollback known. **Writing a plan is not equivalent to modifying the game.**

## Share release policy (owner confirmed 2026-11-14)
- The full Vietnamese portable distribution already shared is release **1.0**, based on Living Worlds v0.4.5. Freeze its ZIP/checksum; never rebuild it from a played demo.
- Release **1.0.1** is the first cumulative patch, based on Living Worlds v0.4.6. Preserve each published patch and checksum.
- Every later patch must be **cumulative from 1.0**, including all earlier localization, launcher and compatibility changes still needed. A user installs 1.0 plus ONLY the latest patch; no sequential patch chain.
- Support both untouched 1.0 and already updated 1.0.x, plus re-running the same patch. Never downgrade a newer release or discard local settings/saves.
- Test those upgrade paths, configuration merge, protected-file checks, graceful save/shutdown and rollback. Changed core structure still requires rebuilding/merging the mods; never overlay an old JAR onto a new native core.
- Keep share release numbers (1.0.x), Living Worlds upstream versions and Interlude+ alpha versions distinct.
- Each update must include a short Vietnamese group announcement: version, 2–4 actual changes, latest-patch instruction, and save/AI preservation where verified. Save it with the release and return the copy-ready text to the owner; do not post to their group automatically.
- Public GitHub holds docs, original maintenance scripts, sanitized manifests/checksums and QA summaries. Keep game/AI binaries, databases, accounts, player memory and logs outside the repository.

## Latest verified checkpoint (2026-11-15)
Patch **1.0.2 / Living Worlds v0.4.7** is cumulative from 1.0. Genuine 1.0 and 1.0.1 upgrade paths converge; installer/config/save/rollback and server/AI/module smoke tests passed. Live dungeon gameplay remains NOT RUN. Preserve frozen 1.0 and 1.0.1 artifacts. See `docs/RELEASE-SHARE-1.0.2.md`.

## Latest local checkpoint — 1.0.3 / Living Worlds v0.4.9
See `docs/RELEASE-SHARE-1.0.3.md`. Tested cumulative installs from 1.0/1.0.1/1.0.2, config three-way defaults and overrides, guarded obsolete teleporter removal, runtime/AI/UI smoke. Keep localized AI entry compatibility until a separate modular migration is implemented and tested. Loot behavior is documented in `docs/BOT-LOOT.md`; do not silently force Living Population loot to players.

## Latest local checkpoint — 1.0.3
Optional FakePlayerPartyLootToPlayers is installed and default OFF, superseding the earlier loot-only documentation when enabled. See docs/RELEASE-SHARE-1.0.3.md. Preserve native behavior when OFF and bot-only economy. GitHub publication remains pending authentication; never claim a local commit was pushed.

Owner version correction 2026-11-19: Xenos HTML fix is release 1.0.31 (not 1.0.3); latest metadata and installer/demo retagged. See docs/RELEASE-SHARE-1.0.31.md. Preserve prior releases.

[OBSERVED 2026-11-19] Latest share release 1.0.32 adds optional local GM settings tools + account/item guide. Default unchanged; activate explicitly then restart. Preserve save/config. See docs/RELEASE-SHARE-1.0.32.md and maintenance/gm-tools. Public push still pending authentication.
