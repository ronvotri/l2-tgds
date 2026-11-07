# QA & backlog triển khai

Quy ước: **P0** = bảo vệ dữ liệu / blocker, **P1** = bắt buộc trước khi đưa vào sử dụng nhiều máy, **P2** = tính năng mở rộng. Dấu checkbox là **chưa test trong repo**, không phải lỗi đã được xác minh.

## P0 — An toàn bản Việt hóa và tiến trình
- [ ] **P0-01** Kiểm kê bản server hiện cài, module, client protocol, tên và vị trí các file Việt hóa; lập checksum manifest.
- [ ] **P0-02** Backup có thể restore: server đầy đủ, DB, client Việt hóa, bản cấu hình tùy chỉnh, module, dữ liệu game không công khai.
- [ ] **P0-03** Dựng **staging** từ backup, cập nhật mục tiêu (v0.4.2 hoặc mới hơn khi có) không làm thay đổi production.
- [ ] **P0-04** Diff `FakePlayers.ini` và `living-population/config/module.ini` sau patch; chuyển từng setting, không chép nguyên file cũ.
- [ ] **P0-05** Test NPC, quest, tên skill, item, system message, chat, Community Board, dấu Việt trước/sau update.
- [ ] **P0-06** Test Stop/restart/restore, kiểm tra level, vật phẩm, Clan, bot memory, raid timers, warehouse, Olympiad reward.

## P1 — Thế giới bắt đầu lại cùng bot
- [ ] **P1-01** So sánh **Wipe Server** và **Reset Living Population**, chụp log/kết quả, không xóa nhầm world chính.
- [ ] **P1-02** Kiểm tra level bắt đầu/đường phát triển của bot mới; thống kê ở 0/1/3/5/10/24 giờ.
- [ ] **P1-03** Test 100/200/500 bots, Cold Sim 4.0 on/off so tài nguyên; All bots live off/on trong staging.
- [ ] **P1-04** Player lvl 1–20: hỏi shout tìm bot gần cấp, mời/nhận party, quest, class master.
- [ ] **P1-05** Healer/support buddy/pet/servitor: heal, buff, resurrect, tránh heal trùng, summoner dùng crystal/beastshots.
- [ ] **P1-06** Clan bot: tạo, gia nhập `.clanjoin`, quest, warehouse, grudge, war/alliance, siege; ghi quan hệ sau restart.
- [ ] **P1-07** Shout channel (event 30s, câu hỏi world thật, Trade Chat default off), kiểm tra tiếng Việt có hoạt động không.
- [ ] **P1-08** Thử 2+ client LAN: connect/party/trade/chat/teleport/logout/reconnect, firewall và IP nội bộ.

## P1 — Client & AI
- [ ] **P1-09** Dựng ma trận Interlude 737 Việt hóa / 746 Archive / Fermata; so hình ảnh, UI, quest, fonts, crashes.
- [ ] **P1-10** So tốc độ Ollama `gemma3:12b` vs model 4B; đo TTFT, token/sec, VRAM, 1/2/4 request.
- [ ] **P1-11** Kiểm tra `ollama ps` thực tế có GPU/CPU offload không; v0.4.2 giữ model nạp giải quyết timeout?
- [ ] **P1-12** Collect Logs: ZIP chứa cả hai server, phiên hiện tại và trước, không có secrets trước khi chia sẻ.

## P2 — Mở rộng / phát triển
- [ ] **P2-01** Xây script/Module telemetry đọc snapshot server để vẽ phân bố level bot và các sự kiện clan.
- [ ] **P2-02** Đánh giá nhu cầu module **giới hạn tốc độ tiến trình bot** sau khi benchmark, không mặc định upstream có sẵn.
- [ ] **P2-03** Việt hóa câu thoại mới và 1.700 template/profile mảnh v0.4.2 sau khi kiểm kê vị trí thật.
- [ ] **P2-04** Thử **Interlude+ v0.4.6 alpha** chỉ trên staging Archive client sạch; làm bảng compatibility riêng.
- [ ] **P2-05** Thử L2 Mod Manager v1.0.2 trên bản sao client, không patch đè file Việt hóa.

## Mẫu kết quả test

```markdown
### TEST-ID: P1-03 / 2026-xx-xx
- Trạng thái: PASS / FAIL / BLOCKED / NOT RUN
- Living Worlds version + commit/build:
- Module versions:
- Client/protocol:
- Host CPU/GPU/VRAM/RAM:
- Số bots; ColdModel; All bots live:
- Các bước tái hiện:
- Kết quả kỳ vọng:
- Kết quả thực tế:
- Log ZIP/ảnh/video: (đường dẫn bảo mật, đã che thông tin riêng)
- Bug upstream liên quan / ngày báo:
- Người xác nhận:
```

**Quy tắc:** Chỉ đổi item từ TODO sang DONE khi đã có log hoặc ảnh với version chính xác. Release note **không tính** là test pass.
