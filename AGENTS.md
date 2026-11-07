# AGENTS.md — Instructions for Codex / assistants

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
