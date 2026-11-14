# L2 Việt hóa 1.0.1 — Living Worlds v0.4.6
Ngày: **2026-11-14**. Patch cộng dồn từ bản đầy đủ **1.0**, không cần tải lại game/AI.

## Thay đổi upstream [UPSTREAM]
Nguồn: [feed phát hành](https://updates.l2livingworlds.space/releases.json), [gói patch v0.4.6](https://updates.l2livingworlds.space/releases/v0.4.6-patch/L2J-Offline-Patch.zip). Không phải Interlude+ alpha.
- Đội trưởng gọi tối đa **4 đội phụ**, tổng cộng 5 đội trong kênh chỉ huy. Đội phụ chỉ nhặt adena.
- Pháp sư/cung thủ/healer tản quanh raid boss, tránh phía trước và một số AOE/disable.
- Bot gần thành đi bộ về; tăng thợ săn cùng đuổi recipe; chia nghề Dwarf đều hơn; giữ potion/tên/shot; mua lại tên; không nghỉ ngay khi mới sinh; shop không đóng ngay khi AFK.
- Nhà phát triển công bố sửa rò bộ nhớ gây lag sau nhiều giờ. **Chưa có soak test nhiều giờ cho bản Việt hóa này.**

## Lệnh đội phụ
Gõ trong chat đội khi bạn là đội trưởng:
| Lệnh | Chức năng |
|---|---|
| `raid party` | Gọi đội phụ Phantom |
| `raid party living` | Gọi đội phụ Living Population |
| `raid parties` | Xem các đội phụ |
| `dismiss party 2` | Giải tán đội phụ số 2 |
| `dismiss raid parties` | Giải tán tất cả đội phụ |

`FakePlayerAlliedRetailChannel=False`: tự tạo kênh chỉ huy. True: áp điều kiện gốc (thủ lĩnh bang cấp 5, Strategy Guide, hoặc Baron có Clan Imperium). `RecipeGoalMinChasers=3` là key mới.

## Phần Việt hóa và đóng gói [OBSERVED]
- Dịch 3 chuỗi giao diện cấu hình raid mới và các thông báo đội phụ; giữ các map dịch core trước đó.
- Tạo core từ upstream v0.4.6 rồi áp lại action menu, bot chat theo kênh, xử lý locale và mod đã có; không dùng JAR cũ thay core mới.
- Bản chia sẻ giữ 3 cầu nối AI ở cổng riêng; giữ config tự mở, sửa vòng đời launcher và chat riêng gọi ông/bà theo lựa chọn (không dùng nguyên cụm ghép khi nói).
- Changelog nói thay FakePlayers.ini, nhưng **gói patch thực tế không chứa file đó**. Đã thêm switch mới vào cấu hình Việt hóa; Feature.ini/General.ini/Rates.ini trong ZIP không đổi byte so với gói v0.4.5. Luôn đối chiếu ZIP thật.
- Installer gồm 23 tệp payload, gộp giá trị INI đang dùng, kiểm tra hash, lưu/dừng đúng cách, backup tệp trước khi thay, rollback khi lỗi, từ chối code không khớp hoặc hạ cấp.
- Bản 1.0.1 giữ nguyên checksum và sẽ được đưa vào patch cộng dồn kế tiếp. [Quy ước](SHARE-RELEASES.md).

## Kết quả kiểm tra ngày 2026-11-14 [OBSERVED]
| Kiểm tra | Kết quả |
|---|---|
| Biên dịch scripts, gồm taming | PASS — 927 Java scripts |
| JVM kiểm tra core/hook và lệnh cũ | PASS |
| Server chính và demo | PASS — khởi động, đủ 6 modules, không lỗi startup |
| AI demo | PASS — runtime và model báo sẵn sàng |
| Config Editor | PASS — schema/default/giá trị tuần tự hóa không đổi; mục raid mới hiển thị Việt |
| Bảo vệ bản dịch sau cài | PASS — 387 tệp bản chính, 392 tệp bản demo |
| Inventory bản chính | PASS — snapshot trước/sau giống byte |
| Dữ liệu demo ngay sau cài patch | PASS — 441 tệp dữ liệu không đổi bởi bước cài |
| Installer | PASS — giữ config/save/giới tính, thêm default, cài lặp, bắt code drift, rollback lỗi ghi |
| ZIP patch | PASS — CRC toàn bộ và hash payload |
| Hiển thị client, đánh raid thực tế | NOT RUN |
| Soak memory/GC nhiều giờ | NOT RUN |

Các kết quả trên dựa trên kiểm thử cục bộ; repo chỉ lưu bản tóm tắt đã lọc, không lưu log cá nhân/SQL. Không tự đánh dấu các checklist gameplay cũ là PASS.

## Gói phát hành
- Tên: `L2-VietHoa-Patch-1.0-len-1.0.1.zip`.
- Dung lượng: **7.757.998 byte** (~7,8 MB).
- SHA256: `314400943a5d3dbcb856dfcdd19a2cb0b3e40ba76f13e6bf1b84443da4c50689`.
- ZIP đầy đủ 1.0: `L2-VietHoa-TGDS.zip`; SHA256 `5d4618000ea00cc59d8ddd779a5905536e0e72b33805c576a624f5a6e624c2e5`, giữ nguyên.
- File ZIP chứa tài nguyên game được chủ dự án chia sẻ riêng; không có trong GitHub này.
- Giải nén cạnh CHOI-NGAY.bat, chạy CAP-NHAT-1.0.1.bat rồi mở game. Giữ tài khoản, nhân vật, clan, thú, bot, cấu hình và model hiện tại.
