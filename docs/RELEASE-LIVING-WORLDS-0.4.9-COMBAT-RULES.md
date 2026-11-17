# Living Worlds v0.4.9 — Combat & Rules sát retail Interlude và sửa lỗi nền game

> **[UPSTREAM · VERSION CONFIRMED BY USER: v0.4.9]** Người dùng xác nhận ngày **2026-11-17** rằng hai phần changelog **"Combat and rules (closer to retail Interlude)"** và **"Base game fixes"** thuộc **Living Worlds v0.4.9**. Hiện đây là **hai phần trích**, chưa có bản phát hành/ZIP/changelog đầy đủ được kiểm tra trong repo. **Không đồng nghĩa** đã tích hợp vào bản Việt hóa **1.0.2 / Living Worlds v0.4.7**, và **chưa có kiểm thử gameplay [OBSERVED]**.

## 1. Combat and rules — bám luật Interlude

| Mục | Changelog công bố | Ảnh hưởng cần kiểm tra |
|---|---|---|
| Magic crit | Magic Critical theo thang Interlude, **×4 sát thương, gồm cả PvP** | Test PvE/PvP với cùng mục tiêu, có/không Magic Crit; phân biệt tỷ lệ chí mạng và hệ số sát thương |
| EXP | Đường cong chênh cấp theo Interlude; **không phạt EXP khi quái cao cấp hơn người chơi**; dùng bảng thưởng Party retail | Test solo vs Party và level gap cao/thấp, không nhầm với tỉ lệ EXP máy chủ tự chỉnh |
| Raid Drop | Đường cong phạt theo chênh cấp **dốc hơn**, sát Interlude | Test Raid Boss theo các chênh cấp, tránh kết luận item rơi ít là bug khi đúng penalty |
| Drop groups | `DropGroupIndependentRolls` **on**: trong một nhóm drop, mỗi vật phẩm được roll riêng | Có thể làm phân phối loot khác cơ chế roll nhóm cũ; phải đo trên đủ số mẫu |
| Weapon damage | Fix lỗi random damage của vũ khí **bị roll hai lần** | Đối chiếu damage distribution trước/sau |
| Spell resist | Fix phép quái **bị kháng mà vẫn gây full damage** | Test các tình huống kháng phép đúng luật |
| Shield/polearm | Fix shield block và góc quét polearm ở đường ranh **0°/360°** | Test hướng đánh, mục tiêu tại ranh giới |
| Resurrection | Fix WIT bonus áp dụng cho Resurrect | Test nhiều WIT với Resurrect, tránh bịa tỉ lệ hồi EXP |
| Karma/death | Sửa edge cases của death penalty và Karma | Test chết do quái/PvP/PK, red name, revive |
| Clan C5 | Clan level cost/requirements, academy points và reputation per kill theo số liệu **C5** | Test tăng level clan, graduation, Reputation |
| Seal of Strife | Hiệu ứng chỉ có trong giai đoạn **Seal Validation** | Test thời điểm competition vs validation, restart qua mốc chu kỳ |

**Quan trọng:** "closer to retail" là mô tả của changelog, không chứng minh mọi quy tắc trong game đã hoàn toàn giống retail Interlude. `DropGroupIndependentRolls` được nói **on**, nhưng chưa rõ file chứa khóa khi chưa thấy cấu hình thực.

## 2. Base game fixes

- **Clan Hall:** làm lại auction, rent, functions; xử lý phiên đấu giá không kết thúc, rent ngừng sau restart, trừ tiền hai lần, lỗi listing và bid validation.
- **Castle Siege / Chamberlain / Olympiad / Festival:** sửa logic Siege, quản lý lâu đài, buff trước trận Olympiad, cleanup Olympiad Manager và lỗi Festival.
- **Hơn 60 lỗi từ L2J Mobius:** giao dịch, kho, crafting, câu cá buổi tối, enchant refresh, đối thoại đổi nghề Dark Elf, tutorial, spawn Wall of Argos, Primeval Isle, Field of Whispers và nhiều lỗi khác.
- **Player.ini:** thêm `CraftingInCombat` và `DisableCp`; **cả hai đều off theo mặc định**. Bật `DisableCp` là lựa chọn thay đổi gameplay rất lớn; không tự bật.

## 3. Rủi ro với bản Việt hóa và server hiện tại

**Mốc được ghi trong repo khi nhận đoạn trích:** bản chia sẻ đầy đủ **1.0** nền v0.4.5; **patch cộng dồn 1.0.2** đã tích hợp Living Worlds **v0.4.7** (xem [HANDOFF](../HANDOFF.md) và [báo cáo 1.0.2](RELEASE-SHARE-1.0.2.md)). **Không khẳng định những thay đổi mới đã có trong 1.0.2.**

1. **Trước khi nâng upstream:** đã biết nhãn phiên bản **v0.4.9**, nhưng cần đối chiếu **gói patch/Full**, các config bị ghi đè và source build; từ đoạn trích vẫn **chưa rõ**. Backup server, DB, Việt hóa và config; diff đúng gói tải trước khi nhập.
2. **Custom shop lists:** bản v0.4.5 trước có `CustomBuyListLoad=False` trong `General.ini`; khi cập nhật cần giữ giá trị tùy chỉnh nếu có, nhưng **không suy ra lần này sẽ ghi đè General.ini**.
3. **Tương thích Việt hóa:** đối thoại đổi nghề Dark Elf, tutorial, enchant, NPC Clan Hall/Chamberlain, Festival/Olympiad, thông báo drop/karma có khả năng thay đổi file/scripting. Kiểm tra dịch trước/sau; không được gộp đè client `.dat` cũ lên client khác.
4. **Công bằng x1:** Magic Crit ×4/PvP, đường cong EXP, Party bonus, Raid Drop và Clan C5 có thể làm **thay đổi cân bằng đáng kể** trong thế giới đã chơi lâu; nên kiểm thử trên staging.
5. **CP:** `DisableCp` off; nếu muốn PvP giống Interlude, **đừng bật tắt CP tùy tiện**.

## 4. Checklist kiểm thử đề xuất (NOT RUN)

### Chiến đấu / phần thưởng
- [ ] Magic Crit ×4 đúng trong PvE **và** PvP, không nhân đôi nhầm.
- [ ] EXP và SP solo chênh cấp dương/âm, quái cấp cao hơn không chịu penalty riêng; Party bonus đúng bảng.
- [ ] Raid boss drop theo chênh cấp, `DropGroupIndependentRolls` on và mỗi item được roll độc lập.
- [ ] Weapon random damage không roll hai lần; spell resist không full damage.
- [ ] Shield block và polearm arc hoạt động khi mục tiêu hai bên góc 0°/360°.
- [ ] Resurrect WIT, death penalty, karma/PK edge cases.
- [ ] Clan level cost/requirements/academy/reputation theo C5; Seal of Strife chỉ trong Validation.

### Base game / Việt hóa
- [ ] Clan Hall auction/rent/functions restart, không thu double, kiểm tra bid/listing.
- [ ] Castle Siege, Chamberlain, Olympiad pre-match buffs/cleanup, Festival.
- [ ] Trades/warehouses/craft/enchant refresh/night fishing; vùng spawn Wall of Argos, Primeval Isle, Field of Whispers.
- [ ] Dark Elf class-change dialog/tutorial đúng và **giữ tiếng Việt**; test NPC/Quest/UI.
- [ ] `Player.ini`: `CraftingInCombat=False`, `DisableCp=False` (theo mặc định bản mới); xác nhận tên khóa, giá trị thực trong gói patch.
- [ ] Xác minh đúng gói **Living Worlds v0.4.9**, config thay thế, checksum, backup và rollback trước cập nhật.
- [ ] **Không đánh dấu PASS** nếu chỉ có thông báo thay vì log gameplay.

## 5. Việc cần xác minh đầu tiên

- Phiên bản đã được người dùng xác nhận là **Living Worlds v0.4.9**. Cần xác nhận **ngày phát hành từ nguồn chính thức**, full changelog; **đây mới là 2 nhóm nội dung trích**, có thể còn phần thay đổi khác.
- URL release gốc/ZIP patch; file nào thực sự thay và khóa DropGroupIndependentRolls nằm trong config nào.
- Có cần cập nhật launcher, client hoặc module nào khác không.
- Bản Việt hóa 1.0.2 và server thực tế **chưa được kiểm chứng** có các sửa đổi này.

[Quy trình cập nhật](UPGRADE-RUNBOOK.md) · [QA](QA-AND-BACKLOG.md) · [Niên biểu](UPSTREAM-TIMELINE.md).
