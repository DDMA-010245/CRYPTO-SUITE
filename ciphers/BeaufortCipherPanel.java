package ciphers;

import components.*;

import javax.swing.*;
import java.awt.Color;

public class BeaufortCipherPanel extends BaseCipherPanel {
    JTextField keyField;

    public BeaufortCipherPanel() {
        super("Beaufort Cipher");
    }

    @Override
    protected void addControls(JPanel p) {
        p.add(new JLabel("<html><font color='white'>Key:</font></html>"));
        keyField = createTextField("KEY");
        p.add(keyField);
        JButton enc = new NeonButton("Encrypt", Theme.ACCENT_COLOR);
        JButton dec = new NeonButton("Decrypt", new Color(200, 50, 50));
        enc.addActionListener(e -> measureAndRun(this::process, true));
        dec.addActionListener(e -> measureAndRun(this::process, false));
        p.add(enc);
        p.add(dec);
    }

    @Override
    protected void resetFields() {
        keyField.setText("KEY");
    }

    private void process() {
        try {
            String text = getInputText().toUpperCase();
            String key = keyField.getText().toUpperCase().replaceAll("[^A-Z]", "");
            StringBuilder sb = new StringBuilder();
            for (int i = 0, j = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (c < 'A' || c > 'Z') {
                    sb.append(c);
                    continue;
                }
                int m = c - 'A';
                int k = key.charAt(j % key.length()) - 'A';
                int val = (k - m + 26) % 26;
                sb.append((char) (val + 'A'));
                j++;
            }
            outputArea.setText(sb.toString());
        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }
}
