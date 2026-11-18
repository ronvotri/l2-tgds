# Bản chia sẻ và patch cộng dồn
Quy ước do chủ dự án xác nhận ngày **2026-11-14**.

## Nghiên cứu upstream mới chưa tích hợp (2026-11-18)
Đã nhận đoạn changelog v0.4.10 về Community Board, Clans, Launcher và cơ chế updater **tự backup config đã chỉnh, giữ setting tùy chỉnh**. Đây chỉ là **[UPSTREAM]**, chưa build/cài patch Việt hóa mới. Bản phát hành vẫn **1.0 + patch cộng dồn 1.0.2 / LW v0.4.7**. Khi làm patch kế tiếp phải kiểm tra full gói v0.4.10 và bảo vệ save/bản dịch; không phụ thuộc hoàn toàn backup config của upstream. [Nghiên cứu](RELEASE-LIVING-WORLDS-0.4.10-PARTIAL.md).

## Ba số phiên bản khác nhau
| Dòng | Hiện tại | Ý nghĩa |
|---|---|---|
| Bản đầy đủ Việt hóa | 1.0 | Mốc ZIP cố định, nền Living Worlds v0.4.5 |
| Patch Việt hóa | 1.0.2 | Patch cộng dồn từ 1.0, nền Living Worlds v0.4.7 |
| Living Worlds | v0.4.7 | Phiên bản upstream; không phải Interlude+ alpha |

**Người có 1.0 chỉ tải patch mới nhất.** Bản hiện tại 1.0.2 có đường đi `1.0 → 1.0.2`, hoặc `1.0.1 → 1.0.2` với cùng gói; không bắt cài 1.0.1 trước. Cả hai đường đã test cho cùng payload đích. Các bản sau tiếp tục cùng quy tắc.

## Bảo quản mốc
- Giữ nguyên ZIP đầy đủ 1.0 và SHA256. Không đóng gói lại từ thư mục demo đã chơi.
- Giữ ZIP 1.0.1 + checksum riêng, không sửa đè một patch đã phát hành.
- Cất metadata/checksum/QA công khai; game binaries, AI model, SQL, log và save ở nơi phân phối/lưu trữ riêng.
- `releases/latest.json` chỉ patch mới nhất. Mỗi release ghi base=1.0, upstream version, checksum, dung lượng và các phiên bản đã test.

## Làm patch kế tiếp
1. Đọc baseline 1.0, overlay 1.0.1 và các patch đã phát hành; lấy thêm gói upstream mới.
2. Tạo trạng thái đích đầy đủ bằng merge upstream + Việt hóa/mod. Dùng các bản gốc làm base khi so sánh.
3. Từ trạng thái đích, lấy **toàn bộ khác biệt còn cần so với 1.0**, không chỉ phần khác với 1.0.1. Bao gồm launcher, font/UI, scripts, JAR, bot chat và những sửa lỗi trước đó còn phù hợp.
4. Gộp cấu hình riêng với defaults mới; không chép nguyên file cũ đè lên. Bản cài hiện tại giữ mọi giá trị đang có và thêm key mới; khi upstream đổi default phải đối chiếu baseline trước khi quyết định giữ/đổi.
5. Nếu phải xóa tệp cũ, dùng danh sách xóa tường minh đã kiểm tra phạm vi; không xóa thư mục dữ liệu hoặc suy ra xóa từ việc tệp vắng trong patch.
6. Cập nhật danh sách SHA/build được hỗ trợ trong installer; không chỉ chấp nhận mỗi bản ngay trước đó. Từ chối bản mới hơn để tránh hạ cấp.
7. Dừng qua yêu cầu lưu server, xác nhận đã thoát rồi mới thay tệp. Backup các tệp cũ và có rollback nếu ghi lỗi.
8. Chạy QA từ 1.0 sạch, từ bản 1.0.x đang dùng, cài lặp và giả lập lỗi ghi. Kiểm tra save, lựa chọn giới tính, cấu hình, bộ bảo vệ Việt hóa, server/AI/modules.
9. Giữ báo cáo, SHA và ZIP mới có tên riêng. Không dựng lại ZIP 13 GB nếu người dùng chỉ cần patch.

## Cài cho người chơi
Giải nén patch vào thư mục có `CHOI-NGAY.bat` và `Du-lieu`; chạy file `CAP-NHAT-<phiên-bản>.bat`, đợi thành công rồi mở game. Không dùng updater upstream ghi đè bộ Việt hóa chia sẻ.

## Thông báo group cho mỗi lần cập nhật
Mỗi release lưu `announcements/<version>.txt` và gửi bản copy-ready cho chủ dự án:
- Một dòng phiên bản.
- 2–4 thay đổi đáng chú ý; không hứa đã test một fix chỉ được upstream công bố.
- “Có 1.0 thì cài patch mới nhất, không cần cài từng patch.”
- Chỉ ghi giữ save/cấu hình/AI khi đã kiểm chứng. Không tự đăng vào group thay người dùng.
