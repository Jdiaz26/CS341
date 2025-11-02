package diaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MainApp extends JFrame {

    JTextArea outputArea;
    JButton openButton;
    MyLinkedList list;

    public MainApp() {
        setTitle("Mean and Standard Deviation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        outputArea = new JTextArea();
        openButton = new JButton("Open File");

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        add(openButton, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        setVisible(true);
    }

    void openFile() {
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            list = new MyLinkedList();
            int invalidCount = 0;

            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();

                while (line != null) {
                    line = line.trim();
                    if (!line.equals("")) {
                        try {
                            double num = Double.parseDouble(line);
                            list.add(num);
                        } catch (NumberFormatException ex) {
                            invalidCount++;
                        }
                    }
                    line = reader.readLine();
                }

                reader.close();

                if (list.size() == 0) {
                    outputArea.setText("No valid numbers in file.");
                    return;
                }

                double mean = list.getMean();
                double std = list.getStd(mean);

                outputArea.setText("Valid numbers: " + list.size() + "\n");
                outputArea.append("Invalid lines: " + invalidCount + "\n");
                outputArea.append("Mean: " + mean + "\n");
                outputArea.append("Standard deviation: " + std + "\n");

            } catch (IOException ex) {
                outputArea.setText("Error reading file.");
            }
        }
    }

    public static void main(String[] args) {
        new MainApp();
    }
}

