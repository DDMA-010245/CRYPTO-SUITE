package ciphers;

import components.*;
import javax.swing.*;
import java.awt.*;

public class TwofishPanel extends BaseCipherPanel {
    public TwofishPanel() {
        super("Twofish (Simulated)");
    }

    @Override
    protected void addControls(JPanel p) {
        JButton enc = new NeonButton("Encrypt", Theme.ACCENT_COLOR);
        JButton dec = new NeonButton("Decrypt", new Color(200, 50, 50));

        enc.addActionListener(e -> measureAndRun(() -> {
            try {
                // Using Blowfish as a robust fallback since Twofish is not in standard JDK
                javax.crypto.Cipher c = javax.crypto.Cipher.getInstance("Blowfish");
                javax.crypto.spec.SecretKeySpec funcKey = new javax.crypto.spec.SecretKeySpec(
                        "TwofishPlaceholder".getBytes(), "Blowfish");
                c.init(javax.crypto.Cipher.ENCRYPT_MODE, funcKey);
                byte[] encVal = c.doFinal(getInputText().getBytes());
                outputArea.setText(java.util.Base64.getEncoder().encodeToString(encVal));
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        }, true));

        dec.addActionListener(e -> measureAndRun(() -> {
            try {
                javax.crypto.Cipher c = javax.crypto.Cipher.getInstance("Blowfish");
                javax.crypto.spec.SecretKeySpec funcKey = new javax.crypto.spec.SecretKeySpec(
                        "TwofishPlaceholder".getBytes(), "Blowfish");
                c.init(javax.crypto.Cipher.DECRYPT_MODE, funcKey);
                byte[] decVal = c.doFinal(java.util.Base64.getDecoder().decode(getInputText()));
                outputArea.setText(new String(decVal));
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        }, false));

        p.add(enc);
        p.add(dec);
    }
}
