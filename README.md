# L2-TGDS · Lineage II Living Worlds — Sổ tay nghiên cứu & phát triển

> Kho tài liệu tiếng Việt để **theo dõi Living Worlds**, bảo vệ dự án **Việt hóa Lineage II**, thử nghiệm AI bot và chuẩn bị cho việc phát triển tiếp bằng GitHub/Codex.
>
> **Cập nhật tài liệu:** 2026-11-07. **Đây không phải source code chính thức của Living Worlds**, cũng không chứa client game, bản quyền tài nguyên, database hay bản sao save.

## Đọc từ đâu?

| Tài liệu | Nội dung |
|---|---|
| [Hotfix v0.4.3](docs/RELEASE-LIVING-WORLDS-0.4.3.md) | Sửa bot nhận lời Party, healer cast chậm, quest kẹt, world saves và reset living clan |
| [Lịch sử cập nhật](docs/UPSTREAM-TIMELINE.md) | Tổng hợp bản phát hành Living Worlds v0.2.2 → v0.4.3 từ những changelog đã thu thập |
| [Bản đồ hệ thống](docs/FEATURE-MAP.md) | Living Population, Phantom, .alt, Clan, kinh tế, ký ức, Cold Sim, AI Brain |
| [Kế hoạch khởi tạo thế giới](docs/FRESH-WORLD-PLAN.md) | Chơi lại từ level 1 và cùng phát triển với bot; những gì đã có và chưa được xác nhận |
| [Client & Việt hóa](docs/CLIENTS-AND-VIET-HOA.md) | Interlude 737/746, Fermata, Archive client, nguy cơ đối với bản dịch |
| [Quy trình cập nhật an toàn](docs/UPGRADE-RUNBOOK.md) | Backup, so sánh cấu hình/bản dịch, test, rollback |
| [Ollama & hiệu năng](docs/OLLAMA-AND-PERFORMANCE.md) | Chọn model nhanh, phân biệt AI hội thoại và AI chiến thuật, nhiều máy dùng chung |
| [Interlude+ alpha](docs/INTERLUDE-PLUS-ALPHA.md) | Module mở rộng v0.4.6 alpha và các rủi ro tương thích |
| [Checklist kiểm thử & backlog](docs/QA-AND-BACKLOG.md) | P0/P1/P2, bài test cụ thể và mẫu ghi nhận kết quả |
| [Nguồn & quy tắc nghiên cứu](docs/SOURCES-AND-CONTRIBUTING.md) | Phân biệt công bố từ tác giả với kiểm thử thực tế |
| [Bàn giao cho Codex](HANDOFF.md) | Điểm bắt đầu, trạng thái và nguyên tắc tránh làm hỏng bản Việt hóa |

## Mục tiêu dự án

1. Chơi **Lineage II Interlude offline từ level 1** trong thế giới có bot tự săn, lên cấp, quest, party, giao thương và lập clan.
2. Xây dựng cảm giác **cùng tiến triển với cộng đồng bot**, không nhầm việc bot *phát triển độc lập* với cơ chế *đồng bộ cấp độ* (chưa được xác nhận).
3. **Giữ nguyên và phát triển bản Việt hóa**, kể cả khi nâng cấp server hoặc thử client mới.
4. Ưu tiên **phản hồi AI nhanh và server ổn định**, có khả năng kết nối từ nhiều máy LAN.
5. Quản lý các thử nghiệm qua tài liệu, issue, log, checkpoint; không thay đổi server sản xuất nếu chưa có backup và kiểm thử.

## Trạng thái nghiên cứu (không phải trạng thái đã cài trên máy)

| Hạng mục | Trạng thái ghi nhận |
|---|---|
| Living Worlds | Changelog mới nhất được chia sẻ trong nghiên cứu: **v0.4.3** (hotfix; thông tin do tác giả công bố, chưa có test cục bộ) |
| Living Population | Công bố mặc định **500 bot** ở v0.4.2, tạo theo đợt ~3 giờ |
| Party & Quest v0.4.3 | Bot đồng ý Party sẽ gửi lời mời/đến thật, chữa quest chuyển nghề kẹt; Trade Chat Town Bot bật mặc định trở lại |
| Bot xã hội | Ghi nhớ tối đa 10 người chơi; shout, clan war, alliance đã được công bố |
| AI Brain/Ollama | Tùy chọn, **không bắt buộc để bot săn/lên cấp**; v0.4.2 nêu cải thiện giữ model đã nạp |
| Client Interlude | Nhóm khuyên **protocol 746** thay 737 để tương thích Fermata tốt hơn; không có nghĩa 737 bị vô hiệu hóa |
| Fermata | Đã công bố **patch tương thích ban đầu** với Living Worlds; phải test từng chức năng |
| Interlude+ | **v0.4.6 alpha của module**, không phải phiên bản server Living Worlds; client pack chỉ nhắm Archive Client |
| Server Việt hóa của người dùng | **Chưa có manifest/backup trong repo này**; không suy đoán file dịch nằm phía client hay server |
| Chạy thử thực tế | **Chưa ghi nhận kết quả kiểm thử có log trong repo** |

## Quản lý công việc qua GitHub Issues

| Ưu tiên | Việc | Issue |
|---|---|---|
| P0 | Kiểm kê file Việt hóa và backup có thể khôi phục | [#1](https://github.com/ronvotri/l2-tgds/issues/1) |
| P1 | Đo bot cùng phát triển trong thế giới mới từ level 1 | [#2](https://github.com/ronvotri/l2-tgds/issues/2) |
| P1 | So sánh client 737/746/Fermata và tiếng Việt | [#3](https://github.com/ronvotri/l2-tgds/issues/3) |
| P1 | Benchmark Ollama 12B/4B và nhiều máy | [#4](https://github.com/ronvotri/l2-tgds/issues/4) |
| P2 | Thử Interlude+ alpha trên staging | [#5](https://github.com/ronvotri/l2-tgds/issues/5) |

**Cho Codex và các phiên làm việc sau:** đọc [AGENTS.md](AGENTS.md) và [HANDOFF.md](HANDOFF.md) trước khi sửa bất cứ thứ gì. Có sẵn mẫu Issue cho [changelog](.github/ISSUE_TEMPLATE/release-research.md) và [QA/bug](.github/ISSUE_TEMPLATE/test-report.md).

## Nguyên tắc an toàn

- **Không đưa lên GitHub công khai** mật khẩu, token, địa chỉ IP công khai cá nhân, database người chơi, log chứa thông tin riêng tư, client/asset có bản quyền.
- Phân biệt: **[UPSTREAM]** tác giả công bố; **[OBSERVED]** đã test có log; **[PLAN]** đề xuất; **[UNKNOWN]** chưa được xác minh.
- Mọi lần cập nhật phải có bản sao server, database, bản Việt hóa và danh sách file tùy chỉnh; tuyệt đối không chép đè cấu hình cũ lên cấu hình mới một cách mù quáng.
- Giữ client Interlude gốc hoạt động song song khi thử Fermata hoặc Interlude+.
- Không nhầm bản Việt hóa với bản mod AI, và không nhầm **Interlude+ v0.4.6** với bản **Living Worlds v0.4.3**.

## Nguồn tham khảo

- [Living Worlds](https://livingworlds.pages.dev/) · [Thư mục module](https://livingworlds.pages.dev/modules)
- [Kênh Discord thông báo đã được chia sẻ](https://discord.com/channels/1528476427979067524/1528476428587237499)
- [Fermata](https://fermata.gg/) · [L2 Mod Manager](https://github.com/BeardBear33/L2-Mod-Manager/releases)
- [Niên biểu nguồn và giới hạn xác minh](docs/SOURCES-AND-CONTRIBUTING.md)

---
**Ghi chú:** Repo này hiện ưu tiên làm **sổ tay nghiên cứu**. Muốn tích hợp kỹ thuật về sau, hãy kiểm kê phiên bản, file hiện có, quyền sử dụng và chụp backup trước khi triển khai.
