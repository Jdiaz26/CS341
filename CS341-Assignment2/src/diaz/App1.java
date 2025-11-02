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
import java.awt.Color;
import javax.swing.JTextArea;

public class App1 {

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
					App1 window = new App1();
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
	public App1() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblScabbleApp = new JLabel("Scrabble App");
		lblScabbleApp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblScabbleApp.setBounds(152, 11, 119, 22);
		frame.getContentPane().add(lblScabbleApp);
		
		textField = new JTextField();
		textField.setBounds(40, 71, 333, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblInstructions = new JLabel("Type your letters below.");
		lblInstructions.setBounds(40, 50, 140, 14);
		frame.getContentPane().add(lblInstructions);
		
		JButton btnGenerate = new JButton("Generate Words");
		btnGenerate.setBounds(135, 105, 136, 23);
		frame.getContentPane().add(btnGenerate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 154, 333, 84);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setBounds(40, 133, 46, 14);
		frame.getContentPane().add(lblResults);
		
		// Add button action listener here
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateWords();
			}
		});
	}

	
	private void createEvents() {
		
	}

	// Method to generate permutations or show errors
	private void generateWords() {
		textArea.setText(""); // clear output
		String input = textField.getText().trim();

		// Check if too many letters
		if (input.length() > 7) {
			textArea.setText("Error: You can only enter up to 7 tiles.");
			return;
		}

		// Check for non-letters
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (!Character.isLetter(c)) {
				textArea.setText("Error: Only letters A-Z allowed.");
				return;
			}
		}

		// Check empty input
		if (input.length() == 0) {
			textArea.setText("Error: Please enter at least one letter.");
			return;
		}

		// Generate all permutations
		permute("", input.toUpperCase());
	}

	// Recursive permutation method
	private void permute(String prefix, String remaining) {
		if (remaining.length() == 0) {
			textArea.append(prefix + "\n");
		} else {
			for (int i = 0; i < remaining.length(); i++) {
				String newPrefix = prefix + remaining.charAt(i);
				String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
				permute(newPrefix, newRemaining);
			}
		}
	}
}

