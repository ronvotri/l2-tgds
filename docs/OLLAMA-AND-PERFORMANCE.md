# Ollama / AI Brain / Hiệu năng

## Tách hai lớp AI

- **AI server Living Worlds**: bot tự farm, lên cấp, mua bán, quest, hỗ trợ party, clan, Shout định tuyến theo trạng thái; nhiều chức năng chạy **không cần Ollama**.
- **AI Brain / Ollama**: hỗ trợ hội thoại linh hoạt, hiểu/ngữ sinh ngôn ngữ tự nhiên; không thay thế thuật toán săn quái/Cold Sim mặc định.
- **Iron Bot Discord**: bot hỗ trợ cộng đồng, có fingerprint theo repository version để tránh dùng câu trả lời cũ; không phải bot điều khiển NPC.

v0.4.2 công bố **giữ local Ollama model loaded** để tránh lỗi khi model nằm trên ổ chậm khởi tạo lần đầu. Điều đó **không đồng nghĩa** số token/giây của model lớn tăng lên.

## Hiện trạng mục tiêu

- Máy chủ dự kiến có **64 GB RAM hệ thống**; muốn **phản hồi nhanh**, có thể chia sẻ tài nguyên khi nhiều máy cùng chơi.
- Model `gemma3:12b` mặc định trước đây được nhận xét **chậm**; nguyên nhân chưa xác định vì **chưa có thông tin GPU/VRAM, tốc độ token hay `ollama ps`**.
- Dung lượng RAM hệ thống đủ lớn **không thay thế** VRAM GPU và không bảo đảm model chạy GPU.

## Phương án thử nghiệm (đề xuất [PLAN], không phải benchmark đã đo)

| Trường hợp | Model thử | Điều cần biết |
|---|---|---|
| Baseline thực tế | `gemma3:12b` | Đo lại tốc độ/độ trễ trước khi đổi |
| Ưu tiên nhanh | `gemma3:4b` | Thường nhẹ hơn 12B, có thể đánh đổi độ phong phú của hội thoại |
| So chất lượng tiếng Việt | Model Qwen nhỏ, ví dụ `qwen3.5:4b` nếu có sẵn trong môi trường Ollama | Xác minh tag chính xác từ thư viện model tại thời điểm cài |
| CPU-only/VRAM ít | Model 2B–4B lượng tử hóa phù hợp | Giảm ngữ cảnh khi cần; đo kết quả thật |

Tránh tải model 20B+ chỉ vì máy có 64GB RAM nếu ưu tiên độ trễ. Giữ context vừa đủ cho NPC, thử 4096 token làm điểm xuất phát **nếu Brain hỗ trợ setting tương ứng**.

### Kiểm tra ngắn

```bash
ollama list
ollama ps
```

`ollama ps` cho biết model đang chạy và sử dụng CPU/GPU (phụ thuộc phiên bản Ollama). Nếu thiếu GPU hoặc model bị offload, tối ưu VRAM/quantization trước khi kết luận model quá dở.

## Kịch bản benchmark đề xuất

| Mục | Kỳ vọng / cách ghi |
|---|---|
| TTFT | Giây từ khi NPC được hỏi đến token đầu tiên |
| Tổng thời gian | Giây đến khi câu trả lời hoàn thành |
| Chất lượng tiếng Việt | Khả năng hiểu lệnh hỏi party, giao tiếp tự nhiên, không bịa trạng thái game |
| VRAM / RAM / CPU | Ghi peak trong 10–30 phút có server hoạt động |
| Concurrency | Test 1, 2, 4 yêu cầu đồng thời **chỉ khi hệ thống có tải thật** |
| Server game | FPS client, giật teleport, thời gian restart/save, DB lag |
| So sánh | Cùng prompt, cùng độ dài câu trả lời, cùng context, cùng GPU, cùng số bot |

**Khuyến nghị vận hành:** Giữ Ollama phục vụ trong máy hoặc mạng LAN tin cậy, không mở công khai cổng dịch vụ qua Internet nếu chưa có bảo vệ. Giới hạn concurrency để tránh VRAM tràn và tăng độ trễ hàng chờ.

## Danh sách thông tin cần thu thập
- [ ] CPU, GPU và **VRAM** máy host; model/quantization đang cài.
- [ ] Ảnh hoặc text `ollama ps` lúc Brain thực sự trả lời.
- [ ] Thời gian phản hồi trung vị/p95 khi server 100/200/500 bot.
- [ ] Số máy và số người kết nối cùng lúc; có chia sẻ Ollama giữa máy không?
- [ ] Log lỗi model tải lâu sau khởi động; xác nhận patch v0.4.2 giải quyết trên máy thực tế.
