package ciphers;

import components.*;
import javax.swing.*;
import java.awt.*;

public class FourSquareCipherPanel extends BaseCipherPanel {
    public FourSquareCipherPanel() {
        super("Four-Square Cipher (Simulated)");
    }

    @Override
    protected void addControls(JPanel p) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setOpaque(false);

        // Keys
        p.add(new JLabel("<html><font color='white'>Key 1:</font></html>"));
        JTextField k1 = createTextField("EXAMPLE");
        p.add(k1);

        p.add(new JLabel("<html><font color='white'>Key 2:</font></html>"));
        JTextField k2 = createTextField("KEYWORD");
        p.add(k2);

        JButton enc = new NeonButton("Encrypt", Theme.ACCENT_COLOR);
        JButton dec = new NeonButton("Decrypt", new Color(200, 50, 50));

        enc.addActionListener(e -> measureAndRun(() -> process(true, k1.getText(), k2.getText()), true));
        dec.addActionListener(e -> measureAndRun(() -> process(false, k1.getText(), k2.getText()), false));

        p.add(enc);
        p.add(dec);
    }

    private void process(boolean encrypt, String k1, String k2) {
        try {
            String text = inputArea.getText().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
            if (text.length() % 2 != 0)
                text += "X";

            char[][] a1 = generateSquare(""), a2 = generateSquare(k1), a3 = generateSquare(k2), a4 = generateSquare("");
            // 4-Square: TopLeft(Standard), TopRight(Key1), BottomLeft(Key2),
            // BottomRight(Standard)
            // Actually usually: TL(Key1), TR(Std), BL(Std), BR(Key2) or variations.
            // Standard: TL(a1), TR(a2), BL(a3), BR(a4)
            // E: Pair(p1, p2). p1 in TL, p2 in BR.
            // C1 = TR row(p1), col(p2). C2 = BL row(p2), col(p1).
            // Let's use:
            // Square 1: Std (TL)
            // Square 2: K1 (TR)
            // Square 3: K2 (BL)
            // Square 4: Std (BR)

            StringBuilder res = new StringBuilder();
            for (int i = 0; i < text.length(); i += 2) {
                char c1 = text.charAt(i);
                char c2 = text.charAt(i + 1);

                int[] p1 = findPos(encrypt ? a1 : a2, c1); // If decrypt, we reverse logic?
                // Decryption is finding Plain from Cipher.
                // E: c1 from TR(r1, c2), c2 from BL(r2, c1).
                // Let's stick to standard.
                // Encrypt: m1, m2. Loc m1 in SQ1 (r1, c1). Loc m2 in SQ4 (r2, c2).
                // c1 = SQ2(r1, c2). c2 = SQ3(r2, c1).
                // Decrypt: c1, c2. Loc c1 in SQ2 (r1, c2). Loc c2 in SQ3 (r2, c1).
                // m1 = SQ1(r1, c2) ??? No.
                // Row matches.
                // r1 = Row of c1 in SQ2. c2 = Col of c1 in SQ2? No.

                if (encrypt) {
                    int[] pos1 = findPos(a1, c1);
                    int[] pos2 = findPos(a4, c2);
                    res.append(a2[pos1[0]][pos2[1]]);
                    res.append(a3[pos2[0]][pos1[1]]);
                } else {
                    int[] pos1 = findPos(a2, c1);
                    int[] pos2 = findPos(a3, c2);
                    res.append(a1[pos1[0]][pos2[1]]);
                    res.append(a4[pos2[0]][pos1[1]]);
                }
            }
            outputArea.setText(res.toString());

        } catch (Exception e) {
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    private char[][] generateSquare(String key) {
        String k = (key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder sb = new StringBuilder();
        for (char c : k.toCharArray())
            if (sb.indexOf("" + c) == -1)
                sb.append(c);
        char[][] sq = new char[5][5];
        for (int i = 0; i < 25; i++)
            sq[i / 5][i % 5] = sb.charAt(i);
        return sq;
    }

    private int[] findPos(char[][] sq, char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (sq[i][j] == c)
                    return new int[] { i, j };
        return new int[] { 0, 0 };
    }
}
