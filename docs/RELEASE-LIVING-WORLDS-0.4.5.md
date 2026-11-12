# Living Worlds v0.4.5 — Castles, Clan Halls, Olympiad, Noblesse, Dyes và nghề phụ

> **[UPSTREAM]** Dựa trên changelog Teras được chia sẻ **2026-11-12**; tác giả cảm ơn **Sateriok** vì Castle/Clan Hall/Olympiad/progression. Nội dung này **không phải kết quả kiểm thử trên server người dùng**. Khi test cần ghi [OBSERVED] kèm bản cài, client, log và ảnh.

## Tóm tắt một câu
Bot không chỉ săn/cày, kinh doanh và lập Clan (v0.4.4) mà còn **quản lý Castle, tranh Clan Hall, học Clan Skills, đạt Noblesse và tham gia Grand Olympiad**; kèm sửa pathfinding và thay đổi khá quan trọng tới `General.ini` và `Rates.ini`.

## Cảnh báo khi cập nhật v0.4.4 → v0.4.5

- Nếu bản cài cách **hơn một bản cập nhật**, dùng bản **Full** thay patch, theo thông báo.
- **Patch v0.4.5 ghi đè `living-population/module.ini`, `General.ini`, `Rates.ini`.** Ghi và backup các cài đặt đã chỉnh, merge từng khóa vào file mới sau khi cập nhật; không chép nguyên config cũ để tránh mất tùy chọn mới.
- **`CustomBuyListLoad = False` trong `General.ini` trở thành mặc định.** Người dùng có *custom buylists* (shop lists tùy chỉnh) phải chủ động chuyển **True** để server nạp các danh sách này. Điều này ảnh hưởng trực tiếp tới server có cửa hàng đã chỉnh sửa; **chưa xác nhận nó ảnh hưởng đến mọi file Việt hóa**.
- Patch này **không nói** ghi đè `Feature.ini` hoặc `FakePlayers.ini`; đừng tự suy luận. Khi dùng **Full** hãy diff toàn bộ file thực bị thay.
- Không có thông báo bắt buộc reset world/DB. **Không reset**, không thử Clan/Castle/Manor trên world chính trước khi backup.

## 1. Castle tự vận hành

- `ClanCastleOps`: Living Clan giữ lâu đài **thiết lập thuế**, **lập kế hoạch Manor** và **gia cố cổng/tường** trước Siege.
- `CastleMercenaries`: Chủ thành thuê một số lính đánh thuê qua **mercenary tickets trong keep**, trả từ **quỹ Clan**.
- **Manor trở về luật retail:** lâu đài **không có chủ** thì **không bán seed và không thu mua crop**. `ManorSteward = True` khôi phục **Manor có hàng tại mọi lâu đài** như cách vận hành trước.
- `BotManor`: bot gieo trồng, thu hoạch, bán crop cho Manor Manager nếu hiệu quả kinh tế hơn săn quái thông thường.

**Rủi ro world mới:** nếu lâu đài chưa có chủ, Manor sẽ không sẵn hàng như trước. Đừng nhầm đó là lỗi NPC/cửa hàng hoặc mất Việt hóa. ManorSteward thay đổi quy tắc kinh tế **cho người chơi lẫn bot**.

## 2. Clan Hall, Clan Skills, Academy

- `ClanHalls`: Living Clan tham gia **đấu giá** Clan Hall, duy trì quyền sở hữu và sử dụng các chức năng Clan Hall.
- `HallSieges`: Living Clan đăng ký và chiến đấu vì Clan Hall tại **Fortress of Resistance, Devastated Castle, Fortress of the Dead, Rainbow Springs, Bandit Stronghold, Wild Beast Reserve**.
- Clan Hall **đã thuộc hoặc có đăng ký bởi Clan người chơi thật sẽ được bot để yên**.
- Tiến trình Clan Hall siege **được lưu qua restart server**.
- `ClanSkills`: lãnh đạo Living Clan dùng **Clan Reputation** học Clan Skills.
- `AcademyGraduates`: học viên tốt nghiệp Academy chuyển lên Clan chính **nếu còn chỗ trống**.

**QA:** Đấu giá có trừ Adena chính xác, không giành Hall player; save qua restart; Clan Reputation, Academy slots và castle treasury không bị sai.

## 3. Noblesse, Grand Olympiad, Hero

- `NoblessePath`: Living Bots phát triển theo **chuỗi nhiệm vụ Noblesse**.
- `ColdOlympiad`: Noblesse bots **thi đấu được mô phỏng (trên giấy)** với nhau khi không có mặt live.
- `HotOlympiad`: **Hot Noblesse Bot** đăng ký xếp hàng vào Olympiad **thật** và chiến đấu trong hệ thống arena thực, để người chơi gặp bot tại đấu trường.
- `BotHeroes`: Hero bots nhận danh hiệu Hero, mặc **vũ khí Hero đúng class**, và chat trong **Hero channel**.
- Nhân vật **của người chơi** khi bật autopilot **vẫn giữ quyền nhận thưởng Olympiad**.

**Cần phân biệt:** mô phỏng cold **không phải** một trận PvP trực tiếp để player xem. Không có xác nhận bot là đối thủ người chơi trong mọi kỳ/lịch đấu khi cấu hình khác.

**QA:** luật đăng ký, Noble status, scoring/ranking, Hero reward/weapon, Hero channel; không trùng phantom hoặc phần thưởng kỳ trước; save giữa period và restart.

## 4. Dyes, augments, Fishing

- `BotDyes`: bots tự vẽ Symbols/Dyes tại Symbol Maker.
- `BotAugmentation`: bots tự nâng vũ khí bằng Life Stones.
- `BotFishingRewards`: mở cá, mở treasure chests, đổi chiến lợi phẩm tại Fishing Guild, học **Expand Inventory**, **Expand Storage**.

**QA:** phí/nguồn vật liệu đúng, không tự sinh Life Stones/dyes, stat augment hợp lệ, quest/game progression và giới hạn túi/kho không bị phá.

## 5. Điều khiển quần thể và tỷ lệ chủng tộc

Trong living-population `module.ini`:
- `RaceShareHuman`, `RaceShareElf`, `RaceShareDarkElf`, `RaceShareOrc`, `RaceShareDwarf`: thiết lập **tỷ phần chủng tộc cho bot được sinh ra mới**; không có xác nhận tự chuyển đổi chủng tộc bot cũ.
- `NamedBots = False`: tắt ba bot có tên **aiSateriok**, **aiSonOfZion**, **aiTeras**.

**QA:** kiểm tra tỷ lệ theo quần thể sinh mới (không tự khẳng định đơn vị/tổng phần trăm nếu chưa thấy file config mẫu). Kiểm tra NamedBots tác động lên bot hiện tồn tại hay chỉ lần sinh sau.

## 6. Rates.ini, điều chỉnh nguồn loot và shops

- `Rates.ini` có **mô tả và ví dụ rõ hơn** cho mỗi Rate.
- Thêm **never-drop list**, và công tắc **No skill books** để ngăn sách kỹ năng rơi.
- **Drop amount rate chỉ tăng số lượng item có thể stack**: giết một quái không tạo nhiều vũ khí (non-stackable) giống nhau chỉ vì rate amount cao.
- Các trang Rates trong `l2admin` đồng bộ theo file mới.
- Custom buylists có **README và template**; bot chỉ đọc custom lists **khi server thực sự load**. **`CustomBuyListLoad=False` mặc định trong General.ini**, muốn dùng buylists riêng phải bật lại `True`.

**Ảnh hưởng tới Việt hóa/customization:** nếu bạn đổi shop lists và dùng nội dung custom riêng, thay `General.ini` có thể khiến dữ liệu không được nạp dù bản thân file không bị xóa. Kiểm tra cấu hình và danh sách shop sau update, không coi đó là mất file dịch.

## 7. Bugfix — di chuyển, Cursed Weapons, Raid

- Elven Village: bot biết đi lên **sàn gỗ/nhà trên cao** ở hồ, không kẹt dưới hồ.
- Catacomb of the Heretic: xử lý vị trí nhỏ ở phòng đầu; nếu đường đi không tìm được, pathfinder **thử tầng khác tại cùng điểm**.
- Bot giữ **Zariche/Akamanah** không ngồi chơi trong Town: ở lại Field, tấn công người chơi và bot theo luật Cursed Weapon bình thường.
- Party bot đánh **Queen Ant** hạ **Nurse Ants trước**; casters không phí opening bằng debuff lên raid adds.
- Sửa một số startup warnings và XML errors.

## 8. Các setting mới được công bố

| File | Key | Diễn giải |
|---|---|---|
| `living-population/module.ini` | `ClanCastleOps` | Chính sách thuế/Manor/gia cố Castle |
| same | `CastleMercenaries` | Bot thuê lính từ quỹ Clan |
| `General.ini` hoặc file cấu hình phù hợp (cần xem config thực) | `ManorSteward` | True khôi phục Manor được stock ở mọi Castle |
| `living-population/module.ini` | `BotManor` | Bot tham gia trồng/thu hoạch/mua bán Manor |
| same | `ClanHalls`, `HallSieges` | Đấu giá, sử dụng và Siege Clan Hall |
| same | `ClanSkills`, `AcademyGraduates` | Clan Skill và chuyển học viên tốt nghiệp |
| same | `NoblessePath`, `ColdOlympiad`, `HotOlympiad`, `BotHeroes` | Noblesse, Olympiad mô phỏng/thật, Hero |
| same | `BotDyes`, `BotAugmentation`, `BotFishingRewards` | Dyes, Life Stones, chiến lợi phẩm câu cá |
| same | `RaceShareHuman`, `RaceShareElf`, `RaceShareDarkElf`, `RaceShareOrc`, `RaceShareDwarf` | Cơ cấu bot sinh mới |
| same | `NamedBots` | False để tắt 3 bot có tên riêng |
| `General.ini` | `CustomBuyListLoad` | **False mặc định**, bật True nếu dùng custom buylists |
| `Rates.ini` | Never-drop list / No skill books | Cần mở file mới để xác nhận **tên khóa cụ thể** |

**Không tự gán tên file cho những khóa mà changelog không nêu rõ.** Đặc biệt `ManorSteward` được thông báo tên khóa nhưng không xác định chính xác file chứa nó, nên phải tìm từ Config Editor/file thật trước khi sửa.

## 9. Checklist QA trên staging

### P0: bảo vệ dữ liệu/Việt hóa
- [ ] Backup full server + database + custom client Việt hóa + `living-population/module.ini`, `General.ini`, `Rates.ini`.
- [ ] Diff các file patch đè, giữ option mới và merge các thiết lập người dùng.
- [ ] Kiểm tra shop lists tùy chỉnh: `CustomBuyListLoad=True` khi cần, không bật mù nếu không có shop tùy chỉnh.
- [ ] Restore bản cũ từ snapshot, test clan hall/siege/world timers, quest, heroes, inventory, castle tax/manor.
- [ ] Kiểm tra NPC Symbol Maker, Clan Hall manager, Olympiad Manager, Manor Manager, Fishing Guild và đồ/skill Việt hóa hiển thị đúng.

### P1: gameplay
- [ ] Castle tax/manor/defence/mercenaries, castle vô chủ **không** có seed/crop theo luật mới.
- [ ] Auction Clan Halls, 6 địa điểm hall siege, bot không phá Hall của player, siege state sau restart.
- [ ] Clan Skills, AcademyGraduates chuyển đúng khi còn chỗ.
- [ ] Cold/Hot Olympiad, bot Noble/Hero, Hero weapons/chat, player autopilot Olympiad rewards.
- [ ] BotDyes, BotAugmentation, FishingRewards, túi/kho sau nâng.
- [ ] RaceShare phân phối bot **mới**, NamedBots False tác động vào 3 named bots.
- [ ] Drop rate cho stackable vs non-stackable, never-drop, No skill books, l2admin Rates.
- [ ] Elven Village decks/upper floors, Heretic catacomb floors, Cursed Weapon bots, Queen Ant Nurses và caster openers.
- [ ] Chạy soak test sau 0.4.4/0.4.5 và xác nhận các bản save vẫn ổn.

## Tác động tới mục tiêu chơi lại từ level 1

- Bot không chỉ có thế giới săn quái, kinh tế mà còn có **tiến trình Clan/Raid/PvP dài hạn** sau khi bạn nâng cấp.
- Những thành có chủ sẽ ảnh hưởng kinh tế Manor, khiến bối cảnh giả lập tiến hóa cùng quần thể bot.
- **Chưa có xác nhận** tất cả living bots bắt đầu cùng cấp 1 hoặc tiến triển ngang người chơi; v0.4.5 cũng không giới thiệu hệ thống đó.
- Khi thử v0.4.5, cần quyết định **có muốn bots chiếm Castle/Clan Hall và tham gia Olympiad** từ sớm không. Đây là lựa chọn cân bằng game chứ không đơn thuần kỹ thuật.
- **Server có bản Việt hóa:** ưu tiên kiểm tra `General.ini`, `Rates.ini` và custom shop lists; không sửa server chính khi chưa có backup.

Xem: [Niên biểu](UPSTREAM-TIMELINE.md) · [Quy trình nâng cấp](UPGRADE-RUNBOOK.md) · [QA](QA-AND-BACKLOG.md).
