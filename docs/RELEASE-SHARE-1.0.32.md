# Bản chia sẻ 1.0.32 / Living Worlds v0.4.9

[OBSERVED 2026-11-19] Patch cộng dồn từ 1.0, gồm các sửa 1.0.1–1.0.31. Thêm QUAN-LY-GM.bat và nút Quyền GM / Tạo đồ trong bảng điều khiển. Công cụ chỉ thay EverybodyHasAdminRights, tự sao lưu General.ini, không sửa database/tài khoản/nhân vật. Không bật GM khi cài; người dùng tự chọn rồi lưu/tắt và mở lại server. Bật quyền chung áp dụng mọi nhân vật; tắt quyền chung không tự thu hồi quyền riêng đã lưu.

Kèm hướng dẫn tạo tài khoản ngay trên màn hình đăng nhập (AutoCreateAccounts mặc định True), tạo nhân vật, //admin, //itemcreate và ví dụ //create_item 57 1000000. Giữ thiết lập riêng nếu chủ máy đã tắt auto-create.

## QA
- Installer từ tệp thật 1.0/1.0.1/1.0.2/1.0.3/1.0.31, cài lặp, giữ save/config, chống downgrade/code drift, rollback đều qua.
- GM enable/disable: 4 ca BOM/no-BOM, LF/CRLF, tiếng Việt, backup byte-identical, không đổi thiết lập khác, cài lặp và từ chối key trùng.
- PowerShell parse và dựng WinForms offscreen qua; không điều khiển chuột/phím hay chơi thử GM trong client.
- Demo đã lên 1.0.32, kiểm tra backend bật/tắt thực tế rồi trả General.ini về nguyên byte cũ, hash bảo vệ khớp. Không cấp GM vĩnh viễn hoặc tạo vật phẩm trên nhân vật.
- Payload game core giữ bản sửa Xenos và tùy chọn bot nhặt hộ; không nâng upstream.

ZIP phân phối riêng, [metadata](../releases/1.0.32.json). Hướng dẫn người chơi đi kèm trong ZIP. Baseline 1.0 và các ZIP cũ giữ nguyên. GitHub còn chờ xác thực, không coi commit cục bộ là đã đẩy remote.

## Sửa cửa sổ bị ẩn — cùng bản 1.0.32
[OBSERVED 2026-11-19] STARTUPINFO Hidden khi mở PowerShell cũng ẩn cửa sổ WinForms đầu tiên. Đã xác nhận nhiều tiến trình GM có form nhưng IsWindowVisible=False. Thêm UiWindow.ps1: hiện form sau lượt ShowWindow đầu, singleton theo thư mục/bảng, đưa cửa sổ cũ lên, lưu lỗi child process và thông báo khi khởi động lỗi. Áp dụng Panel, GM và cửa sổ tiến trình từ Shared.ps1.

Đã thử tiến trình GM thật với WindowStyle Hidden ở fixture riêng: form hiện, mở lặp tái sử dụng, mở bảng không đổi cấu hình. Dispatch đủ 4 callback Panel với external launches được thay bằng stub; chưa bấm Lưu và tắt lên server thật để tránh ngắt phiên chơi. Trên máy chủ đã đóng nhẹ nhàng 19 cửa sổ UI trùng, mở lại Panel và GM, xác minh cả hai visible; không dừng game/database/AI. Thay 4 script UI + hash manifest có backup; core và GMSettings giữ nguyên. ZIP cũ giữ lịch sử; revision visible-ui-hotfix-20261119. Người đã tải 1.0.32 cần tải lại ZIP mới nhất.
