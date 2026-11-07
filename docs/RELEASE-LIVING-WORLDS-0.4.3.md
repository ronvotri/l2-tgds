# Living Worlds hotfix v0.4.3 — bot tìm đến bạn, healer hoàn tất phép, reset clan an toàn hơn

> **[UPSTREAM]** Tóm tắt thông báo Teras được chia sẻ ngày **2026-11-07**, tiêu đề gốc: “Living Worlds hotfix v0.4.3: Living bots come to you, healers finish their heals”. Cảm ơn AgetianMaywind về các báo cáo lỗi. **Chưa có [OBSERVED] kiểm thử trên server/Việt hóa thật.**

## Tóm tắt nhanh
Đây là hotfix nối tiếp **Living Worlds v0.4.2**: sửa tình trạng bot nhận lời party nhưng không đến, healer bị hủy heal khi cast chậm, bot kẹt quest chuyển nghề, save bị chặn dây chuyền, reset population để sót clan/castle, và mời bot vào sai đơn vị clan.

## Chi tiết công bố

### 1. Living bots thực sự tìm đến người chơi
- Khi bot đề nghị nhóm qua **Shout hoặc Whisper**, người chơi chỉ cần trả lời `yes`, `inv me`, `im at Gludio gk`, hoặc `ill come to u`; bot thực sự **gửi party invite**, dù đang ở khu vực khác trong thế giới.
- Khi nhận lời mời, bot whisper **ước lượng thời gian đến**, rồi đi bộ hoặc teleport tới người chơi; teleport có **trả tiền Gatekeeper như người thật**.
- Nếu người chơi nói **sẽ đến chỗ bot** (`ill come to u`), bot gia nhập party, **đợi nguyên tại vị trí**, cho biết bot đang ở đâu và chỉ chống trả khi bị tấn công trong lúc chờ.
- Bot shout tìm party thì người chơi có thể trả lời `me` hoặc `inv me` trên Shout để được mời.
- Nếu **người chơi đang là party leader**, bot có thể vào thẳng party sẵn có.
- `/invite BotName` vẫn dùng được **bất kể khoảng cách trên bản đồ**.

### 2. Không còn nhận lời suông
- Không tự nói “on my way” hay “meet you at the wh” khi **chưa thực sự hình thành party**.
- Nếu không thể gia nhập, báo đúng lý do, chẳng hạn `ask ur leader to inv me` hoặc `lvl gap too big`.
- Một câu `yes` từ bot qua whisper giờ được gắn với hành động gia nhập thực sự, thay vì chỉ trả lời hội thoại.

### 3. Healer niệm phép chậm không còn bị cắt ngang
- Sửa `Heal`, `Greater Heal`, `Greater Group Heal` bị cancel ngay trước khi hiệu lực khi **casting speed thấp** (thiếu trang bị casting speed, WIT âm từ dyes, hoặc bị slow).
- Áp dụng cho **.alt companions** và thành viên được tuyển vào Party.
- Thời gian cast được dành đúng theo casting speed thực của healer.

### 4. Town bot Trade Chat bật lại mặc định
- **`FakePlayerTradeChat = True`** trong `FakePlayers.ini` ở v0.4.3.
- **Khác v0.4.2:** phiên bản trước mặc định Trade Chat town bots **off**. **Shout vẫn thuộc Living Bots**.
- Có thể tắt Trade Chat của town bots thủ công nếu người chơi muốn yên tĩnh.

### 5. Bot không kẹt quest chuyển nghề cả ngày
- Bot bị kẹt bước quest, ví dụ bot level 40 cứ săn quái khu level 20, không nhận EXP.
- Bước hunt của quest nay bắt đầu **gần monster thuộc quest**.
- Nếu đi săn mà quest **không tiến triển trong 3 giờ** (`QuestStallMinutes` trong living-population `module.ini`):
  - **Class quest hoặc pet quest:** hoàn thành theo timer bình thường và **có thưởng**, để bot quay về săn khu phù hợp.
  - **Side quest:** **hủy/bỏ**, không gán thưởng hoàn thành tự động.
- Log của Fuers giúp xác định bug; giá trị 3 giờ là mặc định được thông báo, có thể cấu hình.

### 6. Save không bị lỗi lan truyền do một file status
- Trước đây khi tiến trình khác đang mở/khóa `LivingPopulation.json`, các bản lưu **town stock, market, clan, siege, raid** có thể bị **bỏ qua** ngay cả lúc shutdown.
- Nay những dữ liệu này vẫn được lưu **độc lập với tình trạng file status**.
- **Cần test Stop/Restart và khả năng khôi phục**, không tự suy ra đã hết mọi rủi ro database.

### 7. Reset Living Population cũng dọn clan/castle
- Trước hotfix, **Reset Population trong l2admin** xóa bot nhưng để lại **Living Clan, castle đang chiếm, clan records**, làm bot mới không lập hoặc gia nhập clan.
- Sau hotfix, reset sẽ **giải tán toàn bộ Living Clans, giải phóng những castle mà chúng sở hữu và xóa clan records** để population mới tạo clan mới.
- Bot nào đang ở **clan do người chơi sở hữu** sẽ rời clan đó trước.
- **CẢNH BÁO:** Đây là thay đổi có tác động lớn đến **trạng thái clan/castle**, không dùng nút Reset Living Population trên world chính chỉ để thử tính năng! Backup database + records clan/castle trước.

### 8. Mời bot vào đúng clan unit
- Khi người chơi mời một Living Bot vào **Royal Guard / Order of Knights / Academy**, bot nay vào **đúng sub-unit**.
- Trước đây bot bị đưa vào main clan, có thể làm lời mời thất bại khi main clan đã đầy.

## Thông tin cập nhật
- Nếu bản cài cách **hơn một update** thì dùng **full download** thay patch.
- **Patch ghi đè `FakePlayers.ini` và `living-population/module.ini`**; ghi lại các giá trị tùy chỉnh trước khi patch, sau đó **merge theo cấu hình mới**, không chép ngược toàn file.
- `FakePlayerTradeChat` đổi default **off → on**. Thêm/đọc `QuestStallMinutes` theo nội dung server mới.
- Chưa có bằng chứng hotfix này yêu cầu sửa file client hay source Việt hóa, **nhưng patch/Full vẫn cần diff file trước khi áp dụng**.

## Checklist kiểm thử đề xuất [PLAN]

- [ ] Shout: bot đề nghị party; nói `me` / `inv me`; xác nhận bot **gửi invite**.
- [ ] Whisper: `yes`, `im at Gludio gk`; nhận lời, bot whisper ETA, đi/teleport và **đến vị trí thật**, có tốn tiền GK.
- [ ] `ill come to u`: bot đứng đợi, không tự đi khi chưa tới; vẫn chống trả nếu bị đánh.
- [ ] Player đang là leader party: bot vào party sẵn có; thử `/invite BotName` từ xa.
- [ ] Lời mời không đủ điều kiện: bot phản hồi lý do thật, không nói "on my way" sai.
- [ ] Healer casting thấp (WIT dyes/slow/no casting gear): `Heal`, `Greater Heal`, `Greater Group Heal` không bị cắt trước khi landed (cả .alt và recruited).
- [ ] Trade Chat của Town Fake Bot **bật mặc định**, Shout Living Bot không bị lấn; thử tắt bằng config.
- [ ] Class/pet quest đang kẹt: trong 3h hunt không tiến triển, kết thúc theo timer + thưởng; **side quest bị drop**, không nhận thưởng; không kiểm thử bằng cách đẩy nhanh production.
- [ ] Khóa/mở file `LivingPopulation.json` trong **staging** để kiểm tra town stock/market/clan/siege/raid vẫn lưu; test shutdown/restart sau đó.
- [ ] Reset population **chỉ trên staging**: Living Clans/castles/records được dọn đúng; bot ở player clan rời trước; population mới lập clan.
- [ ] Invite bot vào Royal Guard/Order of Knights/Academy đúng unit, kể cả khi main clan đã đầy.
- [ ] Kiểm tra chữ Việt trong NPC/Quest/Shout/Whisper/Clan và file config không bị revert.

## Liên quan mục tiêu chơi lại từ level 1
- **Cải thiện thiết thực:** có thể tuyển bot ở xa qua chat và bot thực sự tới party; bot không bị kẹt ở quest chuyển nghề quá lâu.
- **Chưa được giải quyết/xác nhận:** toàn bộ bot bắt đầu từ level 1 cùng người chơi, hay có giới hạn tốc độ phát triển bằng người chơi.

Đọc thêm: [niên biểu](UPSTREAM-TIMELINE.md), [kế hoạch world mới](FRESH-WORLD-PLAN.md), [runbook cập nhật an toàn](UPGRADE-RUNBOOK.md), [QA](QA-AND-BACKLOG.md).
