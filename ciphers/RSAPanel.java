package ciphers;

import components.*;

import javax.crypto.Cipher;
import javax.swing.*;
import java.awt.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class RSAPanel extends BaseCipherPanel {
    KeyPair kp;

    public RSAPanel() {
        super("RSA Encryption");
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            kp = kpg.generateKeyPair();
        } catch (Exception e) {
        }
    }

    @Override
    protected void addControls(JPanel p) {
        JButton gen = new NeonButton("New Keys", new Color(100, 100, 100));
        JButton enc = new NeonButton("Encrypt", Theme.ACCENT_COLOR);
        JButton dec = new NeonButton("Decrypt", new Color(200, 50, 50));

        gen.addActionListener(e -> {
            try {
                KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
                kpg.initialize(1024);
                kp = kpg.generateKeyPair();
                outputArea.setText("New KeyPair Generated!");
            } catch (Exception ex) {
            }
        });

        enc.addActionListener(e -> measureAndRun(() -> {
            try {
                Cipher c = Cipher.getInstance("RSA");
                c.init(Cipher.ENCRYPT_MODE, kp.getPublic());

                byte[] inputBytes = getInputText().getBytes();
                int keySizeBits = 1024;
                int maxBlockSize = (keySizeBits / 8) - 11; // PKCS1 Padding overhead

                java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
                int offset = 0;
                while (offset < inputBytes.length) {
                    int len = Math.min(maxBlockSize, inputBytes.length - offset);
                    byte[] chunk = c.doFinal(inputBytes, offset, len);
                    out.write(chunk);
                    offset += len;
                }

                outputArea.setText(Base64.getEncoder().encodeToString(out.toByteArray()));
            } catch (Exception ex) {
                throw new RuntimeException("Enc Error: " + ex.getMessage());
            }
        }, true));

        dec.addActionListener(e -> measureAndRun(() -> {
            try {
                Cipher c = Cipher.getInstance("RSA");
                c.init(Cipher.DECRYPT_MODE, kp.getPrivate());

                byte[] encryptedBytes = Base64.getDecoder().decode(getInputText());
                int keySizeBits = 1024;
                int blockSize = keySizeBits / 8; // 128 bytes

                java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
                int offset = 0;
                while (offset < encryptedBytes.length) {
                    int len = Math.min(blockSize, encryptedBytes.length - offset);
                    byte[] chunk = c.doFinal(encryptedBytes, offset, len);
                    out.write(chunk);
                    offset += len;
                }

                outputArea.setText(new String(out.toByteArray()));
            } catch (Exception ex) {
                // ex.printStackTrace();
                throw new RuntimeException("Dec Error: " + ex.getMessage());
            }
        }, false));

        p.add(gen);
        p.add(enc);
        p.add(dec);
    }
}
