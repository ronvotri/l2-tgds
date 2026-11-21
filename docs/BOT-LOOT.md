# Mới từ 1.0.3: công tắc nhặt hộ

Bot đồng hành → Tổ đội của bạn → **Bot nhặt đồ, người chơi nhận** (`FakePlayerPartyLootToPlayers`). Mặc định False. Bật, lưu rồi `//reload config` hoặc khởi động lại server. Muốn bot tự nhặt cần bật thêm `FakePlayerPartyPickup`.

Áp dụng cho đội có người chơi và Phantom/Living bot. Random/Theo lượt chia giữa người chơi đủ điều kiện. Ai nhặt nấy giữ: khi bot nhặt, ưu tiên đội trưởng đủ điều kiện. Không có người nhận hợp lệ do khoảng cách/túi đồ: giữ/thả lại đất. Adena không bị mất khi không có người nhận. Không thu túi đồ cũ, không đổi EXP, không đổi kinh tế đội chỉ gồm bot. Nhân vật autopilot vẫn tính là người chơi. Tắt công tắc trả về luật native.

[Báo cáo kiểm thử](RELEASE-SHARE-1.0.3.md). Phần dưới mô tả luật cũ khi công tắc tắt.

---

# Bot nhặt đồ có chuyển hết về người chơi không?

Đã kiểm tra mã Party và PhantomPartyCommands của **Living Worlds v0.4.9**, đối chiếu với v0.4.7. Đây là kiểm tra mã, chưa có thử giao dịch/nhặt đồ trực tiếp trong client.

## Hai loại bot khác nhau
| Loại | Chia đồ |
|---|---|
| Phantom tuyển dụng / support buddy | `FakePlayerPartyLootShare=False` loại chúng khỏi Random/By turn và chia adena; người chơi thật đủ điều kiện nhận phần |
| Living Population | Vẫn đủ điều kiện nhận phần như thành viên thật; False ở trên không loại Living bot khỏi chia đồ |
| Autopilot / nhân vật người chơi tự điều khiển | Vẫn được chia phần; không bị coi như Phantom miễn phần |

Random hay By turn chỉ đổi cách phân phối giữa người **đủ điều kiện**, không có nghĩa mọi món đều về chủ đội. Nếu có nhiều người chơi thật hoặc Living bot, đồ có thể về thành viên khác. Còn phụ thuộc khoảng cách, tải trọng và điều kiện nhận vật phẩm.

## Cho Phantom nhặt hộ
Trong `game/config/Custom/FakePlayers.ini`:
```ini
FakePlayerPartyPickup = True
FakePlayerPartyLootShare = False
```
Đặt chia đồ Random hoặc By turn. Với một người chơi thật đi cùng Phantom, đồ hợp lệ sẽ phân về người chơi. Finders keepers có thể để đồ trong túi Phantom.

## Lấy đồ Phantom đang giữ
Gõ `party return loot` trong chat đội để yêu cầu các Phantom liên quan lần lượt giao dịch trả phần loot đã nhặt giữ trong phiên. Với một Phantom cụ thể có lệnh `return loot`. Chấp nhận giao dịch và xác nhận để nhận đồ.

Lệnh dựa trên bộ theo dõi loot của Phantom, không phải lệnh thu toàn bộ túi của Living Population, không bao gồm trang bị vốn có của bot và không bảo đảm phục hồi loot từ mọi phiên cũ.

## Câu trả lời ngắn để hỗ trợ người chơi
“Có, với Phantom tuyển dụng: bật nhặt hộ, tắt chia loot cho Phantom rồi để Random/By turn. Đồ Phantom đang giữ thì chat đội `party return loot` để nó giao dịch trả. Riêng Living Population vẫn được chia phần như người thật, nên đổi Random/By turn không làm mọi món về mình.”

Patch 1.0.3 giữ luật loot upstream, không tự thay đổi kinh tế Living bot hay ép mọi vật phẩm về người chơi.
