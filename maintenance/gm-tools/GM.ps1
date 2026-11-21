$ErrorActionPreference='Stop'
$root=Split-Path -Parent $PSScriptRoot
. (Join-Path $PSScriptRoot 'GMSettings.ps1')
. (Join-Path $PSScriptRoot 'UiWindow.ps1')
Add-Type -AssemblyName System.Windows.Forms
Add-Type -AssemblyName System.Drawing
$form=New-Object Windows.Forms.Form
$form.Text='L2 Việt hóa — Quyền GM và tạo vật phẩm';$form.Size=New-Object Drawing.Size(650,410)
$form.StartPosition='CenterScreen';$form.FormBorderStyle='FixedDialog';$form.MaximizeBox=$false
$form.BackColor=[Drawing.Color]::FromArgb(23,23,26);$form.ForeColor=[Drawing.Color]::FromArgb(238,225,200)
$form.Font=New-Object Drawing.Font('Segoe UI',10)
function Label([string]$text,[int]$y,[int]$height){$l=New-Object Windows.Forms.Label;$l.Text=$text;$l.Location=New-Object Drawing.Point(20,$y);$l.Size=New-Object Drawing.Size(600,$height);$form.Controls.Add($l);return $l}
$heading=Label 'QUYỀN GM · THẾ GIỚI OFFLINE CỦA BẠN' 18 28;$heading.ForeColor=[Drawing.Color]::FromArgb(224,185,107)
$status=Label '' 57 45
$note=Label "Bật: mọi nhân vật trên server riêng đều dùng được lệnh quản trị.`nKhông cần tạo lại nhân vật. Sau khi đổi, lưu/tắt server rồi mở lại bằng CHOI-NGAY.bat." 105 65
$commands=Label "//admin  —  bảng quản trị`n//itemcreate  —  bảng tạo vật phẩm`n//create_item 57 1000000  —  thêm 1 triệu Adena" 180 72
function Refresh-GM {$state=Read-GMSetting $root;$status.Text='Cấu hình quyền chung: '+$(if($state.Enabled){'ĐANG BẬT'}else{'ĐANG TẮT'})+"`nCó hiệu lực sau khi khởi động lại server."}
function Change-GM([bool]$enable){
 try{$result=Set-GMSetting $root $enable;Refresh-GM
  $text=if($result.Changed){"Đã lưu và sao lưu cấu hình cũ.`nDùng DUNG-VA-LUU.bat rồi CHOI-NGAY.bat để áp dụng."}else{'Thiết lập đã ở trạng thái này. Không cần sửa lại.'}
  if(!$enable){$text+="`nTắt quyền chung không tự thu hồi quyền đã cấp/lưu riêng cho nhân vật."}
  [void][Windows.Forms.MessageBox]::Show($text,'Quyền GM')
 }catch{[void][Windows.Forms.MessageBox]::Show($_.Exception.Message,'Chưa thay đổi được')}
}
function Button([string]$text,[int]$x,[scriptblock]$action){$b=New-Object Windows.Forms.Button;$b.Text=$text;$b.Location=New-Object Drawing.Point($x,275);$b.Size=New-Object Drawing.Size(190,38);$b.FlatStyle='Flat';$b.Add_Click($action);$form.Controls.Add($b)}
Button 'Bật GM cho server này' 20 {Change-GM $true}
Button 'Tắt quyền GM chung' 220 {Change-GM $false}
Button 'Hướng dẫn / Tạo tài khoản' 420 {Start-Process -FilePath (Join-Path $root 'tools\HUONG-DAN-GM-VA-TAI-KHOAN.html') | Out-Null}
$footer=Label 'Bảng này chỉ đổi quyền chung; không tạo vật phẩm hoặc sửa nhân vật tự động.' 325 40
try{Refresh-GM;Show-L2UniqueWindow $form $root 'GM'}catch{[void][Windows.Forms.MessageBox]::Show($_.Exception.Message,'Không mở được bảng GM')}
