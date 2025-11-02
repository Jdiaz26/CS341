package diaz;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SalesApp {

    private JFrame frame;
    private JTextField txtItem, txtCost, txtQuantity, txtTotal;
    private JTextArea txtArea;
    private SalesSlip slip = new SalesSlip(); // 👈 Create one SalesSlip

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SalesApp window = new SalesApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SalesApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Sales List");
        frame.setBounds(100, 100, 450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Sales List");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(133, 11, 150, 25);
        frame.getContentPane().add(lblTitle);

        JLabel lblItem = new JLabel("Item:");
        lblItem.setBounds(36, 50, 46, 14);
        frame.getContentPane().add(lblItem);

        txtItem = new JTextField();
        txtItem.setBounds(92, 47, 301, 20);
        frame.getContentPane().add(txtItem);

        JLabel lblCost = new JLabel("Cost $:");
        lblCost.setBounds(36, 80, 46, 14);
        frame.getContentPane().add(lblCost);

        txtCost = new JTextField();
        txtCost.setBounds(92, 77, 134, 20);
        frame.getContentPane().add(txtCost);

        JLabel lblQty = new JLabel("Quantity:");
        lblQty.setBounds(36, 110, 60, 14);
        frame.getContentPane().add(lblQty);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(92, 107, 134, 20);
        frame.getContentPane().add(txtQuantity);

        JButton btnAdd = new JButton("Add Item");
        btnAdd.setBounds(276, 90, 117, 25);
        frame.getContentPane().add(btnAdd);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(36, 150, 372, 100);
        frame.getContentPane().add(scrollPane);

        txtArea = new JTextArea();
        txtArea.setEditable(false);
        scrollPane.setViewportView(txtArea);

        JLabel lblTotal = new JLabel("Total Sales:");
        lblTotal.setBounds(70, 270, 68, 14);
        frame.getContentPane().add(lblTotal);

        txtTotal = new JTextField();
        txtTotal.setBounds(148, 267, 86, 20);
        txtTotal.setEditable(false);
        frame.getContentPane().add(txtTotal);

        //Add item button
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = txtItem.getText();
                    double cost = Double.parseDouble(txtCost.getText());
                    int qty = Integer.parseInt(txtQuantity.getText());

                    if (cost >= 100 || cost < 0) {
                        JOptionPane.showMessageDialog(frame, "Cost must be between 0 and 100");
                        return;
                    }

                    SalesItem item = new SalesItem(name, cost, qty);
                    slip.addItem(item);

                    txtArea.setText(slip.toString());
                    txtTotal.setText(String.format("$%.2f", slip.getTotalSales()));

                    // clear inputs
                    txtItem.setText("");
                    txtCost.setText("");
                    txtQuantity.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers for cost and quantity");
                }
            }
        });
    }
}

