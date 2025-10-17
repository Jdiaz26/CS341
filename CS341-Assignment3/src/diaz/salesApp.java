package diaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class salesApp {

	private JFrame frame;
	private JTextField textItem;
	private JTextField textCost;
    private JTextField textQty;
    private JTextArea textArea;
    private JTextField textTotal;
    
    private SalesSlip slip = new SalesSlip();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					salesApp window = new salesApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public salesApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 492, 335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSalesList = new JLabel("Sales List");
		lblSalesList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSalesList.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalesList.setBounds(133, 11, 134, 20);
		frame.getContentPane().add(lblSalesList);
		
		JLabel lblItem = new JLabel("Item:");
		lblItem.setBounds(36, 41, 61, 14);
		frame.getContentPane().add(lblItem);
		
		textItem = new JTextField();
		textItem.setBounds(107, 38, 301, 20);
		frame.getContentPane().add(textItem);
		textItem.setColumns(10);
		
		JLabel lblCost = new JLabel("Cost $:");
		lblCost.setBounds(36, 72, 61, 14);
		frame.getContentPane().add(lblCost);
		
		textCost = new JTextField();
		textCost.setBounds(107, 69, 134, 20);
		frame.getContentPane().add(textCost);
		textCost.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setBounds(36, 103, 61, 14);
		frame.getContentPane().add(lblQuantity);
		
		textQty = new JTextField();
		textQty.setColumns(10);
		textQty.setBounds(107, 100, 134, 20);
		frame.getContentPane().add(textQty);
		
		JButton btnAdd = new JButton("Add Item");
		btnAdd.setBounds(276, 85, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 136, 372, 69);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblTotal = new JLabel("Total Sales:");
		lblTotal.setBounds(70, 222, 68, 14);
		frame.getContentPane().add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setBounds(148, 219, 86, 20);
		frame.getContentPane().add(textTotal);
		textTotal.setColumns(10);
		
		//Button Event
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textItem.getText();
				double cost = Double.parseDouble(textCost.getText());
				int qty = Integer.parseInt(textQty.getText());
				
				if(cost < 0 || cost >= 100) {
					textArea.setText("Cost must be between 0 and 100\n");
					return;
				}
				
				SalesItem item = new SalesItem(name, cost, qty);
				slip.addItem(item);
				textArea.setText(slip.toString());
				textTotal.setText("" + slip.getTotal());
				
				textItem.setText("");
				textCost.setText("");
				textQty.setText("");
			}
		});
	}
}
