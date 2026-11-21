function Read-GMSetting([string]$Root) {
 $file=Join-Path $Root 'game\config\General.ini'
 if(!(Test-Path -LiteralPath $file -PathType Leaf)){throw 'Không tìm thấy game\config\General.ini của bộ game này.'}
 $bytes=[IO.File]::ReadAllBytes($file)
 $text=[Text.Encoding]::UTF8.GetString($bytes)
 $matches=[regex]::Matches($text,'(?m)^[ \t]*EverybodyHasAdminRights[ \t]*=[ \t]*(True|False)[ \t]*(?=\r?$)')
 if($matches.Count -ne 1){throw 'Cần đúng một dòng EverybodyHasAdminRights = True hoặc False. Đã giữ nguyên tệp.'}
 [pscustomobject]@{File=$file;Bytes=$bytes;Text=$text;Enabled=($matches[0].Groups[1].Value -ieq 'True')}
}
function Set-GMSetting([string]$Root,[bool]$Enabled) {
 $state=Read-GMSetting $Root
 if($state.Enabled -eq $Enabled){return [pscustomobject]@{Changed=$false;Enabled=$Enabled;Backup=$null}}
 $value=if($Enabled){'True'}else{'False'}
 $text=[regex]::Replace($state.Text,'(?m)^([ \t]*EverybodyHasAdminRights[ \t]*=[ \t]*)(True|False)([ \t]*)(?=\r?$)',[Text.RegularExpressions.MatchEvaluator]{param($m) $m.Groups[1].Value+$value+$m.Groups[3].Value})
 $folder=Join-Path $Root ('config-backups\GM-'+(Get-Date -Format 'yyyyMMdd-HHmmss-ffff'))
 [void][IO.Directory]::CreateDirectory($folder)
 $backup=Join-Path $folder 'General.ini';[IO.File]::WriteAllBytes($backup,$state.Bytes)
 $latest=[IO.File]::ReadAllBytes($state.File)
 if([Convert]::ToBase64String($latest) -ne [Convert]::ToBase64String($state.Bytes)){throw 'Cấu hình vừa được chương trình khác thay đổi. Hãy mở lại bảng GM; đã giữ nguyên tệp.'}
 # UTF8.GetString retains an existing BOM as a character; GetBytes preserves it.
 $next=[Text.Encoding]::UTF8.GetBytes($text);$tmp=$state.File+'.gm-'+[Guid]::NewGuid().ToString('N')+'.tmp'
 try{
  [IO.File]::WriteAllBytes($tmp,$next)
  [IO.File]::Replace($tmp,$state.File,[NullString]::Value)
 }finally{if(Test-Path -LiteralPath $tmp){Remove-Item -LiteralPath $tmp -Force}}
 [pscustomobject]@{Changed=$true;Enabled=$Enabled;Backup=$backup}
}

