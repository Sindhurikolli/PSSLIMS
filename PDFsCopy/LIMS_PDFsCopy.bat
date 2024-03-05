@echo off
set CUR_YYYY=%date:~10,4%
set CUR_MM=%date:~4,2%
set CUR_DD=%date:~7,2%
set CUR_HH=%time:~0,2%
if %CUR_HH% lss 10 (set CUR_HH=0%time:~1,1%)

set CUR_NN=%time:~3,2%
set CUR_SS=%time:~6,2%
set CUR_MS=%time:~9,2%

set SUBFILENAME=%CUR_DD%%CUR_MM%%CUR_YYYY%-%CUR_HH%%CUR_NN%%CUR_SS%
set LOGFILE=LogFiles\CC_PDFsCopy_%SUBFILENAME%.log

call :LOG > %LOGFILE%
:LOG

echo on
call xcopy "C:\Windows\System32\config\systemprofile\AppData\Local\Jenkins\.jenkins\LIMS\TestReport" "Z:\PDFCopyFolder\LIMS\CC" /h /i /c /k /e /r /y
cd C:\Windows\System32\config\systemprofile\AppData\Local\Jenkins\.jenkins\QMS3.2\TestReport
del *.pdf
exit

