# L2 Việt hóa 1.0.2 — Living Worlds v0.4.7
Ngày **2026-11-15**. **Cộng dồn từ 1.0**, gồm sửa cần giữ của 1.0.1. Không cần cài 1.0.1 trước.

## Nội dung mới [UPSTREAM]
[Nguồn chính thức](https://updates.l2livingworlds.space/releases.json) · [ZIP v0.4.7](https://updates.l2livingworlds.space/releases/v0.4.7-patch/L2J-Offline-Patch.zip).
- Bot trong đội theo người chơi qua Gatekeeper vào Elven Ruins, trả phí và tới cửa phía trong.
- Sửa bot/quái xuất hiện phía trên Elven Ruins/Elven Fortress, tránh đuổi quái khác tầng.
- Bot rời bãi tân thủ khi quái yếu: Talking Island/Dark Elf cấp 9, Orc/Dwarf 10, Elf 11; bãi đông từ cấp 5 chuyển ra đồng.
- Đội Living Population theo cấp thấp nhất: dưới 20 tối đa 3, từ 20: 5, từ 40: 7, từ 52: 9; vẫn chịu RealPartyMaxSize. Không suy ra mọi đội người chơi đều chịu giới hạn này.

## Tích hợp [OBSERVED]
Dựng lại core từ v0.4.7 rồi áp dịch/hook; giữ taming, action menu, lệnh Việt, wiki/chat AI, xưng hô và ba cầu nối portable.
Bổ sung 6 mẫu thông báo/log dungeon/bãi săn/đội bot; Config Editor không có chuỗi UI mới.
Gói upstream chứa 5 INI như changelog. So với v0.4.6 chỉ chú thích module.ini đổi; Feature.ini/General.ini/Rates.ini/test-gear INI không đổi byte. Giữ giá trị riêng đang dùng.

Payload 23 tệp kế thừa toàn bộ payload 1.0.1 rồi gộp thay đổi mới. Giữ bất biến mốc 1.0 và ZIP/checksum 1.0.1. Installer hỗ trợ 1.0, 1.0.1, cài lại 1.0.2; chặn code drift và hạ cấp từ bản mới hơn.

## Kiểm thử ngày 2026-11-15 [OBSERVED]
| Kiểm tra | Kết quả |
|---|---|
| 927 Java scripts, gồm taming; JVM/core và lệnh Việt | PASS |
| Config Editor: schema/default/giá trị và trường raid đã dịch | PASS |
| Bản chính và demo khởi động | PASS — đủ 6 modules, không lỗi startup; AI demo sẵn sàng |
| Hash bảo vệ bản dịch | PASS — bản chính 387, demo 392 tệp |
| Inventory bản chính | PASS — snapshot trước/sau giống byte |
| Dữ liệu demo ngay sau cài | PASS — 437 tệp không đổi bởi bước cài |
| Giá trị cấu hình demo | PASS — so với backup trước cài |
| 1.0 → 1.0.2 và 1.0.1 → 1.0.2 | PASS — bộ tệp bảo vệ thật của ZIP 1.0 và installer 1.0.1; cùng payload đích |
| Cài lặp, giữ save/giới tính/config, thêm default | PASS |
| Code drift/downgrade và lỗi ghi/rollback | PASS |
| ZIP CRC/hash; ba cầu nối AI riêng | PASS |
| Client visual, đi dungeon trực tiếp, soak nhiều giờ | NOT RUN |

Chưa đóng các bài gameplay ở [Issue #10](https://github.com/ronvotri/l2-tgds/issues/10). Không công khai raw logs/SQL/dữ liệu người chơi.

## Phân phối
- `L2-VietHoa-Patch-1.0-len-1.0.2.zip`: **7.766.138 byte** (~7,8 MB).
- SHA256: `e2f9d49d05b288ab9d4d4229400ae2e1077d94d085acdbc43a2c8baa9b29531a`.
- [Metadata](../releases/1.0.2.json) · [Thông báo group](../announcements/1.0.2.txt).
- Giải nén cạnh CHOI-NGAY.bat, chạy CAP-NHAT-1.0.2.bat, đợi thành công rồi mở game.
- Chủ dự án phân phối ZIP riêng. Repo chỉ lưu docs/checksum/QA.
