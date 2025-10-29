package diaz;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel {

    public JPanel createSettingsPage(User user) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Settings"));

        JLabel lblUsername = new JLabel("Username: " + user.getUsername());
        JLabel lblPassword = new JLabel("Password: " + user.getPassword());
        JLabel lblUserID = new JLabel("User ID: " + user.getUsername());
        JButton btnLogout = new JButton("Logout");

        // When the logout button is clicked, closes the entire app
        btnLogout.addActionListener(e -> System.exit(0));

        panel.add(lblUsername);
        panel.add(lblPassword);
        panel.add(lblUserID);
        panel.add(btnLogout);

        return panel;
    }
}


