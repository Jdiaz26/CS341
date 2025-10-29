package diaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectsPanel {

    // Data
    private List<Project> projects = new ArrayList<>();

    // Create Projects Page
    public JPanel createProjectsPage(JPanel mainPanel) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Projects"));

        // Top controls
        JPanel topPanel = new JPanel();
        JTextField projectNameField = new JTextField(15);
        JButton addProjectBtn = new JButton("Add Project");
        topPanel.add(new JLabel("New Project:"));
        topPanel.add(projectNameField);
        topPanel.add(addProjectBtn);
        panel.add(topPanel, BorderLayout.NORTH);

        // Project list
        JPanel projectListPanel = new JPanel();
        projectListPanel.setLayout(new BoxLayout(projectListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(projectListPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add project button
        addProjectBtn.addActionListener(e -> {
            String projectName = projectNameField.getText().trim();
            if (!projectName.isEmpty()) {
                Project newProject = new Project(projectName);
                projects.add(newProject);
                JButton projectBtn = createProjectButton(newProject, mainPanel);
                projectListPanel.add(projectBtn);
                projectListPanel.revalidate();
                projectListPanel.repaint();
                projectNameField.setText("");
            }
        });

        // Load existing projects (if any)
        for (Project p : projects) {
            JButton projectBtn = createProjectButton(p, mainPanel);
            projectListPanel.add(projectBtn);
        }

        return panel;
    }

    // Helper method: create project button
    private JButton createProjectButton(Project project, JPanel mainPanel) {
        JButton btn = new JButton(project.getName());
        btn.addActionListener(e -> openProject(project, mainPanel));
        return btn;
    }

    // Open project
    private void openProject(Project project, JPanel mainPanel) {
        mainPanel.removeAll();
        TaskListPanel taskListPanel = new TaskListPanel();
        JPanel projectTasksPanel = taskListPanel.createTaskList(project.getName());
        taskListPanel.loadProjectTasks(project);  // load saved tasks
        mainPanel.add(projectTasksPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}






