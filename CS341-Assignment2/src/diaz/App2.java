package diaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The App2 class builds a simple Password Strength application using the Windowbuilder.
 * It allows the user to enter a new password and checks its strength based on certain criteria.
 * 
 * Error detection:
 * Displays an error if the password contains spaces.
 * Displays an error if the password is less than 8 characters long.
 * 
 * @author Jefferson D.
 * @version 1.0
 */

public class App2 {

	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App2 window = new App2();
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
	public App2() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPwStrApp = new JLabel("Password Strength App");
		lblPwStrApp.setFont(new Font("MS UI Gothic", Font.PLAIN, 14));
		lblPwStrApp.setBounds(30, 11, 150, 20);
		frame.getContentPane().add(lblPwStrApp);
		
		JLabel lblEnterPw = new JLabel("Enter New Password:");
		lblEnterPw.setBounds(30, 55, 121, 14);
		frame.getContentPane().add(lblEnterPw);
		
		textField = new JTextField();
		textField.setBounds(30, 80, 394, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCheckStr = new JButton("Check Strength");
		btnCheckStr.setBounds(141, 111, 132, 23);
		frame.getContentPane().add(btnCheckStr);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 160, 394, 76);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setBounds(30, 140, 46, 14);
		frame.getContentPane().add(lblResult);
		
		btnCheckStr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPassword();
			}
		});
	}
	
	/**
	 * Optional: Create the events for the application.
	 */
	private void createEvents() {

		
	}
	
	/**
	 * Main logic to check password strength.
	 */
	
	private void checkPassword() {
		textArea.setText(""); //Clears results
		String password = textField.getText();
		
		//Checks for spaces
		if (password.contains(" ")) {
			textArea.setText("Error: Password cannot contain spaces.");
			return;
		}
		
		//Checks for minimum length
		if (password.length() < 8) {
			textArea.setText("Error: Password must be at least 8 characters long.");
			return;
		}
		
		//Finds the largest block 
		int largestBlock = findLargestBlock(password);
		
		//Displays results
		textArea.append("Password: " + password.length() + "\n");
		textArea.append("Largest Block: " + largestBlock + "\n");
		
		if (largestBlock <= 2) {
			textArea.append("This is a decent password.");
		}else {
			textArea.append("Try shrinking the repeating block.");
		}
		
		
	}
	
	/**
	 * Finds the largest sequence of adjacent repeating characters.
	 */
	
	private int findLargestBlock(String s) {
		if (s.length() == 0) return 0;
		
		int maxBlock = 1;
		int currentBlock = 1;
		
		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				currentBlock++;
			} else {
				if (currentBlock > maxBlock) {
					maxBlock = currentBlock;
				}
				currentBlock = 1;
		}
	  }
		if (currentBlock > maxBlock) {
			maxBlock = currentBlock;
		}
		
		return maxBlock;
		
	}
	
		
		
		
		
}
