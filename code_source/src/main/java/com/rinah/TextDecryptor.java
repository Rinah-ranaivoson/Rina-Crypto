package com.rinah;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class TextDecryptor {

    public TextDecryptor(JTextPane textPane) {
        String encryptionKey = JOptionPane.showInputDialog(null, "Entrez la clé de chiffrement (max 16 caractères) :");
        if (encryptionKey != null && encryptionKey.length() <= 16 && !encryptionKey.isEmpty()) {
            try {
                String encryptedText = textPane.getText().trim(); // Supprimez les espaces en début/fin
                String decryptedText = decryptText(encryptedText, encryptionKey);
                textPane.setText(decryptedText);

                JOptionPane.showMessageDialog(null, "Texte décrypté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors du décryptage du texte", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Veuillez entrer une clé de chiffrement valide (max 16 caractères)", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String decryptText(String encryptedText, String key) throws Exception {
        SecretKey secretKey = generateKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private SecretKey generateKey(String key) throws Exception {
        byte[] keyBytes = new byte[16];
        byte[] originalKeyBytes = key.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(originalKeyBytes, 0, keyBytes, 0, Math.min(originalKeyBytes.length, keyBytes.length));
        return new SecretKeySpec(keyBytes, "AES");
    }
}
