@echo off
if "%~1"=="" (
    echo Usage: build.bat SourceFile.java
    exit /b 1
)
javac -cp ".;lib/*" %*