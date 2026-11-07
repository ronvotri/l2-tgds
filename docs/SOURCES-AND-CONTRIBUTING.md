# Nguồn, độ tin cậy và cách bổ sung nghiên cứu

## Nguồn chủ chốt

1. **Changelog/thông báo từ cộng đồng Living Worlds** được người dùng gửi trực tiếp trong cuộc trò chuyện, qua các bài đăng của Teras và cộng tác viên; kênh: [Discord đã chia sẻ](https://discord.com/channels/1528476427979067524/1528476428587237499). Một số bài không có ngày/version chính xác trong phần trích.
2. **Trang dự án:** [Living Worlds](https://livingworlds.pages.dev/), [Modules](https://livingworlds.pages.dev/modules).
3. **Client mới:** [Fermata](https://fermata.gg/), [tài liệu admin compatibility](https://fermata.gg/docs/admins/compatibility/).
4. **Tool client mod:** [L2 Mod Manager releases](https://github.com/BeardBear33/L2-Mod-Manager/releases) — thông báo v1.0.2 có Server Connection, Character Name Display, Buy Catalog, Volume Slider, sửa nút Configure.
5. **Interlude+ alpha v0.4.6:** Thông báo của Sateriok gửi trong cuộc trò chuyện, có `message.txt` nhắc đến nhưng **chưa nhận đầy đủ tệp đó**. Nội dung ghi ở đây chỉ dựa trên đoạn patch notes được gửi.

**Lưu ý:** Link hình ảnh Discord kèm tham số `ex`/token có thể hết hạn. Không phụ thuộc chúng làm nguồn lưu trữ dài hạn. Link Discord có thể yêu cầu đăng nhập/quyền truy cập; các trích đoạn hội thoại đã chép vào dự án này đóng vai trò tư liệu tóm tắt, không phải chứng thực độc lập.

6. **Living Worlds hotfix v0.4.3**: thông báo Teras được người dùng cung cấp trong cuộc trò chuyện ngày 2026-11-07; nội dung tổng hợp tại [RELEASE-LIVING-WORLDS-0.4.3.md](RELEASE-LIVING-WORLDS-0.4.3.md), kiểm thử tại [Issue #6](https://github.com/ronvotri/l2-tgds/issues/6). Chưa có đường dẫn permalink thông báo hoặc bản vá trực tiếp, vì vậy trạng thái vẫn là [UPSTREAM], **không phải [OBSERVED]**.

## Nhãn độ tin cậy

- **[UPSTREAM]** Nhà phát triển đã công bố trong đoạn changelog. Chưa có QA độc lập.
- **[OBSERVED]** Đã chạy test trên build/version ghi rõ; có log/ảnh/video.
- **[PLAN]** Ý tưởng hoặc khuyến nghị phát triển; chưa cam kết có tính năng.
- **[UNKNOWN]** Thiếu chứng cứ xác thực, cần thử hoặc hỏi tác giả.
- **[ALPHA]** Tác giả ghi rõ chưa ổn định, có thể thay đổi.

## Mẫu thêm một cập nhật

```markdown
## Living Worlds vx.y.z
- Nguồn: URL thông báo/release, tác giả, ngày đăng (nếu có)
- Trạng thái: [UPSTREAM]
- Thay đổi: ...
- Default mới: ...
- File ghi đè/khả năng mất Việt hóa: ...
- Liên quan bot/AI/client/Ollama:
- Việc cần test: ...
- Kết quả [OBSERVED] (nếu có): version, thiết bị, log, ngày:
```

## Quy tắc làm việc với repo này

- Không giả định bản upstream đã được **cài trên máy của người dùng**.
- Không gộp nhầm số phiên bản của **Living Worlds**, **Interlude+**, **Fermata** hay **L2 Mod Manager**.
- Không coi một câu khẳng định từ AI trước đây là tài liệu chính thức nếu chưa có nguồn; ưu tiên văn bản changelog và test của người dùng.
- Chỉ lưu **tóm tắt/kịch bản cấu hình**, không đẩy dữ liệu tài khoản, log lộ địa chỉ cá nhân, key/token, bản game hay dữ liệu của người chơi.
- Khi chưa biết layout server hiện tại, hướng dẫn phải là **quy trình kiểm kê/diff/rollback**, không tự ghi file config cụ thể vào production.
- Mọi thay đổi docs nên cập nhật [HANDOFF](../HANDOFF.md) và [QA](QA-AND-BACKLOG.md) để Codex tiếp tục được.
