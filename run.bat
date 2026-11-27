@echo off
rem Fallback launcher (no NetBeans needed): compiles and runs. Requires JDK 11+ on PATH.
cd /d "%~dp0"
if not exist build\classes mkdir build\classes
dir /s /b src\*.java > build\sources.txt
javac -encoding UTF-8 --release 11 -d build\classes @build\sources.txt || exit /b 1
xcopy /s /y /q src\hiking\db\*.sql build\classes\hiking\db\ >nul
xcopy /s /y /q src\hiking\db\*.csv build\classes\hiking\db\ >nul
java -cp build\classes hiking.Main
