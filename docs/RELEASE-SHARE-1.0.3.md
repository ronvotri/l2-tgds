# Bản chia sẻ 1.0.3 / Living Worlds v0.4.9

## Bổ sung 2026-11-19 — Xenos và hội thoại chưa dịch
+[OBSERVED] Tái hiện lỗi native HtmCache: với MultiLang vi và lazy cache, trang chưa có bản dịch trả null dù tệp gốc tồn tại; mở tiếng Anh trước làm cache ấm và che lỗi. Đã sửa riêng nhánh fallback để nạp tệp gốc khi cần, giữ ưu tiên bản dịch và kiểm tra đường dẫn. Đã thử native cold-cache Xenos/Dark Wizard, Clan, Alliance, tệp không tồn tại và chặn traversal. Bổ sung 24 trang Việt cho Dark Wizard tại Xenos/Tobias/Tronix, đổi nhãn chuyển chức, sửa protocol `_Script` bị dịch sai trong danh sách quest. Không đổi điều kiện cấp 20/Jewel of Darkness hay script chuyển chức.
+Đã cài main/demo, xác minh 415/445 tệp bảo vệ; installer 1.0–1.0.3/giữ save/config/rollback qua. Chưa click thử trong client. Giữ số 1.0.3, revision `xenos-html-fallback-20261119`; người đã tải 1.0.3 cần tải lại gói mới nhất. ZIP cũ giữ trong lịch sử cục bộ.
+

[OBSERVED 2026-11-18] Đã cài trên bản gốc và demo. Patch cộng dồn từ 1.0, gồm 1.0.1–1.0.2 và bổ sung nhặt hộ; không phải upstream mới.

## Tùy chọn nhặt hộ
Bot đồng hành → Tổ đội của bạn → **Bot nhặt đồ, người chơi nhận** (`FakePlayerPartyLootToPlayers`). Mặc định False. Bật, lưu rồi `//reload config` hoặc khởi động lại server. Muốn bot tự nhặt cần bật thêm `FakePlayerPartyPickup`.

Áp dụng cho đội có người chơi và Phantom/Living bot. Random/Theo lượt chia giữa người chơi đủ điều kiện. Ai nhặt nấy giữ: khi bot nhặt, ưu tiên đội trưởng đủ điều kiện. Không có người nhận hợp lệ do khoảng cách/túi đồ: giữ/thả lại đất. Adena không bị mất khi không có người nhận. Không thu túi đồ cũ, không đổi EXP, không đổi kinh tế đội chỉ gồm bot. Nhân vật autopilot vẫn tính là người chơi. Tắt công tắc trả về luật native.

## Kiểm chứng
- 23 tình huống native inventory trên server demo: Phantom/Living, cả 5 chế độ chia đồ, Spoil, Adena, không có người nhận và tắt tính năng.
- Helper production được thử riêng với doubles cho tải trọng, túi đầy, chết, khoảng cách, nhiều người chơi và reload.
- Giao diện thật: click bật/lưu/tắt/lưu; INI khôi phục byte-identical, không lỗi JavaScript.
- Installer từ tệp thật 1.0/1.0.1/1.0.2/1.0.3, cài lặp, giữ save/config/giới tính, chống downgrade/code drift và rollback đều qua.
- Hash bảo vệ main/demo khớp; script kiểm thử tạm đã xóa; server demo đã lưu và dừng.
- [UNKNOWN] Chưa kiểm tra thao tác nhặt đồ trong client hoặc chơi lâu nhiều người. Không suy rộng từ native inventory test sang live gameplay.

ZIP phân phối riêng. Xem metadata `releases/1.0.3.json`. Giữ nguyên các ZIP đã phát hành và baseline 1.0. Backup SQL/tệp gốc và demo trước thay đổi đã kiểm chứng.

---

## Nội dung cập nhật trước đó trong cùng bản 1.0.3

# Việt hóa 1.0.3 — Living Worlds v0.4.9
Ngày phát hành 2026-11-17. **Patch cộng dồn từ 1.0**, đã kiểm tra cả 1.0, 1.0.1 và 1.0.2; không cần cài tuần tự.

## Upstream [UPSTREAM]
[Feed](https://updates.l2livingworlds.space/releases.json) · [Patch chính thức](https://updates.l2livingworlds.space/releases/v0.4.9-patch/L2J-Offline-Patch.zip).
- Bot PvP/PK, nhớ kẻ hạ mình, trả thù và gọi đồng bang, tranh bãi, duel theo tính cách.
- Mở rương, xổ số/Monster Race, Dimensional Rift, cường hóa, nâng Soul Crystal và gắn SA; subclass hiện diện được trong thế giới.
- SimSpeed tới 100x gắn lịch thế giới với mô phỏng. Không tự tăng SimSpeed trên bản người dùng.
- Luật chiến đấu Interlude, EXP/party/drop, Clan C5 và hơn 60 sửa lỗi nền game. Xem [nghiên cứu combat](RELEASE-LIVING-WORLDS-0.4.9-COMBAT-RULES.md); gameplay từng tính năng vẫn cần test.
- Bảng Living Population tách Overview/Map/Bots/Rankings/Clans/Market/Settings, có trang bị/túi và icon item.

## Tích hợp Việt hóa [OBSERVED]
- Dựng core v0.4.9 rồi áp lớp dịch/hook cũ, không ghi đè core mới bằng JAR cũ. 83 chuỗi core mới, 188 mục UI/dynamic templates và 20 câu gọi trợ giúp theo tính cách được bổ sung/rà.
- Giữ dịch trong 6 script Java mà upstream sửa, mang các sửa gameplay mới của upstream sang nguyên vẹn ngoài literal dịch đã kiểm kê.
- Giữ 4 cầu nối AI portable. Upstream tách AI thành `brain_service`; bản này giữ entry chat Việt đang dùng bên cạnh package upstream để giữ wiki/news/personality/addressing, **không tuyên bố đã chuyển toàn bộ tùy biến sang kiến trúc modular mới**.
- Bản gốc có venv trỏ tới Python đã bị gỡ. Đã sao lưu launcher và thêm runtime Python độc lập lấy từ bộ chia sẻ hiện có, giữ venv/model/memory cũ. AI wiki bản gốc và demo trả lời Việt thành công.
- Chuẩn hóa `config-updates` về đúng đường dẫn cấu hình, merge ba chiều: giá trị người dùng đổi được giữ, mặc định chưa đổi nhận bản mới (ví dụ chi phí Clan C5). Không phát thư mục pending để startup tự ghi đè lần hai.
- Sao lưu và chỉ xóa `game/data/teleporters/others/30162.xml` nếu đúng hash đã biết; bản mới ở `others/IvoryTower/30162.xml`. Tệp tùy chỉnh khác hash làm installer dừng, không tự xóa.
- Giữ nguyên save, model, bản đầy đủ 1.0 và ZIP/checksum 1.0.1–1.0.2. Không thêm thay đổi luật loot; [giải thích nhặt đồ](BOT-LOOT.md).

## Kiểm tra [OBSERVED]
| Hạng mục | Kết quả |
|---|---|
| Java scripts và JVM hooks/lệnh Việt | PASS — 928 scripts |
| Bản gốc/demo | PASS — server nạp đủ 6 modules, error log startup rỗng |
| AI wiki Việt | PASS — bản gốc và demo |
| Config schema/default/serialize | PASS |
| Sáu trang quản lý mới, dữ liệu bot/trang bị và icon | PASS — browser headless, không lỗi JS/asset |
| Nhãn UI trang bot mới | PASS — ảnh đã xem, tên item giữ English |
| Inventory bản gốc | PASS — snapshot trước/sau giống byte |
| Save demo qua bước cài | PASS — 437 tệp giữ nguyên |
| 1.0/1.0.1/1.0.2 → 1.0.3 | PASS — dùng các bộ tệp bảo vệ thật, cùng payload đích |
| Merge mặc định mới + override; cài lặp; chặn downgrade/drift | PASS |
| Backup/xóa đúng teleport cũ và rollback lỗi ghi | PASS |
| ZIP CRC/hash và lưu trữ bản cũ | PASS |
| Client gameplay, giao dịch loot, soak nhiều giờ | NOT RUN |

Các log quyết định bot đã lưu từ phiên cũ có thể còn English; không sửa ngược dữ liệu save. Không công khai SQL, raw logs, secrets, tài khoản, model hoặc game binaries trong repo.

## Gói phát hành
- Tên: `L2-VietHoa-Patch-1.0-len-1.0.3.zip`.
- Dung lượng: **10.234.403 byte** (~10,2 MB).
- SHA256: `937a80464b75a70262268e7f823fdda579783387ad1e8e147975350e4b3a8f9f`.
- Giải nén cạnh CHOI-NGAY.bat; chạy CAP-NHAT-1.0.3.bat, đợi báo thành công rồi mở game.
- [Metadata](../releases/1.0.3.json) · [Thông báo group](../announcements/1.0.3.txt). ZIP được chia sẻ riêng.
