if(-not ('L2VisibleWindow' -as [type])){
 Add-Type @"
using System;using System.Runtime.InteropServices;
public static class L2VisibleWindow {
 [DllImport("user32.dll")] public static extern bool ShowWindowAsync(IntPtr h,int cmd);
 [DllImport("user32.dll")] public static extern bool SetForegroundWindow(IntPtr h);
 [DllImport("user32.dll")] public static extern uint GetWindowThreadProcessId(IntPtr h,out uint pid);
 [DllImport("user32.dll")] public static extern bool IsWindow(IntPtr h);
}
"@
}
function Enable-L2VisibleWindow($Window){
 # STARTUPINFO's Hidden flag can hide the first WinForms window too.
 # Show it again after the first native ShowWindow call has been consumed.
 $Window.ShowInTaskbar=$true
 $handle=$Window.Handle
 $timer=New-Object Windows.Forms.Timer;$timer.Interval=200
 $timer.Add_Tick({
  [void][L2VisibleWindow]::ShowWindowAsync($handle,1)
  [void][L2VisibleWindow]::SetForegroundWindow($handle)
  $timer.Stop()
 }.GetNewClosure())
 $Window.Add_FormClosed({$timer.Stop();$timer.Dispose()}.GetNewClosure())
 $timer.Start()
}
function Show-L2UniqueWindow($Window,[string]$Root,[string]$Kind){
 $hash=[Security.Cryptography.SHA256]::Create()
 try{$key=[BitConverter]::ToString($hash.ComputeHash([Text.Encoding]::UTF8.GetBytes(($Root.ToLowerInvariant()+'|'+$Kind)))).Replace('-','')}finally{$hash.Dispose()}
 $mutex=New-Object Threading.Mutex($false,('Local\L2UI_'+$key))
 $owned=$false;$record=Join-Path $Root ('launcher\.ui-'+$Kind+'.json')
 try{
  try{$owned=$mutex.WaitOne(0)}catch [Threading.AbandonedMutexException]{$owned=$true}
  if(!$owned){
   if(Test-Path -LiteralPath $record){
    $r=Get-Content -LiteralPath $record -Raw -Encoding UTF8 | ConvertFrom-Json
    $p=Get-Process -Id $r.pid -ErrorAction SilentlyContinue
    $h=[IntPtr][long]$r.handle;[uint32]$owner=0
    [void][L2VisibleWindow]::GetWindowThreadProcessId($h,[ref]$owner)
    if($p -and $owner -eq $p.Id -and "$($p.StartTime.ToUniversalTime().Ticks)" -eq "$($r.startTicks)" -and [L2VisibleWindow]::IsWindow($h)){
     [void][L2VisibleWindow]::ShowWindowAsync($h,9);[void][L2VisibleWindow]::SetForegroundWindow($h)
    }
   }
   $Window.Dispose();return
  }
  $p=Get-Process -Id $PID
  @{pid=$PID;startTicks="$($p.StartTime.ToUniversalTime().Ticks)";handle="$($Window.Handle.ToInt64())"} | ConvertTo-Json | Set-Content -LiteralPath $record -Encoding UTF8
  Enable-L2VisibleWindow $Window
  [void]$Window.ShowDialog()
 }finally{
  if($owned){if(Test-Path -LiteralPath $record){Remove-Item -LiteralPath $record -Force};$mutex.ReleaseMutex()}
  $mutex.Dispose()
 }
}
function Start-L2UiScript([string]$Root,[string]$Name){
 try{
  $file=Join-Path $Root ('launcher\'+$Name+'.ps1')
  if(!(Test-Path -LiteralPath $file)){throw ('Không tìm thấy '+$file)}
  $logs=Join-Path $Root 'logs';[void][IO.Directory]::CreateDirectory($logs)
  $stamp=Get-Date -Format 'yyyyMMdd-HHmmss-ffff'
  $err=Join-Path $logs ('ui-'+$Name+'-'+$stamp+'-error.log');$out=Join-Path $logs ('ui-'+$Name+'-'+$stamp+'.log')
  $p=Start-Process powershell.exe -WindowStyle Hidden -ArgumentList ('-NoProfile -ExecutionPolicy Bypass -STA -File "'+$file+'"') -RedirectStandardError $err -RedirectStandardOutput $out -PassThru
  $monitor=New-Object Windows.Forms.Timer;$monitor.Interval=500;$deadline=[DateTime]::UtcNow.AddSeconds(10)
  $monitor.Add_Tick({
   $p.Refresh()
   if($p.HasExited){
    if($p.ExitCode -ne 0){$detail=Get-Content -LiteralPath $err -Raw -ErrorAction SilentlyContinue;[void][Windows.Forms.MessageBox]::Show(('Không mở được '+$Name+'. Xem '+$err+"`n"+$detail),'L2 Việt hóa')}
    $monitor.Stop();$monitor.Dispose()
   }elseif([DateTime]::UtcNow -ge $deadline){$monitor.Stop();$monitor.Dispose()}
  }.GetNewClosure())
  $monitor.Start()
 }catch{[void][Windows.Forms.MessageBox]::Show($_.Exception.Message,'Không mở được cửa sổ')}
}
