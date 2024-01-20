package com.rinah;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {

    public FileSaver(JTextPane textPane) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Enregistrer Fichier");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers texte (*.txt)", "txt");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.toLowerCase().endsWith(".txt")) {
                    filePath += ".txt"; 
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    writer.write(textPane.getText());
                }

                JOptionPane.showMessageDialog(null, "Fichier enregistré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement du fichier", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
