# Niên biểu cập nhật Living Worlds (tổng hợp đến 2026-11-17)

> **[UPSTREAM]** Các mục dưới đây dựa trên changelog/thông báo cộng đồng được người dùng chép vào cuộc trò chuyện. Các mục lịch sử không mặc nhiên là bản đã cài. Riêng v0.4.6 đã có [smoke test Việt hóa 1.0.1](RELEASE-SHARE-1.0.1.md). Không tự lấp các phiên bản thiếu bằng suy đoán. Mốc thời gian là ngày bài đăng khi có trong nội dung; một số mốc không có ngày chính xác.

## Dòng thời gian

| Bản | Thay đổi quan trọng | Ghi chú bảo trì |
|---|---|---|
| v0.2.2 (09/10) | Dừng server lưu EXP, vị trí, phantoms, raid, Olympiad, clans; điều chỉnh PopulationSize, HandoffMaxHotBots, PhantomMaxCount | Yêu cầu cập nhật server + launcher |
| v0.2.3 (09/10) | Bot đi cầu, Gatekeeper thay vì bơi sai tuyến; party PvP giữ mục tiêu | Launcher v1.7 cho sửa rollback |
| v0.2.4 (09/10) | Living Population bật mặc định; tăng số bot dễ hơn, giới hạn phantom từ 200 lên 5000; mua shots, thuốc, gear thông minh | Thay living-population module.ini và FakePlayers.ini |
| v0.2.5 (11/10) | Sửa bot đứng yên lúc người chơi chết, nhặt đồ lặp, sweep, spawn chồng vị trí, cập nhật trang bị | Sửa lỗi ổn định |
| v0.2.6 (11/10) | Town fake bot không đánh nhau; Field Phantoms tắt mặc định; sửa Dimensional Rift | FakePlayers.ini có thể bị ghi đè |
| v0.2.7 (11/10) | Loot bot đi vào chợ thành; bot nhận tiền khi bán; mua gear theo nghề, ưu tiên SP/skill | module.ini có setting hàng tồn |
| v0.2.8 (15/10) | Sateya chế tạo shot và gear theo đơn; bot party tự săn raid; thử nghiệm Bot Siege (tắt mặc định) | Bản phát hành chuyển khỏi GitHub sang máy chủ riêng của tác giả |
| v0.2.9 (16/10) | Sửa teleport, shots, loot, kéo đàn quái, party, clan crest; modules được lưu trữ lại | 11 lỗi được công bố |
| v0.3.0 (16/10) | Chiến đấu thông minh, summoner/healer/spoiler; khu Elven Ruins; All bots live tùy chọn; launcher mini + Wipe Server | All bots live tắt mặc định và tốn tài nguyên; patch có thể ghi đè nhiều file |
| v0.3.1 (~21/10) | Warehouse, kinh tế bot thật, tự party, artisan craft, Work Orders; cải thiện monster, boss, quests; reset population | Khuyến nghị reset Living Population bằng Config Editor |
| v0.3.2 (đã được chia sẻ) | Bot trong thành đi lại tự nhiên; sửa freeze sau ~1 giờ, quái giật/mất kết nối teleport; recovery scroll, loot, shop Sateya | Có thể trả GridsAlwaysOn về False nếu từng dùng workaround |
| v0.3.3 (hotfix) | Đường đi qua phố ở 16 thành, vào shop bằng cửa chính, không đứng sai phía quầy | Chỉnh AI di chuyển |
| Bản vá chưa xác định số | Thêm Collect logs ZIP phiên hiện tại + trước, cả hai server; loại vũ khí chỉ dành cho quái/guard khỏi bot | Chưa xác nhận version cụ thể |
| v0.3.7-patch | Sửa phantom Olympiad xuất hiện sai tại Rune; spoiler Sweep; bot giữ luật tải trọng; giới hạn log từ 20×100MB thành 5×20MB | Cleanup log không xóa world data |
| v0.3.8 | Living Clans, gia nhập clan bot, clan warehouse/quest/siege; bot làm quest class, Saga, pet, fishing; **Cold Sim 4.0** đánh quái thật ngoài tầm nhìn | ColdModel=2 về mô hình cũ; mốc mở rộng tính năng xã hội |
| v0.3.9-patch | Bot whisper hỏi trước khi mời party, chênh cấp tối đa 10; archer mua tên; cubics cập nhật ngay; chuyển nghề trước class master | Sao lưu file tùy chỉnh |
| v0.4.1 (đã được chia sẻ) | Fighter biết đánh thường thay spam skill yếu; playstyles có fallback kiểm soát; support buddy chữa trị cả party, pet; giá bot store công bằng; lưu bot trước khi đóng DB; sửa Olympiad reward | Thay FakePlayers.ini và living-population module.ini; >1 bản sau thì dùng Full |
| v0.4.2 (đã được chia sẻ) | **500 bot mặc định**, shout từ sự kiện thật, bot nhớ 10 player, tiểu sử/câu thoại (~1700 mảnh), clan war/alliance/grudge, fixes .alt Summoner, Ollama model giữ đã nạp | Thay FakePlayers.ini/module.ini; Shout Living Bot thay town fakes; Trade Chat town fake tắt mặc định |
| **v0.4.3 hotfix** (chia sẻ 07/11) | Bot nhận lời Party **thực sự gửi invite/đến chỗ player**, slow Healer cast hoàn tất, quest class/pet không kẹt vô hạn, save độc lập khi `LivingPopulation.json` bị khóa, reset clan/castle sạch, invite đúng clan sub-unit | Thay `FakePlayers.ini` và living-population `module.ini`; **Town FakePlayerTradeChat mặc định bật lại**; `QuestStallMinutes` mặc định 180 phút |

Đọc [v0.4.5](RELEASE-LIVING-WORLDS-0.4.5.md) về Castle/Clan Hall/Olympiad/Manor và thay đổi cấu hình shop trước khi đọc [v0.4.4](RELEASE-LIVING-WORLDS-0.4.4.md) cho Seven Signs, Grand Boss mặc định bật, hiệu năng và QA.

Đọc riêng [hotfix v0.4.3](RELEASE-LIVING-WORLDS-0.4.3.md) để biết các từ khóa Party, `QuestStallMinutes`, quy trình reset Living Clan và cảnh báo backup.

| **v0.4.4** (chia sẻ 12/11) | Thêm ~50 grounds, sub-area, crowd learning, Seven Signs/Festival/Mammon, Strider/Wyvern/Baby Pets, private/manufacture stores, Party Matching hot bots, bot Grand Boss, chỉ gear hợp lệ, sửa memory/lag Cold Sim | **Thay Feature.ini và living-population module.ini**; BotGrandBosses=True mặc định, BotFestivalCountsForScore=False, TestGearAll off; [chi tiết](RELEASE-LIVING-WORLDS-0.4.4.md) |

| **v0.4.5** (chia sẻ 12/11) | Living Clans quản lý castle tax/manor/mercenaries, đấu giá/đánh Clan Hall sieges, Clan Skills/Academy, bot Noblesse thi Olympiad Cold/Hot, Hero; Dyes/Augment/Fishing, pathfinding và raid fixes | Patch thay **living-population module.ini, General.ini, Rates.ini**; **CustomBuyListLoad=False** mặc định; [chi tiết](RELEASE-LIVING-WORLDS-0.4.5.md) |

## Living Worlds v0.4.6 — 2026-11-14
[UPSTREAM] Thêm tối đa 4 đội phụ raid, đội hình quanh boss/né AOE, recipe chasers, restock và sửa rò bộ nhớ. [Nguồn](https://updates.l2livingworlds.space/releases.json).
[OBSERVED] Đã gộp thành bản Việt hóa 1.0.1 và kiểm tra khởi động; chưa đánh raid/soak nhiều giờ. [Chi tiết](RELEASE-SHARE-1.0.1.md). Không nhầm với Interlude+ v0.4.6 alpha.

## Living Worlds v0.4.7-patch — thông báo 2026-11-15

[UPSTREAM] Bot Party theo player vào Elven Ruins và trả phí Gatekeeper; sửa bot/quái đứng trên vùng đất/đáy biển phía trên Elven Fortress và Elven Ruins, tránh đuổi quái khác tầng. Bot newbie rời khu khi quái quá yếu: Human Talking Island & Dark Elf **lv9**, Orc & Dwarf **lv10**, Elf **lv11**; từ lv5 bãi đông chuyển sang fields. Party Living Bot giới hạn **3 (<20), 5 (20+), 7 (40+), 9 (52+)**; loại thành viên cấp cao nhất nếu nhóm quá đông. Patch thay **living-population module.ini, General.ini, Rates.ini, Feature.ini, test-gear module.ini**; nếu cũ hơn v0.4.4 dùng **Full**. Chưa có [OBSERVED] gameplay; [chi tiết](RELEASE-LIVING-WORLDS-0.4.7-PATCH.md).

## Living Worlds v0.4.9 — Combat & Rules (trích ngày 2026-11-17)

**[UPSTREAM v0.4.9 — người dùng xác nhận phiên bản]** Đã nhận hai mục “Combat and rules (closer to retail Interlude)” và “Base game fixes”: Magic Crit ×4/PvP, Interlude EXP/Party bonus/Raid drop rules, `DropGroupIndependentRolls` on, Clan C5, Seal of Strife chỉ trong Validation, 60+ Mobius fixes, hai công tắc mới `CraftingInCombat` và `DisableCp` (off). **Phiên bản được người dùng xác nhận là v0.4.9**, nhưng chưa kiểm tra gói patch/link release đầy đủ; **không khẳng định đã có trong bản Việt hóa 1.0.2**. [Tài liệu/QA](RELEASE-LIVING-WORLDS-0.4.9-COMBAT-RULES.md).

## Cụ thể đáng lưu ý

### Bot xã hội v0.4.2
- Shout về raid thành công, chuyển nghề, đạt cấp 52/61, tuyển clan/party; tối đa một event shout mỗi 30 giây **khi người thật online**; vẫn có câu thoại soạn sẵn nếu tắt AI Brain.
- Hỏi shout bằng mẫu tiếng Anh có thể nhận trả lời dựa trên trạng thái thật của clan, raid, siege, bãi săn, người ở Giran.
- Mỗi living bot nhớ tới **10 người chơi**: gặp, party, bị giết/tấn công/hồi sinh, whisper; quan hệ có thể trở nên thân thiện hoặc thù địch. Duel, Olympiad và PvP công bằng không bị tính thành thù.
- Clan có thể ghi thù, khai chiến, hòa giải, liên minh và tham gia siege; **chưa chứng minh toàn bộ các hành vi luôn diễn ra hiệu quả trong mọi save**.
- **FakePlayerTradeChat** cho town bots mặc định off **trong v0.4.2**, nhưng được **bật True mặc định trong v0.4.3**.

### Party v0.4.1
- Auto Attack 0.25–10 giây sau skill là **tùy chọn cho người chỉnh Playstyle**, chưa áp dụng vào playstyle mặc định.
- Support Buddy hồi máu, giải trạng thái, bơm MP, buff và hồi sinh pet/servitor tùy ưu tiên; không hồi máu trùng mục tiêu với healer.
- Cửa hàng mua bán recipe/material/blessed scroll giá cao hơn khoảng ×2.5; giá thu mua vũ khí/giáp theo khoảng 0.6–0.95 giá shop.
- Đóng server chờ living bot lưu xong rồi mới đóng database.

### Các bản chưa được xác nhận
- Không có đủ changelog cho **v0.3.4–v0.3.6, v0.4.0** trong tài liệu thu thập; không tự suy ra.
- **Interlude+ v0.4.6 alpha** là số phiên bản **module khác**, không đưa vào dòng phiên bản Living Worlds.

## Khi nhận changelog mới
1. Thêm đúng số phiên bản, ngày đăng và nguồn trong [SOURCES](SOURCES-AND-CONTRIBUTING.md).
2. Đánh dấu [UPSTREAM]; chỉ nâng thành [OBSERVED] khi có bước test + log.
3. Cập nhật [FEATURE-MAP](FEATURE-MAP.md), [QA](QA-AND-BACKLOG.md) và các cảnh báo file bị ghi đè.
4. Không suy luận version đang cài trên máy chỉ từ version được nhắc ở Discord.

### Triển khai Việt hóa 1.0.2 — 2026-11-15 [OBSERVED]
Đã tải/đối chiếu ZIP v0.4.7, dựng core Việt hóa, cài bản chính/demo và kiểm tra module/AI/installer. Gameplay dungeon vẫn NOT RUN. [Báo cáo](RELEASE-SHARE-1.0.2.md).
