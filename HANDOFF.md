# HANDOFF — L2-TGDS / Living Worlds
Cập nhật: **2026-11-17**. Đọc [AGENTS.md](AGENTS.md) trước khi làm tiếp.

## Ghi nhận mới 2026-11-17 [UPSTREAM v0.4.9 — user confirmed]
Người dùng gửi **hai phần changelog mà người dùng xác nhận thuộc Living Worlds v0.4.9** về Combat & Rules sát Interlude: Magic Crit ×4 (cả PvP), level-gap EXP/Party bonus/raid drops, `DropGroupIndependentRolls` on, Clan C5/Seal of Strife, 60+ L2J Mobius base fixes, `Player.ini` có `CraftingInCombat` và `DisableCp` (đều off mặc định). **Chỉ ghi nghiên cứu, chưa cập nhật build/install/patch, chưa có QA gameplay.** [Tài liệu](docs/RELEASE-LIVING-WORLDS-0.4.9-COMBAT-RULES.md) · [Issue #12](https://github.com/ronvotri/l2-tgds/issues/12). **Mốc bản Việt hóa có báo cáo hiện vẫn là 1.0.2 / Living Worlds v0.4.7**; v0.4.9 mới là nhãn changelog, chưa có bằng chứng đã tích hợp.

## Cập nhật vừa triển khai
**1.0.2 / Living Worlds v0.4.7** đã build/cài trên bản gốc và demo, giữ Việt hóa. [Báo cáo](docs/RELEASE-SHARE-1.0.2.md). Gameplay dungeon trong [changelog](docs/RELEASE-LIVING-WORLDS-0.4.7-PATCH.md) vẫn cần test.

## Trạng thái đã kiểm chứng [OBSERVED]
- Máy chủ chính và demo chia sẻ đã lên **Living Worlds v0.4.7**, giữ bản dịch và các mod.
- Bản đầy đủ đang chia sẻ được chốt là **1.0**, nền upstream v0.4.5. ZIP gốc không thay đổi, không chứa save demo.
- Patch mới nhất **1.0.2**, cộng dồn từ 1.0; test nâng từ 1.0 sạch và từ 1.0.1 cho cùng payload. [Metadata](releases/1.0.2.json).
- Patch đầu tiên **1.0.1**, khoảng 7,8 MB, nền upstream v0.4.6; SHA256 và metadata tại [releases/1.0.1.json](releases/1.0.1.json).
- Người dùng muốn **mỗi người chơi một thế giới riêng trên máy họ**, không phải nhiều người vào một server chung.
- Giữ model **gemma3:12b** đã có. Bản chia sẻ đóng gói runtime và AI; không đổi sang model nhỏ khi chưa được yêu cầu.
- Launcher không còn tự tắt server theo tiến trình client trung gian. Dùng Lưu và tắt để lưu đúng cách.
- Config Editor tự nhận thư mục bản cài và tự mở khi chơi. Tên item/skill/quest item giữ tiếng Anh, mô tả và giao diện Việt hóa.
- Chat riêng dùng ông nếu đã chọn nam, bà nếu chọn nữ; kênh chung dùng bạn/mọi người, không ghép nguyên cụm hai cách gọi.
- 927 scripts đã biên dịch; cả hai bản khởi động đủ 6 module, không có lỗi startup. Giữ inventory bản gốc; 437 tệp dữ liệu demo không đổi bởi bước cài patch.
- Cấu hình mới được đối chiếu ngữ nghĩa; đã thử giữ giá trị riêng, thêm mặc định mới, cài lại cùng patch, phát hiện code drift và rollback lỗi ghi.
- Chưa kiểm thử gameplay dungeon/raid trực tiếp, hiển thị client sau lượt này hoặc memory/lag qua nhiều giờ. Không biến release note thành kết quả test PASS.

## Quy ước bắt buộc cho các lần sau
**1.0 + patch mới nhất = bản hiện tại.** Không bắt người chơi tải lại 13 GB hay cài từng patch trung gian.
1. Giữ nguyên mốc 1.0 và mọi patch đã phát hành, gồm 1.0.1.
2. Gộp tất cả thay đổi còn cần từ 1.0.1 vào patch kế tiếp, cộng thay đổi upstream/Việt hóa mới.
3. Tái tạo core từ upstream mới + mod đã kiểm kê; không dùng JAR cũ đè lên core mới.
4. Test từ 1.0 sạch, từ bản đã cập nhật và chạy lại cùng patch. Giữ cấu hình, database, thú, clan, bot và lựa chọn xưng hô.
5. Trả một thông báo tiếng Việt ngắn để chủ dự án đăng group. Mẫu: [announcements/1.0.2.txt](announcements/1.0.2.txt).
6. Xem [quy trình cộng dồn](docs/SHARE-RELEASES.md), [báo cáo 1.0.2](docs/RELEASE-SHARE-1.0.2.md) và [runbook](docs/UPGRADE-RUNBOOK.md).

## Mốc cục bộ để tìm lại
- Bản gốc: `E:\L2`; lớp dịch riêng: `E:\L2-VietHoa`.
- Gói đầy đủ 1.0 và demo: `E:\L2-Chia-Se-v0.4.5`.
- Lưu trữ phát hành, baseline và patch: `E:\L2-Phat-Hanh`.
- Tên thư mục v0.4.5 không quyết định phiên bản demo hiện tại; đọc `portable.json`, `share-release.json` và `launcher/version.txt`.
- Backup SQL, save, log, bộ game và model nằm cục bộ; không đưa lên repo công khai.

- [Issue #10](https://github.com/ronvotri/l2-tgds/issues/10): phần build/cài/smoke đã xong qua 1.0.2; còn gameplay dungeon/Party và bãi tân thủ. Không đóng chỉ vì server khởi động.

## Nghiên cứu còn mở
- Đánh raid với 4 đội phụ và kiểm tra di chuyển/né AOE; soak memory/GC dài giờ.
- Benchmark 100/200/500 bots, GPU/VRAM, độ trễ AI và tiến trình thế giới mới.
- Kiểm thử client 737/746/Fermata song song. Chưa thay client mặc định từ những trao đổi nghiên cứu.
- Interlude+ v0.4.6 alpha là dự án/module khác, không phải Living Worlds v0.4.6.
- Các checklist cũ chưa được kiểm thử vẫn giữ TODO; xem [QA](docs/QA-AND-BACKLOG.md). Không đóng các issue về gameplay chỉ vì server khởi động thành công.
