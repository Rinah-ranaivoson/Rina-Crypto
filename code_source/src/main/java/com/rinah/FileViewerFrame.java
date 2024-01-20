package com.rinah;

import javax.swing.*;
import java.awt.*;

public class FileViewerFrame extends JFrame {

    public FileViewerFrame() {
        setTitle("Rina-Cryptography");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JTextPane textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton openButton = new JButton("Ouvrir Fichier");
        openButton.addActionListener(e -> new FileOpener(textPane));

        JButton saveButton = new JButton("Enregistrer Fichier");
        saveButton.addActionListener(e -> new FileSaver(textPane));

        JButton encryptButton = new JButton("Crypter");
        encryptButton.addActionListener(e -> new TextEncryptor(textPane));

        JButton decryptButton = new JButton("Décrypter");
        decryptButton.addActionListener(e -> new TextDecryptor(textPane));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(encryptButton);
        buttonPanel.add(decryptButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}
