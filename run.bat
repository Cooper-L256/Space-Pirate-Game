@echo off
if "%~1"=="" (
    echo Usage: run.bat ClassName
    exit /b 1
)
java -cp ".;lib/*" --enable-native-access=ALL-UNNAMED %*