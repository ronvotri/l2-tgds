# Living Worlds v0.4.10 — Community Board, Clans, Launcher và bảo toàn cấu hình

> **[UPSTREAM · PARTIAL CHANGELOG]** Người dùng cung cấp ngày **2026-11-18** trích đoạn bốn mục **Community Board**, **Clans**, **Launcher**, **Updating**. Đây **chưa phải full changelog**; các nội dung khác của v0.4.10, file ZIP, checksum, build và ngày phát hành chính thức chưa được kiểm tra trong repo. **[OBSERVED]: NOT RUN.**

## Tóm tắt nhanh

| Hạng mục | Nhà phát triển công bố | Ý nghĩa |
|---|---|---|
| Community Board | Tự ẩn nút của các dịch vụ đã tắt: **Buffer, Merchant, Gatekeeper, Delevel, Premium** | Không hiện lựa chọn không sử dụng |
| Community Board | Có thêm **danh sách tự đặt tên các nút cần ẩn**, bao gồm **module tabs**; cả hai cài đặt trong Control Panel → Community Board | Chủ server có thể dọn UI, ẩn tab không cần thiết |
| Clans | `DaysBeforeAcceptNewMemberWhenDismissed` trong `Player.ini`: số **ngày Clan cần chờ trước khi nhận thành viên mới sau khi dismiss một người** | `0` tắt thời gian chờ; default **giữ luật cũ**, **chưa nêu con số cụ thể** |
| Launcher | Modules panel chia **module tự thêm** và **core module đi kèm server** thành hai nhóm | Dễ nhận diện module riêng và module nền |
| Updater | Patch sao lưu từng file cấu hình đã chỉnh vào `config-backup\before-v0.4.10\` và **giữ các setting người dùng đã sửa** | Không phải ghi lại thủ công từng giá trị như các bản trước, nhưng vẫn cần test migration |
| Defaults | Nếu chưa sửa `FakePlayerBaseNpcId` hoặc **Olympiad roster size**, nhận **default mới** | Không được tự suy đoán giá trị default mới |
| Launcher executable | **Phải lấy `LivingWorld.exe` mới từ release page** để thấy Modules panel chia nhóm | Chỉ patch server không bảo đảm giao diện launcher mới |
| Very old installs | Cần dùng **full download** | Chưa xác định chính xác mốc phiên bản trong đoạn này |

## 1. Community Board — ảnh hưởng UI và Việt hóa

- Khi Buffer, Merchant, Gatekeeper, Delevel hoặc Premium **được tắt trong cấu hình**, nút dịch vụ tương ứng **tự bị ẩn**, không để người chơi bấm vào mục không hoạt động.
- Có một **hide list tùy chọn** cho **tên nút** chủ server không muốn hiển thị; **bao gồm cả tab do module thêm**.
- Cả hai phần tùy chỉnh đều nằm ở **Control Panel → Community Board**.
- **[UNKNOWN]** Đoạn changelog **không nêu tên khóa .ini chính xác, cú pháp danh sách, cách matching tên nút, điều kiện reload hoặc ảnh hưởng tới từng client**. Phải xem file/Control Panel thực tế trước khi hướng dẫn nhập.
- [PLAN] Kiểm tra những nút/tab ẩn có liên quan bản Việt hóa, dấu tiếng Việt, Work Orders/Interlude+ module hoặc lựa chọn bắt buộc hay không; không nên ẩn module thực mà người chơi còn cần.

## 2. Clans — cooldown nhận thành viên mới

- `Player.ini`: `DaysBeforeAcceptNewMemberWhenDismissed` xác định số **ngày phải chờ sau khi một thành viên Clan bị dismiss** rồi mới được nhận người mới.
- **`0`**: tắt quy tắc chờ.
- **Default**: giữ **luật cũ của server**, nhưng **không công bố số ngày** trong đoạn trích; không gán bừa.
- [PLAN] Kiểm tra trường hợp leave tự nguyện, kick/dismiss, Academy, main Clan, Royal Guard, Order of Knights, có/không slot, thời gian lưu qua restart. Không suy ra tùy chọn này đồng nghĩa reset toàn bộ Clan.

## 3. Launcher — Modules panel phân hai nhóm

- Modules panel mới chia **custom modules** (do chủ server thêm) và **core modules** (bản server đi kèm) thành **hai phần**.
- Muốn thấy UI mới phải tải **`LivingWorld.exe` launcher mới từ release page**.
- [UNKNOWN] Chưa có URL release/trình cài/cách build mới, vì vậy **không đưa file exe lạ vào bản chia sẻ** nếu chưa đối chiếu nguồn và hash. Phần này khác với việc update `game/` và module .ini thông thường.
- [PLAN] Test launcher hiển thị đủ modules, tab tiếng Việt và trạng thái Enabled/Disabled; giữ chức năng một-click của gói chia sẻ.

## 4. Updater — giữ setting tùy chỉnh: điểm mới quan trọng

**Changelog ghi:** “Your own changes to config files are kept.” Patch tự:
1. Sao lưu từng **file config đã chỉnh** vào `config-backup\before-v0.4.10\`.
2. **Giữ giá trị người dùng đã thay đổi**, còn cấu hình chưa chỉnh nhận default phiên bản mới.
3. Có thể nhận **default mới** cho `FakePlayerBaseNpcId` và cỡ danh sách Olympiad **nếu chưa từng đổi hai setting này**.

**Danh sách tệp được thông báo là có thay đổi trong update này:**

| File | Rủi ro/kiểm tra |
|---|---|
| `FakePlayers.ini` | Giữ các setting town fakes và `FakePlayerBaseNpcId`; kiểm tra default mới nếu chưa từng đổi |
| `PhantomOlympiad.ini` | Kiểm tra Olympiad roster size, tránh mất cấu hình custom |
| `CommunityBoard.ini` | Kiểm tra ẩn nút dịch vụ bị tắt và hide list |
| `Player.ini` | Kiểm tra `DaysBeforeAcceptNewMemberWhenDismissed` và các thiết lập trước đó |
| `living-population/clans.ini` | Giữ thiết lập Clan của bot và các default mới; xác minh **đường dẫn thật** của file trong gói mới |

### Những điều KHÔNG nên suy ra
- **Backup file config không phải backup database, saves, nhân vật, AI memory, client hay bộ game Việt hóa.**
- Mô tả của updater **không chứng minh** mọi kiểu config custom/việt hóa đều được merge chính xác. Phải diff và test cả **đã chỉnh** / **chưa chỉnh**.
- `config-backup\before-v0.4.10\` là đường dẫn **tương đối được tác giả công bố**; xác minh nó nằm ở folder nào trong bản launcher/patch thực tế.
- Bản Việt hóa hiện đã có báo cáo tích hợp trong repo vẫn là **full 1.0 + patch cộng dồn 1.0.2 (Living Worlds v0.4.7)**. Đây là **changelog upstream mới**, **chưa có chứng cứ đã đóng gói v0.4.10**.
- Chỉ có **trích đoạn 4 mục**: không được coi đó là toàn bộ nội dung update.

## 5. Checklist QA [PLAN / NOT RUN]

### P0 — Giữ dữ liệu và bản Việt hóa
- [ ] Tải đúng release v0.4.10, xem Full/Patch/Launcher và checksum; giữ mốc full 1.0 và các patch Việt hóa cũ **bất biến**.
- [ ] Backup **server + DB + game data + save + AI model/config + client Việt hóa** vào nơi độc lập trước khi cập nhật, không dựa riêng `config-backup`.
- [ ] Lấy snapshot 5 config nêu trên; ghi hai trường hợp: setting đã sửa và chưa sửa.
- [ ] Test updater tạo `config-backup\before-v0.4.10\` và **không ghi đè giá trị riêng**; thiết lập chưa sửa nhận default mới.
- [ ] Test `FakePlayerBaseNpcId` và cỡ Olympiad roster size với giá trị tùy biến và mặc định.
- [ ] Test từ **full 1.0 sạch**, từ **1.0.2** và chạy lại patch nếu cần để bảo đảm update cộng dồn, không reset save/loss dịch.
- [ ] Kiểm tra rollback, restore database/config, không mất custom NPC/item/skill/HTML/chữ Việt.

### P1 — Chức năng
- [ ] Control Panel → Community Board: tắt Buffer/Merchant/Gatekeeper/Delevel/Premium thì từng nút ẩn; bật lại thì xuất hiện theo mong đợi.
- [ ] Hide list: ẩn từng tên nút và module tabs, không ẩn nhầm lựa chọn khác, client Việt hóa có dấu vẫn đọc được.
- [ ] `DaysBeforeAcceptNewMemberWhenDismissed`: thử `0` và default cũ, sau dismiss/có slot, reboot server và trường hợp Academy/Clan Unit.
- [ ] Dùng `LivingWorld.exe` mới: Modules panel chia đúng **own modules/core modules**, không thay đổi trạng thái Enabled/Disabled.
- [ ] Test launcher một click và các thành phần runtime/AI đã đóng gói; khi chỉ cập nhật patch không có EXE mới thì **không báo người dùng tính năng split UI đã hiện**.
- [ ] Kiểm tra dịch/biểu tượng trong Community Board và thông báo tuyển Clan.

## 6. Việc cần xác minh thêm
- Full changelog v0.4.10 và link phát hành, ngày publish; patch server cùng launcher EXE đúng release.
- Tên khóa cấu hình **hide list** và **auto-hide service buttons**; cách hot-reload/restart Community Board.
- Giá trị default thực của `FakePlayerBaseNpcId`, Olympiad roster size và Clan member cooldown (nếu không chỉnh).
- Quy tắc merge khi một file config có nhiều thay đổi/định dạng/comment khác nhau, cách rollback config.
- [GitHub Issue QA](../README.md) để theo dõi sau khi gắn issue số cụ thể.

Xem thêm [UPGRADE-RUNBOOK](UPGRADE-RUNBOOK.md) · [QA-AND-BACKLOG](QA-AND-BACKLOG.md) · [HANDOFF](../HANDOFF.md).
