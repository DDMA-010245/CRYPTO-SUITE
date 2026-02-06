# 🔐 Cryptography Suite

A comprehensive Java Swing application featuring **200+ cryptographic algorithms** with a modern, cyberpunk-themed user interface.

![Version](https://img.shields.io/badge/version-2.2-blue)
![Java](https://img.shields.io/badge/java-8%2B-orange)
![License](https://img.shields.io/badge/license-MIT-green)

## 📸 Screenshots

![Application Interface](IMAGE-1.png)
*The Cryptography Suite Interface*

## ✨ Features

### 🎨 Modern UI Design
- **Cyberpunk-themed interface** with dynamic particle effects
- **Animated sidebar** for smooth navigation
- **Neon-styled components** for a premium aesthetic
- **Dark theme** optimized for visual comfort
- **Real-time search** to instantly filter algorithms

### 🔒 Cryptography Algorithms
The suite includes implementations of over **200 algorithms**:
- **Classical Ciphers:** Caesar, Vigenère, Playfair, Affine, Atbash
- **Modern Standards:** AES, DES, Blowfish, RC4, RSA
- **Hashing:** SHA-256, MD5, Whirlpool
- **Transposition:** Columnar, Rail Fence, Scytale

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Git (for cloning)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/DDMA-010245/CRYPTO-SUITE.git
   cd CRYPTO-SUITE
   ```

2. **Run the application (Mac/Linux)**
   We have included a helper script to setup the environment and launch the app:
   ```bash
   ./launch.sh
   ```

3. **Manual Compilation (Optional)**
   ```bash
   javac CryptoApp.java
   java CryptoApp
   ```

## 📁 Project Structure

```
CRYPTO-SUITE/
├── CryptoApp.java              # Main Application Entry Point
├── launch.sh                   # Auto-launcher script (recommended)
├── components/                 # UI Components & Themes
│   ├── CyberBackground.java    # Particle animation system
│   ├── Theme.java              # Color palettes and fonts
│   └── ...
├── ciphers/                    # Library of 200+ Cipher Implementations
│   ├── AESPanel.java
│   ├── CaesarCipherPanel.java
│   └── ...
└── README.md                   # Documentation
```

## 🎯 Usage

1. **Launch the Suite:** Use `./launch.sh` for the best experience.
2. **Select an Algorithm:** Browse the sidebar list or use the search bar.
3. **Input Data:** Enter text in the input field.
4. **Encrypt/Decrypt:** Press the corresponding neon buttons to process the text.
5. **Visualize:** View the transformation in the output area.

## 👨‍💻 Author

**"DARK-DEVIL"**
*DDMA-010245*
GMAIL: mr.ghost010245@gmail.com
