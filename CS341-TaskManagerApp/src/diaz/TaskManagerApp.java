package diaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TaskManagerApp {

    /**
     * @wbp.parser.entryPoint
     */
    public static void showDashboard(User user) {
        JFrame frame = new JFrame("Task Manager Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Task Manager");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        headerPanel.add(title, BorderLayout.WEST);

        JLabel userInfo = new JLabel("Logged in as: " + user.getUsername() + " (" + user.getRole() + ")");
        headerPanel.add(userInfo, BorderLayout.EAST);

        frame.add(headerPanel, BorderLayout.NORTH);

        // Sidebar buttons
        JPanel sidebar = new JPanel(new GridLayout(3, 1, 0, 10));
        sidebar.setPreferredSize(new Dimension(180, 0));
        JButton btnTasks = new JButton("My Tasks");
        JButton btnProjects = new JButton("Projects");
        JButton btnSettings = new JButton("Settings");
        sidebar.add(btnTasks);
        sidebar.add(btnProjects);
        sidebar.add(btnSettings);
        frame.add(sidebar, BorderLayout.WEST);

        // Main panel 
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // Personal Tasks Panel
        btnTasks.addActionListener(e -> {
            mainPanel.removeAll();
            TaskListPanel taskListPanel = new TaskListPanel();
            JPanel personalPanel = taskListPanel.createTaskList("My Tasks");
            taskListPanel.loadPersonalTasks(); // load saved personal tasks
            mainPanel.add(personalPanel, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        // Projects Panel
        btnProjects.addActionListener(e -> {
            mainPanel.removeAll();
            ProjectsPanel projectsPanel = new ProjectsPanel();
            mainPanel.add(projectsPanel.createProjectsPage(mainPanel), BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        // Settings Panel
        btnSettings.addActionListener(e -> {
            mainPanel.removeAll();
            SettingsPanel settingsPanel = new SettingsPanel();
            JPanel settings = settingsPanel.createSettingsPage(user);
            mainPanel.add(settings, BorderLayout.CENTER);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        // Show dashboard
        frame.setVisible(true);
    }
}








