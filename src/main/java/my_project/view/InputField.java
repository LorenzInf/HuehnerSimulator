package my_project.view;

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

    public InputField() {
        scanner = new Scanner();
        parser = new Parser();
        statusLabel.setFont(new Font(statusLabel.getFont().getName(),Font.ITALIC,12));
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
                parser.parse(textArea.getText());
            }
        });
    }

    public void scan() {
        boolean scanSuccess = scanner.scan(textArea.getText());
        if(!scanSuccess) {
            statusLabel.setText("Fix errors");
            statusLabel.setForeground(new Color(200,10,10));
            statusLabel.setFont(new Font(statusLabel.getFont().getName(),Font.BOLD,17));
        } else {
            statusLabel.setText("No errors");
            statusLabel.setForeground(Color.BLACK);
            statusLabel.setFont(new Font(statusLabel.getFont().getName(),Font.ITALIC,12));
        }
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JPanel getPanel() {
        return panel1;
    }
}
