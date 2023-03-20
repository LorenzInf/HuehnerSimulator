package my_project.view;

import my_project.control.ProgramController;
import my_project.model.Parser;
import my_project.model.Scanner;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputField {
    private JTextArea textArea;
    private JPanel panel1;
    private JLabel statusLabel;
    private JButton runButton;
    private final Scanner scanner;
    private final Parser parser;
    private ProgramController programController;

    public InputField(ProgramController programController) {
        scanner = new Scanner();
        parser = new Parser();
        this.programController = programController;
        statusLabel.setFont(new Font(statusLabel.getFont().getName(),Font.ITALIC,12));
        statusLabel.setText("Es müssen Mengenklammern gesetzt werden!");
        System.out.println(statusLabel.getFont().getSize());
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                scan();
            }
            public void removeUpdate(DocumentEvent e) {
                scan();
            }
            public void changedUpdate(DocumentEvent e) {

            }
        });
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean works = false;
                String output = parser.parse(textArea.getText());
                if(output.equals("Keine Fehler")){
                    works = true;
                }
                statusLabel.setText(output);
                if(works){
                    programController.startSimulation(parser.getQueue());
                }
            }
        });
    }

    public void scan() {
        boolean scanSuccess = scanner.scan(textArea.getText());
        if(!scanSuccess) {
            statusLabel.setText("Unbekanntes Symbol");
            statusLabel.setForeground(new Color(200,10,10));
            statusLabel.setFont(new Font(statusLabel.getFont().getName(),Font.BOLD,12));
        } else {
            statusLabel.setForeground(Color.BLACK);
            statusLabel.setFont(new Font(statusLabel.getFont().getName(),Font.ITALIC,12));
        }
        String output = parser.parse(textArea.getText());
        statusLabel.setText(output);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JPanel getPanel() {
        return panel1;
    }
}
