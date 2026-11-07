# Client, giao thức và rủi ro với bản Việt hóa

## Ba con đường client

| Lựa chọn | Mục tiêu | Mức độ chắc chắn |
|---|---|---|
| **Interlude 737 hiện hữu** | Giữ bản Việt hóa và client cũ đang dùng | Chưa có manifest bản hiện tại để xác minh |
| **Interlude 746 / Archive client** | Được nhóm khuyên dùng hơn protocol 737, nhất là khi thử Fermata | Thông báo tác giả: tìm `Lineage2-TCT-746-client` ở Archive.org |
| **Fermata** | Client remaster phía người chơi, giữ gameplay Interlude trên server Living Worlds | Đã có **compatibility patch ban đầu**; không đồng nghĩa 100% hệ thống chạy đúng |
| **Interlude+ alpha** | Module + client pack mở rộng gameplay | **Chỉ xây cho Archive client**; client khác có thể thiếu icon/tên hoặc crash |

**Quan trọng:** `737` và `746` là số **protocol**, không phải phiên bản patch gameplay hoặc chất lượng bản dịch. Khuyến nghị chuyển lên 746 không có nghĩa 737 bị cấm.

## Các dự án client khác đã được nhắc

### Lineage II Classic Client — thử nghiệm của Teras

Trước thông báo hỗ trợ Fermata, Teras cho biết đang thử **lớp chuyển đổi giao thức** để đưa một **Classic Client** vào server Living Worlds Interlude, **giữ nguyên logic gameplay Interlude**. Ông đã thử vào thế giới, tương tác NPC và đánh/giết quái, nhưng vẫn gặp lỗi mất kết nối. **Đây là proof of concept, chưa được công bố là client chính thức, không đồng nghĩa Fermata.** Không được nhầm Classic client nói trên với Interlude protocol 746.

### L2 Mod Manager v1.0.2

Công cụ mod client độc lập, không phải bản cập nhật Living Worlds. Theo changelog đã nhận:
- **Server connection:** chỉnh IP địa chỉ server;
- **Character name display:** hiện/ẩn tên và khoảng cách hiển thị;
- **Buy Catalog:** đưa đồ vào danh sách Private Store Buy mà không cần đang cầm vật phẩm;
- Volume sliders dùng các mức **6, 12, 26, 32, 43** thay vì giá trị tùy ý; sửa nút Configure ở mod không có tùy chọn.

**[UNKNOWN]** Chưa kiểm chứng L2 Mod Manager áp dụng được cho client 737, 746 Việt hóa hay Fermata. Test trên client copy riêng, nhất là các bản vá file `system`.

## Phân tầng Việt hóa

| Phần dịch | Nằm ở đâu thường gặp | Rủi ro |
|---|---|---|
| NPC dialogue / quest HTML / script / properties | Server | Có thể bị gói Full/Patch ghi đè nếu trùng file |
| Item/skill/system message `.dat` | Client Interlude | Có thể không được Fermata đọc theo cách cũ; dễ lỗi nếu chép nguyên dữ liệu 737 → 746 |
| HUD/menu/font/giao diện | Client | Fermata thay UI riêng; không tự bảo đảm giữ Việt hóa gốc |
| Mô tả module/Community Board | Server/module + dữ liệu client | Nội dung Interlude+ mới phải dịch và kiểm tra riêng |
| World/characters/Clans | Database/server | Không liên quan trực tiếp đến ngôn ngữ, nhưng cần backup dữ liệu trước update |

**[UNKNOWN]** Chưa biết bản Việt hóa hiện tại của người dùng nằm ở phần client, server hay cả hai, vì repo lúc khởi tạo trống và không có file bản dịch đi kèm.

## Quy tắc tích hợp

1. Giữ **client Việt hóa đang hoạt động** nguyên vẹn.
2. Tạo thư mục **client 746/Archive sạch** rồi thử kết nối tới bản server **test**, không đè file gốc.
3. Cài Fermata với patch Living Worlds đúng bản, kiểm tra chat/UI/quest/clan/skill/Party/Olympiad; **không kết luận tương thích tuyệt đối chỉ qua bước đăng nhập thành công**.
4. Với **Interlude+ alpha**: chỉ chạy trên bản server và Archive client test riêng; chưa được xác nhận tương thích Fermata.
5. So sánh từng file dịch và metadata (đường dẫn, checksum, encoding) trước khi port. Không copy nguyên thư mục `system` 737 sang 746.
6. Test dấu tiếng Việt (UTF-8/font), ký tự trong HTML NPC, vật phẩm, kỹ năng, hệ thống Shout/Whisper, popup và Community Board.
7. Nếu thay đổi cả server và client, khôi phục được trạng thái trước khi thay đổi.

## Manifest bản dịch (đề nghị bổ sung sau khi có backup)

| Trường | Giá trị |
|---|---|
| Living Worlds hiện cài | CHƯA XÁC MINH |
| Client đang chơi / protocol | CHƯA XÁC MINH |
| Phạm vi dịch client | CHƯA KIỂM KÊ |
| Phạm vi dịch server | CHƯA KIỂM KÊ |
| Encoding và font | CHƯA KIỂM KÊ |
| Module có chuỗi dịch | CHƯA KIỂM KÊ |
| Backup hash / ngày | CHƯA CÓ |

## Nguồn gốc
- [Fermata](https://fermata.gg/), [hướng dẫn](https://fermata.gg/docs/admins/compatibility/)
- [Living Worlds](https://livingworlds.pages.dev/) và thông báo cộng đồng về 746.
- Xem [SOURCES](SOURCES-AND-CONTRIBUTING.md) để phân biệt thông tin nhà phát triển công bố với kết quả kiểm chứng.
