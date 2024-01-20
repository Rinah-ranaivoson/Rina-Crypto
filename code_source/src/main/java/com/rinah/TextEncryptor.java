package com.rinah;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TextEncryptor {

    public TextEncryptor(JTextPane textPane) {
        String encryptionKey = JOptionPane.showInputDialog(null, "Entrez la clé de chiffrement (max 16 caractères) :");
        if (encryptionKey != null && encryptionKey.length() <= 16 && !encryptionKey.isEmpty()) {
            try {
                String originalText = textPane.getText();
                String encryptedText = encryptText(originalText, encryptionKey);
                textPane.setText(encryptedText);

                JOptionPane.showMessageDialog(null, "Texte crypté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors du cryptage du texte", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez entrer une clé de chiffrement valide (max 16 caractères)", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String encryptText(String plainText, String key) throws Exception {
        SecretKey secretKey = generateKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    private SecretKey generateKey(String key) throws Exception {
        byte[] keyBytes = new byte[16];
        byte[] originalKeyBytes = key.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(originalKeyBytes, 0, keyBytes, 0, Math.min(originalKeyBytes.length, keyBytes.length));
        return new SecretKeySpec(keyBytes, "AES");
    }
}
