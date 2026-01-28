@echo off
REM ============================================================================
REM Cryptography Suite - Quick Launch Script
REM ============================================================================

echo.
echo ========================================================================
echo   🔐 CRYPTOGRAPHY SUITE - LAUNCHER
echo ========================================================================
echo.

REM Check if Java is installed
where java >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ❌ ERROR: Java is not installed or not in PATH
    echo Please install Java JDK 8 or higher
    echo Download from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

echo ✓ Java detected
java -version
echo.

REM Compile if needed
echo 📝 Checking compilation...
if not exist "CryptoApp.class" (
    echo Compiling application...
    javac CryptoApp.java
    if %ERRORLEVEL% NEQ 0 (
        echo ❌ Compilation failed!
        pause
        exit /b 1
    )
    echo ✓ Compilation successful
) else (
    echo ✓ Application already compiled
)

echo.
echo 🚀 Launching Cryptography Suite...
echo.
echo ========================================================================
echo.

REM Run the application
java CryptoApp

echo.
echo Application closed.
pause
