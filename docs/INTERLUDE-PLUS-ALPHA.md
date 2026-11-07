# Interlude+ v0.4.6 Alpha (module mở rộng độc lập)

> **[UPSTREAM] [ALPHA]** Thông báo tác giả Sateriok. Tác giả nói rõ **"Nothing new in this version has run in game yet"** tại thời điểm đăng và cần nhiều test/bugfix. **Không phải Living Worlds server v0.4.6.**

## Mục đích
Mở rộng Interlude x1: tiến trình dài hạn theo account, talent, artifact, professions, thức tỉnh skill, gear tier, event, shop/service trên Community Board (Alt+B). Gồm **một server module + một client pack** có installer backup file bị thay, nhưng vẫn cần backup đầy đủ.

## Hệ thống mới

| Hệ thống | Chi tiết đã công bố |
|---|---|
| **Talents** | 10 archetype (Healer, Tank, Charge Fighter, Warrior, Artisan, Dagger, Archer, Nuker, Summoner, Support); mỗi archetype có 3 nhánh Offense/Defense/Utility, mỗi nhánh **13 node** |
| Talent points | +1 mỗi level từ level 2, đạt 79 ở level 80; riêng từng subclass; respec miễn phí; keystone theo mốc 40/50 điểm nhánh; `.talents` |
| **Artifacts** | 3 slot minor (Offense/Defense/Utility), 12 lựa chọn; 1 major slot với 8 lựa chọn active/proc: miễn debuff, berserk, hút máu, heal lớn/nhóm, reflect, self-revive, stun |
| Artifact charge | Từ level 20; phí EXP + SP; minor 1 charge, major 3; `.artifacts` |
| **Professions** | Harvesting đổi drop/adena/spoil lấy EXP; Fishing có cơ hội cá thứ hai/rương; **Enchanting tắt do Living Worlds chưa có enchant hook** |
| **Awakened Skills** | 66 thức tỉnh cho class thứ ba, mỗi class 1 chính + 2 signature; giá 50M / 25M Adena ở chế độ Interlude+; `.awaken` |
| **Gear** | Death, Sovereign armor; Death/Antharas weapon; Raid Jewels T2/T3; Grimoire/Rune S+/S++ sau rebirth |
| **Potions** | Infinite Superior HP/MP/CP heal ×1.5, một lần/account |
| **Invasions** | Bảo vệ Town Crystal qua wave; chết hồi sinh tăng 20 giây đến 60; thưởng Adena/loot theo từng wave |
| **The Descent** | Tầng thay vì đồng hồ; mỗi tầng +10% khó; elite có tính chất đặc biệt; mỗi 5 tầng boss; nghỉ 30 giây, có thể rút lui/đi sâu |
| **Phantoms trong event** | Có thể đi cùng chiến đấu; không nhận chia thưởng và không thể một mình giữ lượt chơi |
| **Custom mode** | Talent mạnh gấp đôi Interlude+, artifact +50%, trang bị khoảng +11%/tier thay vì +5%; cân bằng khác bản x1 |

## Client và Việt hóa

- **Chỉ xây cho "Archive client"** theo thông báo; client khác có thể **thiếu icon/tên, crash, hoặc không chạy**.
- Client pack tăng từ ~40 MB lên ~300 MB do thêm dữ liệu item/skill, model gear, icons.
- Các chuỗi mới (Talent, Artifact, Skill, Gear, Event, Community Board) tạo **backlog Việt hóa riêng**.
- **Chưa được xác nhận tương thích với Fermata**, client 737 Việt hóa hoặc dữ liệu .dat đã sửa.
- Tooltip major artifact còn placeholder MP/cast range; awakened skills dùng animation stock.

## Kiểm thử bắt buộc trước khi dùng server chính

- [ ] Cài bản sao Living Worlds test + Archive client sạch, kiểm tra installer backup và hoàn tác.
- [ ] Thử mở `.talents`, `.artifacts`, `.professions`, `.awaken`, Alt+B.
- [ ] Test cả 10 archetypes / 3 nhánh, điểm level, subclass, miễn phí respec.
- [ ] Test tạo, nâng cấp, hao EXP/SP và hiệu ứng 20 artifacts.
- [ ] Test 66 skill awakened, các tooltip, icon, chi phí và animation.
- [ ] Test gear tier trade-in x1 vs Custom, rebirth, chế độ mua bằng tiền, auto potion.
- [ ] Test Invasions/Descent với và không có Phantom, wipe/revive, thưởng khi rút lui/thất bại.
- [ ] Kiểm tra các UI mới có tiếng Việt, ký tự/font hợp lệ; không bê thẳng file dịch client khác sang Archive.
- [ ] Ghi lại cân bằng thực tế: tác giả tự lưu ý payout đang dựa giả định **120 kills/h**, chưa đo trong game.

## Kết luận triển khai
**Chỉ dùng staging.** Đợi bản có kiểm thử thực tế và danh sách tương thích rõ ràng trước khi cân nhắc tích hợp server Việt hóa.
