# Living Worlds v0.4.4 — World mở rộng, Seven Signs, pet/mount, cửa hàng bot, Grand Boss

> **[UPSTREAM]** Tổng hợp từ changelog Teras được người dùng cung cấp ngày **2026-11-12**. Tác giả cảm ơn Sateriok và cộng đồng đã gửi logs/heap dumps. Nội dung chưa được kiểm thử độc lập trên bản cài của người dùng; đánh dấu **[OBSERVED]** chỉ sau khi có log/screenshots với version chính xác.

## Tóm tắt quan trọng

- **Độ phủ bản đồ:** thêm khoảng 50 bãi săn và chia các khu lớn thành nhiều sub-area theo cấp, quái và tầng; bot đi giữa các khu con mà không cần về town.
- **Săn quái có nhận thức đông đúc:** bot dựa vào nơi từng săn, thông tin từ party/clan/láng giềng và giờ đông; ưu tiên thử lập party/chia bãi, ở lại ít nhất **30 phút** trừ khi có điều kiện tốt hơn rõ ràng.
- **Seven Signs / Festival / Mammon:** bot chọn phe Dawn/Dusk, săn Catacomb/Necropolis và đổi seal stones lấy Ancient Adena; Festival dành cho bot party >=5 cùng phe; điểm seal vẫn ưu tiên quyền quyết định của người thật.
- **Thú nuôi/cưỡi:** nâng Hatchling thành Strider, đôi khi cưỡi Wyvern nếu là chủ castle, baby pets có hồi máu, Beast Farm.
- **Đời sống town:** bot AFK mở buy/sell stores, dwarf crafters mở manufacture và tính phí **10%**; bot mua shots.
- **Party matching:** Hot Bots xuất hiện trong waiting list/party room; có thể kéo Cold Bots vào party và biến chúng thành Hot ở cạnh nhóm.
- **Grand Boss:** mặc định **BotGrandBosses=True**. Bot có thể săn Queen Ant, Core, Orfen, Zaken (ban đêm), Antharas, Valakas, Baium, Frintezza khi boss nhàn rỗi và không có người thật ở lair/đang đánh. **Nếu muốn giữ boss cho người thật: đặt False.**
- **Hiệu năng:** sửa rò rỉ trạng thái simulator gây tăng memory/GC, một skill xấu làm ngưng cả Cold Sim, và bãi đồ rơi ảo gần Primeval Isle.
- **Trang bị/kinh tế:** chỉ dùng đồ thực sự lấy được trong world; tự mua đồ tạm khi thiếu tiền, dual swords do thợ rèn chế, săn recipes thiếu và tối ưu lượng shots/potions theo 1h.

## I. Hiệu năng, tính ổn định

Changelog nêu 3 nguồn memory tăng theo thời gian:
1. Các bản sao bot mô phỏng bị kẹt trong **monster hate lists**;
2. Buff timers vẫn chạy trên các bot copies đã bỏ;
3. Caches ước lượng tích lũy vô hạn.

Đã sửa theo thông báo. Một skill lỗi cũng không còn ngưng toàn bộ bot mô phỏng nhiều giờ: lỗi được khoanh vào bot gặp vấn đề. Trường hợp hidden monster chết ở rìa map cạnh Primeval Isle không còn rơi herbs/loot ảo.

**QA:** đo RAM/CPU/GC, số bot, log, giật/freeze ở mốc 1h/3h/6h/12h/24h trên staging. Không kết luận fix hết memory leak khi chưa benchmark.

## II. Thế giới, phân mảnh bãi săn và crowd learning

- Thêm **~50 grounds**, các khu rộng như Cruma Tower, Devil's Isle được chia thành các **sub-area đánh số**, có quái/level/floor phù hợp.
- Bot có thể đi bộ giữa những area của cùng khu thay vì quay town và trả Gatekeeper.
- Quest hunt đến đúng area chứa quest mobs, và các bước quest được xét tại các cấp độ đúng nội dung quest.
- Bot lưu từ phiên bản cũ ở ground lớn tự được đưa tới area phù hợp level khi dữ liệu được chuyển.
- Crowd learning dựa vào: bãi từng đi, chia sẻ từ party/clan/lân cận, độ đông theo giờ. Đến chỗ đông sẽ **tìm party trước**, thử chia bãi. Bot bám bãi **ít nhất 30 phút**, chỉ rời nếu có khu rõ ràng tốt hơn; khi rời town đi tới đúng khu đã chọn, tránh khu không muốn vào.

**QA:** đánh dấu bot area cũ->mới, quest mobs, đa tầng, đi bộ giữa các khu, crowd thay đổi theo giờ, phân bố 100/200/500 bot và CPU/lag.

## III. Seven Signs / Festival of Darkness / Mammon

### Seven Signs
- Sau lần chuyển nghề đầu, bot đăng ký phe tại Priest: **Dawn** nếu clan/alliance của bot giữ castle, **Dusk** nếu không.
- Bot săn **6 Catacombs và 8 Necropolises**, theo điều kiện vào bình thường; giữ seal stones và đổi thành **Ancient Adena (AA)**.
- Bot **không được bỏ phiếu seal vào bảng điểm thực**, do đó người chơi thật quyết định seal mà một phe chiếm.
- Ở chế độ **không có lịch cố định**, competition tự kết thúc sau **7 ngày**, validation kéo dài **7 ngày**, có chu kỳ mới; điều chỉnh qua `SevenSignsCompetitionDays` / `SevenSignsValidationDays` trong `Feature.ini` (`0` tắt tự động). Vẫn có thể chấm dứt sớm tại Priest hay `//signs`. Nếu không người thật nào tham gia một chu kỳ, kết quả hòa.
- AA có giá tham chiếu **11 Adena**, NPC shop mua với **nửa mức đó**, bot bán như loot thường; `AncientAdenaPrice` trong `Feature.ini`.

### Festival of Darkness
- **Party bot >=5 cùng phe** có thể tham gia festival bằng mô phỏng, trả seal stones làm entry fee, **không chiếm slot** người chơi.
- `BotFestivalCountsForScore = False` mặc định: điểm festival bot **không được tính vào kết quả thi đua**, người chơi thật quyết định thắng thua.
- `FestivalPayOnCompletion = True` mặc định: phần thưởng Ancient Adena được trả ngay lúc gửi điểm theo pool và thứ hạng; có festival mỗi cycle kể cả chế độ không lịch.

### Mammon
- Bot mang gear A/S còn seal tới Blacksmith of Mammon, trả Ancient Adena để mở seal khi đây là nâng cấp thực.
- `MammonAlwaysSpawned` **off mặc định**, nếu bật thì Merchant và Blacksmith of Mammon luôn có mặt cho **cả bot lẫn người thật**.

**QA:** phe/clan/castle logic, điều kiện level và quyền vào Dungeon, AA trade/rate, lịch cycle/tie, score isolation, mammon không làm lệch economy.

## IV. Pet, mount, Beast Farm

- Bot có Hatchling trưởng thành thực hiện **Little Wing's Big Adventure** lấy Dragon Bugle.
- Cưỡi **Strider** khi đi ngoài giao tranh, xuống trước khi vào town, dừng hoặc đánh.
- Bot là chủ castle và có Strider đôi khi thuê **Wyvern** ở castle.
- Một số fighter chọn **Baby Buffalo / Cougar / Kookaburra** có hồi máu.
- Bot mua **Golden Spice**, cho thú hoang ở Beast Farm ăn và được buff từ thú đã thuần.
- Items pet/mount theo bot sau khi dùng warehouse; pet vẫn đánh dù flute ở warehouse.

**QA:** pet progression, quest, tiền/đồ, mount/dismount, inventory/warehouse, không nhân bản flute, sống sau restart.

## V. Cửa hàng, Party Matching, recommendations

### Cửa hàng trong thành
- Bot resting/AFK mở **Private Sell Store** cho hàng town trader không chứa hết; một bot Hot gần người thật **ngồi bán thật**, player mua được.
- Khi cửa hàng shots trống, bot có thể mở **Buy Store**.
- Dwarf Crafter nghỉ ở town mở **Dwarven Manufacture Store**, dùng **nguyên liệu của người chơi** và lấy phí **10%**.
- Idle bots mua shots từ kệ Merchant.

### Party Matching và đề cử
- **Hot Bots** chờ nhóm xuất hiện trong **Party Matching waiting list**.
- Một Hot Bot làm party leader với chỗ trống mở **party room thật** cho player vào.
- Party có Hot Bot có thể gọi bot đang **Cold** vào, biến chúng thành **Hot** gần party và cùng chiến đấu. Cold bots **không có trong party window khi chưa Hot**.
- Hot Bots có thể tự Shout tuyển party bằng nhân vật của mình; Cold Bots đã làm được từ trước.
- Bot tự **recommend** player từng đi Party trong ngày qua; ưu tiên người thật hơn bot; giới hạn theo quy tắc recommendation hàng ngày của server.

**QA:** không có ghost stores/rooms sau logout, đồng bộ cold->hot, party level gap/invite, giao dịch/giá/phí, load/lag khi nhiều bot ở town.

## VI. Grand Boss — chú ý cấu hình mặc định

- `BotGrandBosses = True` mặc định trong living-population `module.ini`.
- Các boss: **Queen Ant, Core, Orfen, Zaken (chỉ đêm), Antharas, Valakas, Baium, Frintezza** (8).
- Bot mua item yêu cầu để vào, kill kích hoạt **respawn boss theo server**.
- Bot **không đụng boss** khi **người chơi thật đang ở trong lair hoặc đang đánh nó**.
- Điều đó **không có nghĩa boss luôn được dành cho người thật**: ở những giờ không có người, bot có thể giết và kích hoạt respawn, khiến người chơi tới sau phải chờ.

**Khuyến nghị [PLAN] cho server chơi lại từ cấp 1:** nếu muốn giữ Grand Boss cho người chơi và bạn bè, đặt `BotGrandBosses = False` trong cấu hình mới; sau khi phát triển nhân vật và kiểm tra sức mạnh thế giới mới cân nhắc bật. **Không tự động chỉnh config sản xuất.**

## VII. Trang bị và nền kinh tế

- Gear bot chỉ được lấy từ shop có merchant thực, drop/spoil, recipes, quest rewards, starter gear; item chỉ có trong GM shop bị tháo ở lần load và thay thế trong lần đi mua tiếp.
- Clan Oath/Apella armor và Kamex test weapons không còn dùng cho bot. Mất Hero status thì bỏ Hero weapon.
- Bot thiếu weapon và không đủ tiền mua món muốn có sẽ mua vũ khí rẻ hơn tạm thời khi `BridgeWeapon` bật.
- Dual swords do blacksmith làm từ hai kiếm ở town/Mammon thay vì sinh tự do.
- Khi mở grade mới, server cung cấp recipes còn thiếu (ưu tiên shot recipes) để bot đi săn.
- Chọn bãi săn cân bằng EXP/Adena theo độ ham tiền của bot.
- Số shots/potions mua dựa trên **1 giờ săn gần nhất**, tránh một trận bất thường làm lệch kế hoạch.
- Living Clans không cất shadow items ở clan warehouse.

**Rủi ro cân bằng/QA:** kiểm tra việc thay gear trên bot đã tồn tại, giá kinh tế, gear bất hợp lệ, nguồn vật phẩm thật, bot không farm thiếu vũ khí.

## VIII. Tool admin, module hooks, Interlude+

- `l2admin` hiển thị **grade của gear đang mặc** thay cho nhãn starter/tier 0 chung.
- Module **test-gear** mới dành cho server test, **tắt mặc định**; lệnh `.testgear` mặc đồ full một grade và cấp shots/supplies cho bạn, mục tiêu, Party hoặc toàn bộ bot tùy lựa chọn. `TestGearAll` áp dụng mọi bot không cần lệnh, **mặc định Off**.
- Hooks mới: **pre-damage** được xem/sửa hit trước khi trúng; **teleport veto**; **risky enchant bonus**; stat flat damage reduction, buff duration, skill enchant bonus, HP thay MP; recipes có MP riêng/double-result chance; drops có chế độ display-only.
- **Interlude+ v0.4.6 alpha** trước đó có `Enchanting` tắt vì **thiếu enchant hook**. Living Worlds v0.4.4 đã công bố **bonus to risky enchant chances hook**, nhưng **chưa có xác nhận rằng module alpha sẽ tự bật hoặc hoàn toàn tương thích**. Cần hỏi tác giả và kiểm tra API thực tế trước khi cập nhật tài liệu module thành "đã hỗ trợ".

## IX. File cấu hình và giá trị mới cần giữ lại

### Cực kỳ quan trọng khi nâng cấp
- Tác giả: **bản cài cách >1 update thì dùng Full thay Patch**.
- Patch v0.4.4 **ghi đè `Feature.ini` và `living-population/module.ini`**.
- **Khác v0.4.3:** thông báo này **không nói patch ghi đè `FakePlayers.ini`**. Không suy đoán rằng nó vẫn bị thay.
- Backup cấu hình cũ và **merge từng setting** sang file mới (không chép ngược nguyên file cũ).
- [UNKNOWN] Các file dịch client/server có bị Full update đụng tới hay không: phải diff gói cài và kiểm thử riêng.

### Feature.ini
| Option | Giá trị theo thông báo |
|---|---|
| `SevenSignsCompetitionDays` | 7 ngày ở no-schedule; 0 tắt |
| `SevenSignsValidationDays` | 7 ngày; 0 tắt |
| `AncientAdenaPrice` | 11 Adena |
| `BotFestivalCountsForScore` | **False mặc định** |
| `FestivalPayOnCompletion` | **True mặc định** |
| `MammonAlwaysSpawned` | **Off mặc định** |

### living-population/module.ini
| Option | Mặc định/ý nghĩa theo changelog |
|---|---|
| `CrowdLearning`, `CrowdShareFirst`, `CrowdStickiness` | **On** |
| `CrowdMinStayMinutes` | **30** |
| `CrowdBusyHours` | **On** |
| `PartyRooms`, `HotPartyPulls`, `BotRecommends` | **On** |
| `PrivateStores`, `ManufactureStores`, `CraftersStayHot` | **On** |
| `BotStriders`, `BotMounts`, `BotWyvern` | **On** |
| `BabyPetShare` | **25** (giá trị theo changelog, **chưa xác nhận đơn vị**) |
| `BotBeastFarm`, `BotGrandBosses`, `BotFestival`, `MammonVisits` | **On** |
| `BotFestivalCountsForScore` | **False** (xuất hiện cả trong danh sách module.ini và giải thích festival; cần đối chiếu vị trí thật khi có tệp config) |
| `StrifeContestDays` | **7** |
| `StrifeContestScoring` | **Average** |
| `RecipeGoals`, `GearTradeCraftable`, `BridgeWeapon`, `SmoothSupplyRates` | **On** |
| `ZoneMoneyWeight` | **0.6** |
| `TestGearAll` | **Off** |

**Chú ý không chắc chắn về file thực:** Changelog nhắc `BotFestivalCountsForScore` ở cả phần mô tả Festival (`Feature.ini`) lẫn danh sách option module.ini. **Chưa thể khẳng định chỉ đặt trong file nào** khi chưa xem file phiên bản thực; nên ghi nhận cả hai và kiểm tra Config Editor/ini thay vì tự điền sai.

## X. Checklist kiểm thử staging

- [ ] Sao lưu đủ `Feature.ini`, living-population `module.ini`, DB/server/client Việt hóa; ghi version trước/sau.
- [ ] Baseline RAM và độ trễ Cold Sim 1h/3h/6h/12h/24h; kiểm tra monster hate list, buff timer, không freeze toàn bộ population.
- [ ] Vùng mới: bot phân bố ~50 grounds và sub-area, quest hunt đúng quái/tầng, chuyển vùng foot, chuyển đổi area save cũ.
- [ ] Crowd behavior: bám area ít nhất 30 phút, tìm party trước khi rời bãi đông, không dồn cả 500 bot vào một zone.
- [ ] Seven Signs: phe Dawn/Dusk đúng theo castle, 6+8 dungeon, AA drop/exchange, 7+7 cycle và tie; bot votes không đè player.
- [ ] Festival: 5+ cùng phe, fee, payout, không chiếm slot, score off default.
- [ ] Mammon: sealed gear đúng cần thiết, `MammonAlwaysSpawned` off default.
- [ ] Strider/Wyvern/Baby Pet/Beast Farm/warehouse, mount off trước town/combat.
- [ ] Town stores: giao dịch thật, manufacturer dùng mats + fee 10%, không trùng/cấp đồ miễn phí.
- [ ] Party Matching hot/cold chuyển trạng thái, room/waiting list, shout, recommend.
- [ ] Grand Boss: **nếu BotGrandBosses True**, bot chỉ đánh khi không có player trong lair/đang fight; boss respawn hoạt động; test disabled=False chặn bot.
- [ ] Gear legality, stop-gap weapon, Blacksmith duals, shot recipes, supply smoothing.
- [ ] Admin `l2admin` gear grade; `test-gear` chỉ chạy server test, `TestGearAll` off production.
- [ ] Với bản Việt hóa: NPC priest, Seven Signs, Festival, Mammon, Party Matching, title store, tên pet/mount/gear và hệ thống chat hoạt động có dấu.
- [ ] Sau Stop/restart/rollback: save clan/castle, economy, quest, pet, seal stones, AA, bot settings vẫn nguyên.

## Tương quan với mục tiêu người dùng

**[UPSTREAM] Đã tiến gần hơn tới MMORPG "một mình nhưng không cô đơn":** tự tìm Party qua UI, bot bán hàng/crafting thực, cưỡi thú đi đường, đi săn nhiều bãi, tham gia Seven Signs và thậm chí Grand Boss. **[UNKNOWN] Chưa có bằng chứng** mọi bot bắt đầu level 1, cùng tốc độ player, hay tất cả tính năng chạy ổn trên client Việt hóa/Fermata. Nếu muốn boss dành cho player, **đặt `BotGrandBosses=False` sau khi backup**.

Đọc thêm: [timeline](UPSTREAM-TIMELINE.md) · [world mới](FRESH-WORLD-PLAN.md) · [runbook](UPGRADE-RUNBOOK.md) · [QA](QA-AND-BACKLOG.md).
