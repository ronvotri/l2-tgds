# Living Worlds v0.4.7-patch — Elven Ruins, Elven Fortress và bãi tân thủ

> **[UPSTREAM]** Tóm tắt thông báo hotfix **“Living Worlds v0.4.7-patch: Elven Ruins, Elven Fortress and newbie grounds”** người dùng gửi ngày **2026-11-15**. Đây là **changelog hotfix**, không phải bằng chứng bản cài của người dùng đã lên v0.4.7. Chưa có bài thử gameplay [OBSERVED] trên gói Việt hóa.

## 1. Lợi ích chính

- **Đồng đội trong Elven Ruins:** Living Bot đã gia nhập Party **theo người chơi vào Elven Ruins**, có **trả phí Gatekeeper**, xuất hiện ở **cổng vào phía trong**, không chỉ đứng bên ngoài.
- **Đúng tầng dungeon:** bot không còn săn trên **mặt đất phía trên Elven Fortress** hoặc **đáy biển phía trên Elven Ruins**. Nếu tới sai vị trí, được đưa trở lại bãi săn **bên trong dungeon**.
- **Quái trong dungeon:** monster từ hai dungeon không còn xuất hiện sai ở bề mặt phía trên; monster lọt ra ngoài sẽ trở về điểm spawn.
- **Tránh chase quái khác tầng:** bot không đuổi mục tiêu ở tầng cao hơn hoặc thấp hơn.

## 2. Khu khởi đầu: bot trưởng thành thì rời bãi

| Chủng tộc/khu | Bot rời khu newbie khi tới level |
|---|---:|
| Human ở Talking Island | 9 |
| Dark Elf | 9 |
| Orc | 10 |
| Dwarf | 10 |
| Elf | 11 |

- Khi quái tân thủ **quá yếu** so với bot, bot rời khu vực theo ngưỡng kể trên.
- Ở **level 5 trở lên**, khi khu newbie quá đông, bot sẽ tìm bãi đồng xung quanh thay vì **tất cả cùng cố tìm Party**.
- Chưa có thông báo sửa cơ chế **bot bắt đầu đồng loạt từ level 1** hay **khóa tiến trình bot theo cấp của người chơi**; vẫn là **[UNKNOWN]**.

## 3. Party tăng quy mô theo cấp

| Cấp độ Party/bot | Số thành viên tối đa |
|---|---:|
| Dưới 20 | **3** |
| Từ 20 đến 39 | **5** |
| Từ 40 đến 51 | **7** |
| Từ 52 trở lên | **9** |

- Khi nhóm **đông quá giới hạn cấp**, các thành viên cấp cao nhất sẽ **rời nhóm trước**.
- Changelog mô tả **Living Bot Parties**, không đủ căn cứ suy ra mọi Party do người chơi lập đều chịu giới hạn mới. Cần test chi tiết nếu Party hỗn hợp người chơi + bot.

### Ảnh hưởng tới mục tiêu chơi lại từ cấp 1
Kỳ vọng thấy các Party tân thủ quy mô vừa phải, bot phân bổ ra bãi săn hợp cấp nhanh hơn, và bạn có thể cùng bot vào Elven Ruins. Cần tự kiểm chứng spawn, quest và Party trong gói Việt hóa.

## 4. Cảnh báo cập nhật

Theo tác giả:
- Nếu bản **đang cài cũ hơn v0.4.4**, cần dùng **Full download**, không dùng riêng patch này.
- **Patch v0.4.7 ghi đè 5 file:**
  1. `living-population/module.ini`
  2. `General.ini`
  3. `Rates.ini`
  4. `Feature.ini`
  5. `test-gear/module.ini`
- Ghi lại mọi tùy chỉnh và merge từng key với defaults mới. Không chép đè toàn bộ file config cũ lên mới, cũng không để updater upstream vô tình thay file Việt hóa.
- **Không có yêu cầu reset database hoặc xóa nhân vật trong thông báo.** Không tự reset world để áp dụng sửa dungeon.
- Đường dẫn thật của `module.ini` và `test-gear/module.ini` phải xác nhận từ cây thư mục server đang chạy.

### Rất quan trọng với gói chia sẻ Việt hóa 1.0 / 1.0.1
Tại checkpoint repo ngày **2026-11-14**:
- **1.0** là gói đầy đủ cố định dựa trên Living Worlds **v0.4.5**.
- **1.0.1** là patch **cộng dồn** được kiểm thử cài/rollback ở mức smoke, nâng nền lên **Living Worlds v0.4.6**.
- **Chưa có bản phát hành Việt hóa v0.4.7 được xác minh từ changelog này.**
- Khi chuẩn bị patch Việt hóa kế tiếp, không phát riêng `v0.4.7-patch` upstream cho người dùng của gói chia sẻ. Theo [SHARE-RELEASES](SHARE-RELEASES.md), hãy tạo **patch cộng dồn từ nền 1.0**, chứa cả cập nhật sau 1.0, dịch/changes, migration config, backup, kiểm thử trên 1.0 sạch và 1.0.1 rồi mới công bố.
- Không nhầm **Living Worlds v0.4.7-patch** với **Interlude+ v0.4.6 alpha**.

## 5. Kiểm thử staging

### P0 — Không mất dữ liệu
- [ ] Kiểm kê gói upstream Full/Patch theo build chính xác; so sánh SHA/list file với baseline v0.4.6 nếu chuẩn bị cập nhật thật.
- [ ] Backup server/DB/client/bản dịch và 5 file config; merge cấu hình, giữ các thiết lập EXP/drop/custom buylists.
- [ ] Kiểm tra `CustomBuyListLoad` vẫn giữ như bản đang dùng và `BotGrandBosses` vẫn theo lựa chọn chủ server; tránh vô tình reset giá trị khi module.ini/General.ini bị đè.
- [ ] Test cài patch cộng dồn từ **gói full 1.0 sạch** và **1.0.1**, cài lặp/rollback, không tải lại 13GB hoặc bắt cài lần lượt (theo quy ước dự án).
- [ ] Test tiếng Việt, Party/NPC Gatekeeper/Quest, save/inventory/pet/clan và restart.

### P1 — Dungeon & newbie
- [ ] Người chơi lập Party với Living Bot tại vùng khác rồi vào **Elven Ruins**: bot theo đến **entrance bên trong**, Gatekeeper thu đúng phí; không ở trên đáy biển.
- [ ] Bot tự săn **Elven Fortress** nằm bên trong dungeon, không chạy trên mặt đất phía trên.
- [ ] Quái bị lệch lên trên hai dungeon phải trở về spawn; bot sai vị trí được sửa.
- [ ] Bot không chase/target quái ở tầng trên hoặc dưới; kiểm tra khi nhiều tầng gần nhau.
- [ ] Thử Human Talking Island và Dark Elf level 8→9, Orc/Dwarf 9→10, Elf 10→11: bot đổi hunting ground khi quái quá yếu.
- [ ] Ở level 5–11 khi khu newbie đông: bot rời ra field, không spam Party tìm nhóm.
- [ ] Thử Party cấp dưới 20 tối đa 3, 20+ tối đa 5, 40+ tối đa 7, 52+ tối đa 9; nhóm quá đông phải tách thành viên **level cao nhất trước**.
- [ ] Test Party **bot-only** và **có người chơi**, đánh dấu nếu logic giới hạn khác nhau; theo dõi XP/loot và hoạt động trong dungeon.
- [ ] Soak 1–3h để kiểm tra bot không kẹt lại ở trên/bên ngoài dungeon sau teleport hay reload.

## 6. Nguồn và tình trạng
Nguồn đầu vào: toàn văn thông báo hotfix do người dùng cung cấp ngày **2026-11-15**. Không có bản binary/patch đính kèm trong lượt trao đổi này; không ghi [OBSERVED] gameplay. Đọc thêm [timeline](UPSTREAM-TIMELINE.md), [kế hoạch World mới](FRESH-WORLD-PLAN.md), [upgrade runbook](UPGRADE-RUNBOOK.md), [QA](QA-AND-BACKLOG.md) và [quy ước phát hành patch Việt hóa](SHARE-RELEASES.md).
