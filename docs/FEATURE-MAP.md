# Bản đồ hệ thống Living Worlds

> Mục đích: tránh nhầm **AI chiến thuật server**, **AI hội thoại** và **NPC giả lập** khi lập kế hoạch phát triển.

## Các loại nhân vật

| Tên | Vai trò | Tình trạng theo changelog |
|---|---|---|
| **Living Population** | Cộng đồng bot tiến triển riêng: lên cấp, săn quái, đồ, tiền, quest, party, clan | Từ v0.2.x; mở rộng xã hội v0.3.8/v0.4.2; v0.4.3 sửa quest bị kẹt |
| **Town Fake Players** | Nhân vật tạo không khí thành phố, shop/trade | Có cấu hình FakePlayers.ini; v0.4.2 say/shout/trade tắt mặc định; **v0.4.3 trade bật True**, shout vẫn dành cho living bots |
| **Field Phantoms** | Bot săn ngoài đồng riêng; không đồng nghĩa Living Population | Có công tắc PhantomFieldHunters (mặc định off từ v0.2.6) |
| **Party Phantoms / Support Buddies** | Đồng đội đi cùng người chơi, heal/buff/combat | v0.4.1 nâng cấp chăm toàn bộ party/pet |
| **.alt / Account Companions** | Nhân vật phụ tài khoản được dùng như đồng đội (module) | Lỗi summoner được đề cập và sửa ở v0.4.2 |
| **Brain / Ollama** | Lớp tạo/hiểu hội thoại tùy chọn | Không phải hệ thống tạo chiến thuật săn quái |
| **Iron Bot** | Bot trợ giúp cộng đồng Discord | Không chạy gameplay; fingerprint theo phiên bản repo để tránh trả lời lỗi thời |

## Chu kỳ đời sống bot

- **Khởi đầu và lên cấp:** bot sinh theo đợt, chọn khu săn theo level, dùng teleport, học kỹ năng, thay gear, làm nhiệm vụ (kể cả class/side/pet/Saga), có kho riêng. **v0.4.3:** quest hunt bắt đầu gần quái nhiệm vụ; class/pet quest không tiến triển quá 3 giờ sẽ được giải quyết theo timer và nhận thưởng, side quest bị drop.
- **Kinh tế:** loot thật đi vào kho/chợ; bot mua bán thành phố, đặt gear từ Sateya, craft, trao đổi vật phẩm qua cửa hàng cá nhân; Giran là trung tâm hàng dư.
- **Đồng đội:** living bots tự party, chọn vai trò, raid boss; người chơi có thể tìm party trong Shout. Bot mời người chơi bằng whisper trước, chênh tối đa 10 level (v0.3.9). **v0.4.3:** khi bot nhận lời Party, nó thực sự gửi invite, đi bộ/teleport đến player (có trả tiền Gatekeeper), hoặc đứng đợi khi player hẹn tới; `/invite BotName` dùng từ xa. Lời hứa không có party thật sẽ bị chặn.
- **Clans:** bot thành lập/gia nhập/đóng góp, lên cấp clan, clan warehouse, raid, siege, academy/sub-unit; v0.4.2 nhớ quan hệ, chiến tranh, liên minh, thù hận.
- **Giao tiếp:** ~1700 câu thoại/mảnh hồ sơ được chuẩn bị sẵn giúp bot phát biểu ngay cả khi tắt Brain; trả lời thông tin dựa trên sự kiện thật. Các mẫu nhận diện tiếng Việt chưa được xác minh.
- **Offline/out of sight:** **Cold Sim 4.0** (v0.3.8) cho bot ngoài tầm nhìn chiến đấu với quái thật, loot thật, party có tank/heal/buff; thiết lập về mô hình cũ: `ColdModel = 2`.
- **Nghỉ ngơi:** v0.4.1 có bot thoát ra trong giờ nghỉ (mặc định khoảng một nửa số bot đang nghỉ), số còn lại đi dạo.

## Điều kiện cấu hình có thể gây hiểu nhầm

| Thiết lập/tính năng | Ý nghĩa |
|---|---|
| `PopulationSize` / Number of bots | Số bot trong quần thể; v0.4.2 công bố mặc định 500, trước đó 100 |
| `All bots live` | Bot hoạt động ở thế giới theo cơ chế nặng tài nguyên; **không mặc định bật** |
| `PhantomFieldHunters` | Phantom săn ngoài đồng; khác Living Population; mặc định off từ v0.2.6 |
| `FakePlayerTownFollowsLivingPopulation` | Town fake bot cấp/grade tương ứng thế giới đang phát triển |
| `FakePlayerTradeChat` | Town Fake Bot Trade Chat: **off ở v0.4.2 → True mặc định từ v0.4.3**; Shout Living Bot vẫn giữ riêng |
| `QuestStallMinutes` | v0.4.3: mặc định **180 phút** không tiến triển sau khi săn; class/pet quest hoàn thành theo timer và có thưởng, side quest bỏ |
| `PhantomSkillFallbackMode = HYBRID` | Về hành vi fallback skill cũ của phantom (v0.4.1) |
| `DowntimeLogoutPercent` | Tỷ lệ bot nghỉ thì log off |
| `ColdModel = 2` | Trở về mô hình mô phỏng cũ thay vì Cold Sim 4.0 |

**Lưu ý:** Reset Living Population **v0.4.3** đồng thời giải tán toàn bộ Living Clan, giải phóng castle và xóa clan records; bot nằm trong clan player rời đi trước. **Không thực hiện trên thế giới chính mà chưa backup.**

Tên cài đặt phụ thuộc phiên bản. Không đưa cấu hình cũ nguyên xi vào server mới mà chưa kiểm tra mặc định mới.

## QA quan trọng ở v0.4.3
- Healer cast chậm (WIT thấp, slow/no gear) giờ được phép niệm hết `Heal`/`Greater Heal`/`Greater Group Heal`, cả .alt và recruited bots.
- Save town stock/market/clan/siege/raid không bị bỏ qua chỉ vì `LivingPopulation.json` đang mở/khóa.
- Bot được mời vào đúng Royal Guard, Order of Knights hoặc Academy thay vì ép vào main clan.

## Các giả thuyết cần kiểm chứng trên một world mới

- [UNKNOWN] Có thể bắt đầu **toàn bộ** living bots tại level 1 cùng nhân vật mới hay không?
- [UNKNOWN] Có tùy chọn giới hạn tốc độ lên cấp/sức mạnh của bots so với player không?
- [UNKNOWN] Tần suất clan war/alliances thực tế ở population 100, 200, 500 là bao nhiêu?
- [UNKNOWN] Các câu hỏi Shout tiếng Việt có được hệ thống mẫu định tuyến đúng hay không?
- [UNKNOWN] CPU/RAM và lưu DB khi 500 bot + Cold Sim 4.0 + AI Brain + nhiều client LAN?
- [UNKNOWN] Kết quả thực tế khi dùng client Việt hóa/Fermata/Interlude+ với hệ thống guild/chat/quest mới?

## Hướng phát triển đề xuất [PLAN]
Thay vì can thiệp thẳng vào AI upstream:
1. Làm bộ **world telemetry** nhỏ: snapshot số bot theo band cấp, số clan, số party, raid/siege/war và giờ online.
2. Tạo bộ **benchmark có kịch bản** chạy với các cấu hình 100/200/500 bots và Ollama on/off.
3. Tạo **smoke tests** cho Việt hóa, quest, shop, clan, party, Summoner, client 737/746/Fermata.
4. Chỉ xem xét module đồng bộ tiến trình nếu log cho thấy bot vượt người chơi quá nhanh và upstream chưa có cấu hình tương ứng.
