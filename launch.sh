#!/bin/bash
# ============================================================================
# Cryptography Suite - Quick Launch Script (Linux/Mac)
# ============================================================================

echo ""
echo "========================================================================"
echo "  🔐 CRYPTOGRAPHY SUITE - LAUNCHER"
echo "========================================================================"
echo ""

# Set Java Path for Homebrew OpenJDK
export PATH="/opt/homebrew/opt/openjdk/bin:$PATH"

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ ERROR: Java is not installed or not in PATH"
    echo "Please install Java JDK 8 or higher"
    exit 1
fi

echo "✓ Java detected"
java -version
echo ""

# Compile if needed
echo "📝 Checking compilation..."
if [ ! -f "CryptoApp.class" ]; then
    echo "Compiling application..."
    javac CryptoApp.java
    if [ $? -ne 0 ]; then
        echo "❌ Compilation failed!"
        exit 1
    fi
    echo "✓ Compilation successful"
else
    echo "✓ Application already compiled"
fi

echo ""
echo "🚀 Launching Cryptography Suite..."
echo ""
echo "========================================================================"
echo ""

# Run the application
java CryptoApp

echo ""
echo "Application closed."
