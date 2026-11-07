# HANDOFF — L2-TGDS / Living Worlds

**Ngày tạo handoff:** 2026-11-07  
**Vai trò repo:** kho nghiên cứu tiếng Việt, không phải repository upstream hay source game.

## Mục tiêu đã xác định
- Giữ một server **Lineage II Interlude Việt hóa** có thể nâng cấp không mất bản dịch/save.
- Chơi lại từ **level 1** cùng bot có tiến trình phát triển, phát triển clan/kinh tế/quan hệ xã hội.
- Ưu tiên server/AI **nhanh**, cân nhắc nhiều máy chơi LAN; máy host có **64GB RAM**, nhưng chưa biết GPU/VRAM.
- Xem xét client Fermata/746; Interlude+ chỉ khi tương thích, có test đầy đủ.

## Snapshot thông tin (không có nghĩa đã cài)
- Living Worlds release note được gửi gần nhất: **v0.4.3 hotfix** (thông báo tiếp sau v0.4.2): bot đồng ý Party thực sự mời và tìm đến bạn, healer cast chậm không bị cancel, quest kẹt quá 3 giờ được xử lý, Trade Chat Town Bot bật lại mặc định, world save độc lập khi status bị khóa, Reset Living Population giải tán living clans và giải phóng castle. [Chi tiết + QA](docs/RELEASE-LIVING-WORLDS-0.4.3.md).
- Nền tảng v0.4.2: dân số mặc định 500; event Shout, nhớ người chơi, clan war/alliance, local Ollama model keep-loaded.
- v0.4.1: AI party/fighter/healer/pet, store economy, server save an toàn.
- Tư vấn client: nhóm đề nghị chuyển từ **737 sang 746**; Fermata đã có compatibility patch **ban đầu**, chưa xác minh đủ tính năng.
- Module mở rộng **Interlude+ v0.4.6 alpha**: chỉ hỗ trợ Archive client trong thông báo, chưa chạy kiểm thử nội dung mới.
- Model `gemma3:12b` trước đó được đánh giá chậm; chưa có benchmark/VRAM nên chưa chọn model cuối cùng.
- Chưa có file server/Việt hóa, database, module hay client thực tế trong repo. **Không được suy ra source tree**.

## Điều đã làm trong repo
- Viết README định hướng và [niên biểu](docs/UPSTREAM-TIMELINE.md).
- Phân loại [các hệ thống AI/bot](docs/FEATURE-MAP.md).
- Soạn [kịch bản chơi lại từ cấp 1](docs/FRESH-WORLD-PLAN.md).
- Chuẩn hóa [client & rủi ro Việt hóa](docs/CLIENTS-AND-VIET-HOA.md), [quy trình update/rollback](docs/UPGRADE-RUNBOOK.md).
- Đã bổ sung [hotfix v0.4.3](docs/RELEASE-LIVING-WORLDS-0.4.3.md) vào niên biểu, QA và quy trình cập nhật.
- Ghi [AI/Ollama](docs/OLLAMA-AND-PERFORMANCE.md), [Interlude+](docs/INTERLUDE-PLUS-ALPHA.md) và [QA/backlog](docs/QA-AND-BACKLOG.md).
- Gắn [nguồn & nhãn tin cậy](docs/SOURCES-AND-CONTRIBUTING.md).

## Ưu tiên tiếp theo cho Codex/hỗ trợ viên khác
1. **Đừng thay đổi game thật khi chưa có backup**: kiểm kê phiên bản, cấu trúc file Việt hóa, DB và cách build/cập nhật server.
2. Tạo manifest file dịch, checksum, quy trình merge ba chiều; đặt script trong `tools/` nếu được cung cấp các file hợp lệ.
3. Dựng test staging + một bảng kết quả QA đúng **version/build/client**.
4. Khi reset quần thể bot: **v0.4.3 sẽ giải tán Living Clans, thả castle, dọn records và cho bot rời player clan**; phải backup và test riêng.
5. Chạy so sánh 100/200/500 Living Population, Cold Sim/Ollama, phân bố level bot trong world mới.
6. Thử client 746/Archive/Fermata **song song**, giữ client Việt hóa cũ.
7. Chỉ sau khi test mới quyết định tích hợp Interlude+; đang [ALPHA].

## Theo dõi tiến độ qua Issue
- [#1 P0 — Manifest dịch & backup](https://github.com/ronvotri/l2-tgds/issues/1)
- [#2 P1 — New world, level progression](https://github.com/ronvotri/l2-tgds/issues/2)
- [#3 P1 — Client/Fermata/Việt hóa](https://github.com/ronvotri/l2-tgds/issues/3)
- [#4 P1 — Ollama performance](https://github.com/ronvotri/l2-tgds/issues/4)
- [#5 P2 — Interlude+ Alpha](https://github.com/ronvotri/l2-tgds/issues/5)
- [#6 P1 — Hotfix 0.4.3 Party/Quest/Save/Reset](https://github.com/ronvotri/l2-tgds/issues/6)

[AGENTS.md](AGENTS.md) quy định cách làm việc với Codex; có Issue template cho release/bug mới.

## Các câu chưa giải đáp
- Phiên bản Living Worlds nào **thực sự đang chạy**? Có server source/module nào đang được quản lý ở repo khác?
- File Việt hóa nằm client/server/cả hai, font/encoding gì? Backup ở đâu?
- GPU/VRAM và loại ổ cứng trên máy host? Ai dùng chung Ollama?
- Có lựa chọn upstream để hạn chế tốc độ tăng level Living Population theo người chơi hay không?
- Shout/hội thoại mới có hiểu tiếng Việt và hỗ trợ UTF-8/font đầy đủ không?

## Nguyên tắc bảo toàn dự án
**Không tự nhận đã sửa gameplay, cài patch, build, Việt hóa, hay QA** nếu chỉ mới viết tài liệu. Không đưa dữ liệu cá nhân, bản game, database hay secrets lên repo công khai.
