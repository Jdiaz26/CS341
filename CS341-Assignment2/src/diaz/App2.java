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
		lblPwStrApp.setBounds(30, 11, 200, 20);
		frame.getContentPane().add(lblPwStrApp);
		
		JLabel lblEnterPw = new JLabel("Enter New Password:");
		lblEnterPw.setBounds(30, 55, 150, 14);
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
		
		// Button listener here
		btnCheckStr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPassword();
			}
		});
	}

	private void createEvents() {
		// currently empty (already attached listener above)
	}

	// Main logic
	private void checkPassword() {
		textArea.setText(""); // clear results
		String password = textField.getText();

		// Check for spaces
		if (password.contains(" ")) {
			textArea.setText("Error: Password cannot contain spaces.");
			return;
		}

		// Check for valid size (8–12)
		if (password.length() < 8 || password.length() > 12) {
			textArea.setText("Error: Password must be between 8 and 12 characters.");
			return;
		}

		// Find largest block (case-sensitive)
		int largestBlock = findLargestBlock(password);

		// Display results
		textArea.append("Password: " + password + "\n");
		textArea.append("Largest block length: " + largestBlock + "\n");

		if (largestBlock <= 2) {
			textArea.append("This is a decent password.");
		} else {
			textArea.append("Try shrinking the repeating block.");
		}
	}

	// Finds the longest sequence of same adjacent characters
	private int findLargestBlock(String s) {
		if (s.length() == 0) return 0;

		int maxBlock = 1;
		int currentBlock = 1;

		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt(i - 1)) {
				currentBlock++;
			} else {
				if (currentBlock > maxBlock) {
					maxBlock = currentBlock;
				}
				currentBlock = 1; // reset
			}
		}

		// check at the end of the string
		if (currentBlock > maxBlock) {
			maxBlock = currentBlock;
		}

		return maxBlock;
	}
}

