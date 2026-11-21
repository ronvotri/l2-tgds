# Bản chia sẻ 1.0.33 / Living Worlds v0.4.10

[OBSERVED 2026-11-21] Đã cập nhật bản gốc và demo lên Living Worlds v0.4.10; bản chia sẻ tiếp tục cộng dồn từ baseline 1.0. Có 1.0/1.0.1/1.0.2/1.0.3/1.0.31/1.0.32 chỉ cài patch 1.0.33. Không cần cài từng patch.

## Nội dung upstream
[UPSTREAM] 0.4.10 ổn định đội Living bot, bot thiếu shots/vũ khí về tiếp tế; sửa buff Raid Curse và thông báo thành viên ngoài tầm. Quà người chơi tặng được giữ, bot nói lý do không dùng; thuộc tính enchant/augmentation được bảo toàn khi tháo đồ/lưu clan warehouse. Bot ưu tiên tiết kiệm mua vũ khí đúng hạng. Thành giảm quét xung quanh gây tải CPU. Thêm FakePlayerRecruitTownOnly, cấu hình ẩn nút Community Board và DaysBeforeAcceptNewMemberWhenDismissed. Olympiad roster mặc định 20. Launcher chia danh sách module đi kèm và module riêng.

Nguồn chính thức: [release feed](https://updates.l2livingworlds.space/releases.json), [patch 0.4.10](https://updates.l2livingworlds.space/releases/v0.4.10-patch/L2J-Offline-Patch.zip). Feed được kiểm tra 2026-11-21; published_at của upstream là 2026-11-17T20:07:09Z. Đây là Living Worlds, không phải Interlude+ alpha.

## Phần Việt hóa và tương thích
- Rebuild từ core 0.4.10: giữ action guide/pet, Việt hóa bot chat, locale fallback; tái áp có kiểm tra cấu trúc các hook nhặt hộ và HTML missing-language fallback. Không chép core cũ lên bản mới.
- Dịch phản hồi quà tặng, lý do không dùng trang bị, trạng thái raid và buff Raid Curse. Giữ tên vật phẩm/kỹ năng tiếng Anh.
- Gộp UI upstream mới vào UI đang tùy chỉnh, giữ công tắc nhặt hộ; dịch trang Community Board và tuyển Phantom trong thành.
- Ba chiều config theo factory từng bản: tùy chỉnh thật được giữ, mặc định chưa chỉnh nhận giá trị mới. Bổ sung defaults trước đây thiếu cho PhantomOlympiad/CommunityBoard. Kiểm tra riêng roster mặc định 40 → 20 và tùy chỉnh 33 vẫn 33.
- Giữ công cụ GM/tạo tài khoản và hotfix cửa sổ hiển thị, Xenos, slash aliases, taming, wiki/news/personality hooks và runtime/model. Không tự bật GM hoặc reset tiến trình.
- Launcher gốc được nâng exe 0.4.10; bản share giữ launcher portable riêng để không trỏ nhầm database/model.

## Kiểm chứng thực tế
[OBSERVED]
- 928 scripts gồm taming biên dịch; core JVM verify; lệnh Việt tuyển đội/nhường đường/buff mana/chốt giao dịch qua kiểm tra.
- Main và demo khởi động đủ 6 module, không lỗi startup, đăng ký login server thành công; đã lưu và dừng đúng cách sau QA.
- 23 native inventory cases của nhặt hộ trên core 0.4.10 qua (Phantom/Living, các luật chia đồ/Spoil, Adena, không có người nhận, tắt tính năng về native). Script QA tạm đã xóa.
- Native HtmCache với Player ngôn ngữ Việt: cold fallback, ưu tiên bản dịch, Clan/Alliance, thiếu tệp và chặn traversal qua.
- UI headless: mục mới được dịch, loot option giữ, không JS lỗi. Native core vẫn giữ cập nhật upstream.
- Inventory nhân vật gốc không đổi; 441 tệp database/save/memory/address demo byte-identical qua bước cài (trước chạy server QA). SQL/file backup trước thay đổi có đối chiếu; baseline full1.0 và ZIP cũ giữ nguyên.
- Installer từ tệp thật 1.0–1.0.32, cài lặp, giữ config/save/giới tính, drift/downgrade và rollback qua.
- AI local HTTP đáp tiếng Việt; câu hỏi wiki Prophet/Empower có dữ liệu trả đúng Shillien Elder. Bộ AI monolithic compatibility entry vẫn được giữ, chưa migrate hoàn toàn brain_service.

[UNKNOWN / NOT RUN] Chưa chơi thử client, raid dài hoặc soak nhiều giờ; các hiệu năng/giữ gear mới mô tả theo upstream, không coi startup là kiểm chứng hết gameplay. AI có thể chưa trả lời được một số câu wiki không có dữ liệu cụ thể (thử hỏi Dark Wizard chuyển chức cấp mấy đã trả lời chưa biết); không tuyên bố wiki toàn diện.

## Cài và rollback
Giải nén patch cạnh CHOI-NGAY.bat + Du-lieu, chạy CAP-NHAT-1.0.33.bat, chờ thành công rồi mở game. Installer dùng Stop.ps1 lưu/dừng trước khi sao lưu tệp; kiểm tra hash và không downgrade. Giữ account/database/AI memories/config riêng. Backup main trước 0.4.10 và backup mỗi lần demo cài được giữ cục bộ. Chỉ rollback file kèm backup tương ứng, không reset database đang có tiến trình.

ZIP/binary/save/model không đưa GitHub; [metadata/checksum](../releases/1.0.33.json), [thông báo](../announcements/1.0.33.txt). Repo chứa docs, metadata và công cụ bảo trì tự viết.
