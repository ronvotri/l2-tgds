# Mục tiêu: chơi lại từ đầu và cùng farm với bot

## Kết luận hiện tại

**Có thể trải nghiệm**: nhân vật mới cấp 1, cùng bot đi săn, làm quest, lên cấp, mua đồ, party, phát triển clan. **Không được xác nhận**: toàn bộ bot luôn bắt đầu ở level 1 cùng lúc, hay bot bị giới hạn tốc độ phát triển bằng người chơi.

Từ v0.3.0 có nút **Wipe Server** (reset database, giữ cấu hình; tạo backup trong Documents theo changelog); v0.3.1 có nút **Reset Living Population** trong Config Editor. Đây là **hai thao tác khác nhau**. **Ở v0.4.3, Reset Living Population còn giải tán Living Clan, giải phóng castle và xóa clan records; bot thuộc player clan cũng rời đi trước khi reset.**

## Kế hoạch an toàn

### A. Chuẩn bị
1. Ghi chính xác phiên bản server hiện chạy, client đang dùng, module và config đã thay đổi; không suy ra chỉ từ changelog đã đọc.
2. Nhấn **Stop**, đợi save hoàn tất; backup thủ công **toàn bộ server + database + client Việt hóa + file mod**. Đảm bảo có thể khôi phục offline.
3. Dùng **bản sao tách biệt** để thử thế giới mới; không wipe bản chơi chính.
4. Thử với **Interlude gốc được xác nhận hoạt động** trước; Fermata và Interlude+ kiểm tra độc lập.

### B. Khởi tạo thế giới
1. Dùng **Wipe Server** chỉ trên bản sao test nếu muốn reset cả thế giới/nhân vật; hoặc **Reset Living Population** nếu chỉ muốn tạo lại cộng đồng bot. **Đừng hiểu reset population là chỉ xóa nhân vật bot: v0.4.3 reset Living Clan/castle/records và khiến bot trong player clan rời đi; kiểm tra ảnh hưởng trước khi áp dụng.**
2. Cài rates mong muốn, ví dụ **EXP x1** để giữ tiến trình truyền thống (đây là lựa chọn, không phải bắt buộc).
3. Bắt đầu từ **100–200 living bots** nếu ưu tiên ổn định, rồi tăng dần tới 500 khi có log chứng minh hiệu năng.
4. Giữ **All bots live** off lúc đầu; kiểm tra Cold Sim ở mặc định mới; bật chức năng Town Fakes theo Living Population để tránh server cấp thấp mà phố toàn gear S.
5. Tạo người chơi mới level 1 và quan sát các bot ở khu tân thủ có thực sự xuất hiện/chơi/quest hay không.

### C. Bài test tiến trình
Ghi snapshot ở 0h, 1h, 3h, 5h, 10h, 24h chơi:
- Số bot tổng, online, đang nghỉ, level 1–20 / 21–40 / 41–60 / 61+.
- Sau v0.4.3: số bot kẹt quest class/pet lâu quá `QuestStallMinutes` (mặc định 180 phút); theo dõi class quests tự hoàn thành theo timer, side quests bị drop.
- Level của người chơi, số party bot có thể gia nhập gần cấp.
- Clan bot mới, level clan, số raid/war/siege thực tế.
- Độ trễ teleport; spikes CPU/RAM, tốc độ lưu khi tắt server, kích thước DB/log.
- Mức độ dịch tiếng Việt: quest/NPC/shout/skill/đồ ở từng client.

### C1. Kiểm thử Party v0.4.3 dành cho mục tiêu solo + bot
- Khi bot rao tuyển Party trên Shout, trả lời `me` hoặc `inv me`; bot phải gửi lời mời thật.
- Whisper `yes`, `im at Gludio gk` sau lời đề nghị từ bot: chấp nhận, đọc ETA và quan sát bot dùng Gatekeeper/đi bộ đến tận nơi.
- Whisper `ill come to u`: bot phải đứng chờ và nói vị trí; trong lúc chờ chỉ chống trả quái tấn công.
- Thử `/invite BotName` từ xa, Party đã có leader, và trường hợp vượt khoảng cấp; không chấp nhận phản hồi “on my way” giả.
- Test Healer casting chậm hoàn tất phép trị thương; quest class/pet không kẹt một ngày.

### D. Kiểm thử nhiều máy
1. Máy chủ chạy login/game server; máy khách dùng IP LAN của máy chủ (không dùng `127.0.0.1` từ máy khác).
2. Xác minh server quảng bá IP/port đúng và firewall chỉ mở trong LAN theo nhu cầu.
3. Test đồng thời nhiều nhân vật, party, lời mời bot, teleport, logout/reconnect; so hiệu năng khi Ollama bật/tắt.
4. Không mở dịch vụ Ollama ra Internet công khai mà chưa có xác thực và bảo vệ.

## Tiêu chí chấp nhận
- Server chạy nhiều giờ ổn định, save sau Stop và restore thử hoạt động.
- Bot cấp thấp thực sự xuất hiện và có thể ghép Party theo chênh lệch 10 cấp; v0.4.3 cần kiểm tra cả **Party được hình thành thật** và bot **đi/teleport tới người chơi hoặc chờ người chơi đến**.
- Bot/Clan phát triển theo thời gian, không phá cân bằng đến mức người mới không thể chơi.
- Nội dung Việt hóa cũ không bị mất; mọi phần tiếng Anh mới có danh sách cần dịch.
- Không có thông tin nhạy cảm xuất hiện trong log/commit.

## Nếu bot mạnh quá nhanh
Trước tiên đo và xác minh setting từ bản đang dùng, rồi mới đề xuất module giới hạn tiến trình. **Không khẳng định có sẵn chức năng đồng bộ level vì changelog chưa nói tới.**
