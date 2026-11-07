# Runbook cập nhật Living Worlds mà không mất Việt hóa/save

> Checklist dành cho **bản server cá nhân đã Việt hóa**. Không thay thế hướng dẫn cụ thể của launcher/phát hành đúng version. Trạng thái bản cài và vị trí file Việt hóa trên máy hiện **chưa được kiểm kê**.

## Nguyên tắc 0: Không ghi đè bản chính để thử

- Ghi **server version hiện tại → target version**; nếu cách hơn **một bản**, changelog v0.4.x khuyên dùng **Full download**, không chỉ Patch.
- Chụp danh mục module, client protocol, các chỉnh sửa tùy biến và mã hash file; dành một bản sao **staging** để test.
- Cất bản sao **database, toàn bộ server, client Việt hóa, module tùy chỉnh và config** ra nơi riêng, không cùng thư mục cài sẽ ghi đè.
- Sao lưu **bí mật ngoài GitHub** (đừng commit mật khẩu, database, log riêng tư).

## Trước khi update

- [ ] Ghi version cũ/mới, link bản phát hành và thời điểm backup.
- [ ] Nếu có lỗi, dùng **Collect logs** để ghi log phiên hiện tại và phiên trước; kiểm tra ZIP đã tạo.
- [ ] Nhấn **Stop** và chờ hẳn tới khi server tắt/saving hoàn tất; v0.4.1 công bố khắc phục lưu living bot sau khi DB đóng.
- [ ] Lưu **database + tiến trình player/clan/quest/raid**, toàn bộ `game/`, `login/` và mọi folder được launcher dùng, cấu hình, module và client.
- [ ] Kiểm tra có thể đọc backup, ghi hash SHA-256 (nếu có thể, restore bản copy riêng để kiểm tra).

### Ví dụ kiểm kê file (PowerShell; chỉ đọc)

```powershell
Get-ChildItem "D:\LivingWorlds-Backup" -Recurse -File |
  Get-FileHash -Algorithm SHA256 |
  Select-Object Path, Hash |
  Export-Csv "D:\LivingWorlds-backup-manifest.csv" -NoTypeInformation -Encoding UTF8
```

**Đổi đường dẫn thành đường dẫn thực tế.** Đừng chạy lệnh reset hoặc xóa database trước khi xác nhận bản sao.

## Những file đặc biệt cần chú ý

| Bản được công bố | File có nguy cơ bị thay |
|---|---|
| v0.4.1 và v0.4.2 | `FakePlayers.ini`, `game/modules/living-population/config/module.ini` |
| v0.3.0 | `FakePlayers.ini`, living-population `module.ini`, vùng `data/zones.xml`, `data/zone_combat.tsv` |
| Một số v0.2.x | `FakePlayers.ini`, living-population `module.ini` |
| Bản Full nói chung | **Chưa kiểm kê** — phải diff danh sách file trong gói thay vì đoán |
| v0.4.1 | `Rates.ini` **được công bố không bị patch thay**, nhưng có thêm giá trị mặc định mới |

**Không chép đè `module.ini`/ `FakePlayers.ini` cũ lên mới.** Phải diff và chỉ chuyển từng giá trị cần giữ; vì setting/default mới có thể được bổ sung.

## Sau khi update (trên staging)

- [ ] So sánh cây file old/new: **added / removed / modified**.
- [ ] Tách các file có nội dung đã Việt hóa: NPC HTML, scripts, properties, hệ thống client `.dat`, font, UI, mô tả module.
- [ ] Với file đã thay ở upstream: dùng **3-way merge** (base upstream cũ → upstream mới → custom Việt hóa) thay vì chép đè.
- [ ] Khởi động server test, xem log lỗi; check NPC/quest/đồ/skill/chat Việt hóa có dấu.
- [ ] Test Living Population, clan, party/healer/pet, Shout, Olympiad, Raid, Cold Sim và `.alt` Summoner.
- [ ] Chạy đủ lâu để kiểm tra lag; Stop server, chạy lại, xem DB và nhân vật/bot còn đúng.
- [ ] Thử Fermata/746 **sau khi** Interlude client chuẩn đã qua smoke test.
- [ ] Đánh dấu kết quả [OBSERVED] kèm version + thiết bị + bước tái hiện.

## Rollback (không được phép làm kiểu nửa vời)

1. Dừng server ở staging hoặc production đúng cách.
2. Restore **cả server files + database tương ứng cùng thời điểm backup**, tránh mix schema DB mới với server cũ.
3. Restore client/config/mod nếu đã thay đổi; khởi động test với một player.
4. Bảo toàn log lỗi và báo cáo riêng để điều tra, không xóa log/backup có ích.
5. Không cho client nhiều máy kết nối vào lúc rollback chưa hoàn tất.

## Lưu ý liên quan Interlude+
Bản alpha v0.4.6 có **installer backup file nó thay** nhưng không thay thế được backup server/database thủ công. Client pack **chỉ hướng đến Archive Client**; không cài thử trên server Việt hóa chính.
