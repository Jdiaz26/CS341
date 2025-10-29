package diaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("User Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 180);
        setLayout(new GridLayout(3, 2, 5, 5));
        setLocationRelativeTo(null);

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(new JLabel());
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        });
    }
    
    // Simple user
    private User[] users = {
        new User("admin", "1234", "Project Manager"),
    };

    private void checkLogin() {
        String userInput = usernameField.getText();
        String passInput = new String(passwordField.getPassword());

        boolean found = false;
        int i = 0;

        while (i < users.length) {
            User u = users[i];
            if (u.getUsername().equals(userInput) && u.getPassword().equals(passInput)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose(); // close login window
                TaskManagerApp.showDashboard(u); // passes user to your GUI
                found = true;
                break;
            }
            i++;
        }

        if (!found) {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
    }

}

