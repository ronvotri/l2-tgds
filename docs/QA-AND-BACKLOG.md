# QA & backlog triển khai

Quy ước: **P0** = bảo vệ dữ liệu / blocker, **P1** = bắt buộc trước khi đưa vào sử dụng nhiều máy, **P2** = tính năng mở rộng. Dấu checkbox là **chưa test trong repo**, không phải lỗi đã được xác minh.

## Kiểm chứng mới nhất — 1.0.2 / Living Worlds v0.4.7
[OBSERVED 2026-11-15] Đã test nâng từ bộ tệp thật 1.0 và từ 1.0.1 cho cùng payload; giữ save/config/giới tính, cài lặp, chống downgrade/code drift và rollback. Bản gốc/demo khởi động đủ 6 module và AI, inventory không đổi; 437 tệp dữ liệu demo không đổi bởi bước cài. [Báo cáo](RELEASE-SHARE-1.0.2.md). Chưa chơi thử dungeon.

## Kiểm chứng trước — 1.0.1 / Living Worlds v0.4.6
[OBSERVED, 2026-11-14] Đã biên dịch 927 scripts, khởi động hai bản đủ 6 module và AI demo, kiểm tra config, hash Việt hóa, giữ inventory/save khi cài, cài lặp và rollback. [Báo cáo chi tiết](RELEASE-SHARE-1.0.1.md).
- [ ] Đánh raid thực tế với 4 đội phụ, kiểm tra vị trí/né AOE và loot.
- [ ] Soak memory/GC nhiều giờ để kiểm chứng fix upstream.
- [ ] Với patch tiếp theo: test cùng gói từ 1.0 sạch và 1.0.1 đang dùng; không yêu cầu chuỗi patch trung gian.
Các mục TODO cũ bên dưới chưa được coi là hoàn tất chỉ vì smoke test khởi động thành công.

## Bản v0.4.7-patch — đã triển khai 1.0.2, còn gameplay QA
- [x] **P0-07** [OBSERVED 1.0.2] Đã đối chiếu ZIP, backup, merge và giữ giá trị config.  Backup và merge 5 file patch thay (`living-population/module.ini`, `General.ini`, `Rates.ini`, `Feature.ini`, `test-gear/module.ini`); tránh mất rates, CustomBuyListLoad, Grand Boss policy và config riêng.
- [x] **P0-08** [OBSERVED 1.0.2] Đã test đủ hai đường nâng cấp và installer.  Khi xây patch Việt hóa **cộng dồn** mới: test trên **1.0 sạch** và **1.0.1**, cài lặp, rollback, checksums và save/inventory/AI modules; [báo cáo đã build/test](RELEASE-SHARE-1.0.2.md).
- [ ] **P1-34** Party Bot đi cùng người chơi vào Elven Ruins có trả tiền Gatekeeper, đến vị trí **phía trong**; không đứng ngoài/dưới biển.
- [ ] **P1-35** Bot Elven Fortress không săn ở mặt đất bên trên; monster dungeon ở đúng spawn; bot không chase tầng khác.
- [ ] **P1-36** Tại ngưỡng bot rời newbie: Human Talking Island/Dark Elf lv9, Orc/Dwarf lv10, Elf lv11; lv5+ khi bãi đông chuyển ra fields.
- [ ] **P1-37** Party Living Bot tối đa 3 (<20), 5 (20–39), 7 (40–51), 9 (52+), kick level cao nhất khi vượt; test có player và không có player.
- [ ] **P1-38** QA Việt hóa câu thoại Party/dungeon/Gatekeeper, không spawn/chase sai tầng, không giảm EXP/SP bất thường khi farm.

**Chi tiết [UPSTREAM] và kịch bản test:** [Living Worlds v0.4.7-patch](RELEASE-LIVING-WORLDS-0.4.7-PATCH.md).

## Ghi nhận 2026-11-17 — Living Worlds v0.4.9 Combat/Rules
- [ ] **P0-NEW** Xác minh gói release **v0.4.9** và package, các file patch thay, chưa gộp vào 1.0.2 nếu chưa có source/bản cài.
- [ ] **P1-NEW** Magic Crit ×4 PvP/PvE, Interlude EXP/Party bonus/Raid drop & independent rolls, shield/polearm 0–360, WIT resurrect, Karma/death, Clan C5/Seal of Strife.
- [ ] **P1-NEW** Clan Hall auction/rent, Olympiad manager, Festival, 60+ Mobius bugfix, Dark Elf dialogues/tutorial, `Player.ini` defaults `CraftingInCombat=False`, `DisableCp=False`.
[Tài liệu và bài test chi tiết](RELEASE-LIVING-WORLDS-0.4.9-COMBAT-RULES.md) · [Issue #12](https://github.com/ronvotri/l2-tgds/issues/12). **Trạng thái: NOT RUN cho v0.4.9**.

## V0.4.10 — Config preservation & Community Board (UPSTREAM / NOT RUN)
- [ ] **P0-0410-1** Xác minh patch/Full và `LivingWorld.exe` v0.4.10 chính thức; checksum trước khi dùng.
- [ ] **P0-0410-2** Backup server, SQL/save, client Việt hóa, AI/runtime riêng (config-backup chỉ chứa config đã đổi, không thay thế bản sao world).
- [ ] **P0-0410-3** Với 5 file: `FakePlayers.ini`, `PhantomOlympiad.ini`, `CommunityBoard.ini`, `Player.ini`, `living-population/clans.ini`, test sao lưu trong `config-backup\\before-v0.4.10\\` và merge các giá trị riêng.
- [ ] **P0-0410-4** Test giá trị `FakePlayerBaseNpcId` và Olympiad roster size đã chỉnh/không chỉnh; chưa chỉnh nhận default mới.
- [ ] **P0-0410-5** Tích hợp patch cộng dồn tương lai phải test từ 1.0 sạch và từ 1.0.2, cài lặp, rollback; **không coi README update là build đã xong**.
- [ ] **P1-0410-1** Community Board: tự ẩn button của Buffer/Merchant/Gatekeeper/Delevel/Premium khi dịch vụ tắt; hide list các button name + module tabs; không lỗi Việt hóa.
- [ ] **P1-0410-2** `DaysBeforeAcceptNewMemberWhenDismissed`: 0 bỏ chờ, default giữ luật cũ; test sau dismiss và restart.
- [ ] **P1-0410-3** Launcher mới chia core/custom modules; giữ chức năng one-click và runtime.

[Changelog v0.4.10 trích](RELEASE-LIVING-WORLDS-0.4.10-PARTIAL.md) · [Issue #13](https://github.com/ronvotri/l2-tgds/issues/13).

## P0 — An toàn bản Việt hóa và tiến trình
- [ ] **P0-01** Kiểm kê bản server hiện cài, module, client protocol, tên và vị trí các file Việt hóa; lập checksum manifest.
- [ ] **P0-02** Backup có thể restore: server đầy đủ, DB, client Việt hóa, bản cấu hình tùy chỉnh, module, dữ liệu game không công khai.
- [ ] **P0-03** Dựng **staging** từ backup, cập nhật mục tiêu (v0.4.7-patch hoặc mới hơn khi có) không làm thay đổi production.
- [ ] **P0-04a** v0.4.5: patch ghi đè module.ini, `General.ini`, `Rates.ini`; **`CustomBuyListLoad=False` mặc định**, test custom buylist và tất cả file Việt hóa/custom NPC/shop; merge config thay vì ghi đè bản cũ.
- [ ] **P0-04** Diff `FakePlayers.ini` và `living-population/config/module.ini` khi relevant; **v0.4.4 patch thay `Feature.ini` + living-population `module.ini`**. Chuyển từng setting, không chép nguyên file cũ.
- [ ] **P0-05** Test NPC, quest, tên skill, item, system message, chat, Community Board, dấu Việt trước/sau update.
- [ ] **P0-06** Test Stop/restart/restore, kiểm tra level, vật phẩm, Clan, bot memory, raid timers, warehouse, Olympiad reward.

## P1 — Thế giới bắt đầu lại cùng bot
- [ ] **P1-01** So sánh **Wipe Server** và **Reset Living Population**, chụp log/kết quả, không xóa nhầm world chính.
- [ ] **P1-02** Kiểm tra level bắt đầu/đường phát triển của bot mới; thống kê ở 0/1/3/5/10/24 giờ.
- [ ] **P1-03** Test 100/200/500 bots, Cold Sim 4.0 on/off so tài nguyên; All bots live off/on trong staging.
- [ ] **P1-04** Player lvl 1–20: hỏi shout tìm bot gần cấp, mời/nhận party, quest, class master.
- [ ] **P1-04a** Hotfix 0.4.3: `me`/`inv me` trong shout → invite thật; `yes`/`im at Gludio gk` whisper → bot gửi invite, ETA, đi/teleport đến; `ill come to u` → bot chờ và đánh trả khi bị tấn công; `/invite BotName` từ xa; Party có leader.
- [ ] **P1-04b** Không còn lời hứa suông: thiếu leader, lệch cấp → thông báo đúng lý do, không tự nói đang đến.
- [ ] **P1-05** Healer/support buddy/pet/servitor: heal, buff, resurrect, tránh heal trùng, summoner dùng crystal/beastshots.
- [ ] **P1-05a** Hotfix 0.4.3: Heal/Greater Heal/Greater Group Heal hoàn tất khi casting speed thấp (WIT âm, thiếu gear, bị slow), thử cả alt companion và recruited bot.
- [ ] **P1-06** Clan bot: tạo, gia nhập `.clanjoin`, quest, warehouse, grudge, war/alliance, siege; ghi quan hệ sau restart.
- [ ] **P1-06a** v0.4.3: reset population **trên staging** giải tán Living Clans, giải phóng castle, xóa records, tách bot khỏi player clan; bot mới lập clan bình thường.
- [ ] **P1-06b** Bot gia nhập đúng Royal Guard / Order of Knights / Academy, không bị nhét vào main clan.
- [ ] **P1-07** Shout channel (event 30s, câu hỏi world thật; **Town Fake Trade Chat default True từ v0.4.3**, off ở v0.4.2), kiểm tra tiếng Việt có hoạt động không.
- [ ] **P1-07a** Quest stall v0.4.3: `QuestStallMinutes` mặc định 180; class/pet quest hoàn tất theo timer và có reward, side quest drop; bot hunt khởi đầu gần quái nhiệm vụ.
- [ ] **P1-07b** Khóa `LivingPopulation.json` trong staging: các save town stock, market, clan, siege, raid vẫn hoàn tất; test shutdown/restart.
- [ ] **P1-08** Thử 2+ client LAN: connect/party/trade/chat/teleport/logout/reconnect, firewall và IP nội bộ.

## P1 — v0.4.4: world, economy, party, Seven Signs
- [ ] **P1-13** Map: ~50 hunting grounds, các sub-area, bot đi giữa khu, quest hunting đúng quái/level, bot cũ chuyển area phù hợp.
- [ ] **P1-14** Crowd: bot chia sẻ bãi, ưu tiên Party khi đông, bám bãi >=30 phút, không tụ cả 500 bot vào một khu.
- [ ] **P1-15** Seven Signs: Dawn/Dusk, 6 Catacomb/8 Necropolis, stones→AA, cycle 7+7, Festival cùng phe >=5, không tính bot votes/score mặc định.
- [ ] **P1-16** Mammon và economy AA: unseal gear đúng upgrade, NPC price, AlwaysSpawned off.
- [ ] **P1-17** Pet/mount: Hatchling→Strider, Wyvern, Baby Buffalo/Cougar/Kookaburra, Beast Farm, warehouse.
- [ ] **P1-18** Town Private Buy/Sell Stores, Dwarven Manufacture 10% fee, shot purchases, không ghost stores.
- [ ] **P1-19** Hot Bot Party Matching room/waiting list, Cold→Hot vào party, Shout và recommend.
- [ ] **P1-20** Grand Boss `BotGrandBosses=True` default; bot không đánh khi player ở lair/đang đánh; False bảo vệ boss player; respawn không sai.
- [ ] **P1-21** Gear hợp lệ từ shop/drop/quest/craft; bỏ GM-only gear/hero weapon sai, BridgeWeapon, Dual Sword craft, recipe goals, supply smoothing.
- [ ] **P1-22** Soak memory/GC/CPU 1/3/6/12/24h và không freeze Cold Sim toàn cục; kiểm tra no phantom drop near Primeval Isle.
- [ ] **P1-23** Admin gear display; test-gear & TestGearAll luôn off production.
- [ ] **P1-24** Dấu Việt trong NPC Priest, Seven Signs, Festival, Mammon, store, party matching, mount/pet UI.

## P1 — v0.4.5: chính trị Clan và tiến trình endgame
- [ ] **P1-25** Castle management: tax, manor, gates/walls, mercenaries dùng Clan treasury; castle vô chủ không bán seed/mua crops, `ManorSteward` True thay đổi theo mong đợi.
- [ ] **P1-26** Clan Hall auctions & siege tại 6 khu, không động tới hall do player sở hữu/đăng ký; siege state tồn tại sau restart.
- [ ] **P1-27** Clan Skills lấy Reputation, AcademyGraduates chuyển main clan nếu còn slot.
- [ ] **P1-28** Bot Noblesse progression, ColdOlympiad mô phỏng, HotOlympiad queue thật, BotHeroes vũ khí/chat, player autopilot reward.
- [ ] **P1-29** BotDyes, BotAugmentation, BotFishingRewards, học Expand Inventory/Storage.
- [ ] **P1-30** RaceShares cho bot sinh mới, NamedBots=False và các bot aiSateriok/aiSonOfZion/aiTeras.
- [ ] **P1-31** Rates.ini: never-drop, No skill books, lượng drop chỉ nhân cho item stackable; `l2admin` rate pages đồng bộ; custom buylists chỉ được nạp khi `CustomBuyListLoad=True`.
- [ ] **P1-32** Navigation Elven Village/HERETIC catacomb nhiều tầng; Cursed Weapon bot field hunting, Queen Ant nurses trước; XML warnings.
- [ ] **P1-33** Việt hóa NPC Manor Manager, Clan Hall, Symbol Maker, Fishing Guild, Olympiad, Hero channel; server/client font và chuỗi mới.

## P1 — Client & AI

**Cảnh báo chung:** không có bài test nào được đánh dấu PASS chỉ dựa trên nội dung release note. [Bản hotfix + checklist chi tiết](RELEASE-LIVING-WORLDS-0.4.3.md).
- [ ] **P1-09** Dựng ma trận Interlude 737 Việt hóa / 746 Archive / Fermata; so hình ảnh, UI, quest, fonts, crashes.
- [ ] **P1-10** So tốc độ Ollama `gemma3:12b` vs model 4B; đo TTFT, token/sec, VRAM, 1/2/4 request.
- [ ] **P1-11** Kiểm tra `ollama ps` thực tế có GPU/CPU offload không; v0.4.2 giữ model nạp giải quyết timeout?
- [ ] **P1-12** Collect Logs: ZIP chứa cả hai server, phiên hiện tại và trước, không có secrets trước khi chia sẻ.

## P2 — Mở rộng / phát triển
- [ ] **P2-01** Xây script/Module telemetry đọc snapshot server để vẽ phân bố level bot và các sự kiện clan.
- [ ] **P2-02** Đánh giá nhu cầu module **giới hạn tốc độ tiến trình bot** sau khi benchmark, không mặc định upstream có sẵn.
- [ ] **P2-03** Việt hóa câu thoại mới và 1.700 template/profile mảnh v0.4.2 sau khi kiểm kê vị trí thật.
- [ ] **P2-04** Thử **Interlude+ v0.4.6 alpha** chỉ trên staging Archive client sạch; làm bảng compatibility riêng.
- [ ] **P2-05** Thử L2 Mod Manager v1.0.2 trên bản sao client, không patch đè file Việt hóa.

**V0.4.5:** [release và QA](RELEASE-LIVING-WORLDS-0.4.5.md).

**V0.4.4:** [release study và QA chi tiết](RELEASE-LIVING-WORLDS-0.4.4.md).

## Mẫu kết quả test

```markdown
### TEST-ID: P1-03 / 2026-xx-xx
- Trạng thái: PASS / FAIL / BLOCKED / NOT RUN
- Living Worlds version + commit/build:
- Module versions:
- Client/protocol:
- Host CPU/GPU/VRAM/RAM:
- Số bots; ColdModel; All bots live:
- Các bước tái hiện:
- Kết quả kỳ vọng:
- Kết quả thực tế:
- Log ZIP/ảnh/video: (đường dẫn bảo mật, đã che thông tin riêng)
- Bug upstream liên quan / ngày báo:
- Người xác nhận:
```

**Quy tắc:** Chỉ đổi item từ TODO sang DONE khi đã có log hoặc ảnh với version chính xác. Release note **không tính** là test pass.
