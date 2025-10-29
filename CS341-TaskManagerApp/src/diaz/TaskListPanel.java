package diaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TaskListPanel {

    // Data / UI Components
    private List<TaskItem> tasks = new ArrayList<>();
    private List<TaskItemData> personalTasks = new ArrayList<>();
    private JPanel taskListPanel;
    private JTextField searchField;
    private JCheckBox showCompleted;
    private Project currentProject;

    private JButton addBtn;
    private JButton shareBtn;

    // Create Task List Panel
    public JPanel createTaskList(String title) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(lblTitle, BorderLayout.NORTH);

        // Top controls
        JPanel topPanel = new JPanel();
        JTextField taskInput = new JTextField(15);
        JTextField dueInput = new JTextField("Due date", 10);
        addBtn = new JButton("Add Task");
        shareBtn = new JButton("Share");
        searchField = new JTextField("Search...", 10);
        showCompleted = new JCheckBox("Show Completed", true);

        topPanel.add(taskInput);
        topPanel.add(dueInput);
        topPanel.add(addBtn);
        topPanel.add(shareBtn);
        topPanel.add(searchField);
        topPanel.add(showCompleted);
        panel.add(topPanel, BorderLayout.SOUTH);

        // Task list panel
        taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskListPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Button Actions
        addBtn.addActionListener(e -> {
            String taskText = taskInput.getText().trim();
            String dueText = dueInput.getText().trim();
            if (!taskText.isEmpty()) {
                TaskItem item = new TaskItem(taskText, dueText);
                tasks.add(item);
                taskListPanel.add(item.getPanel());

                if (currentProject != null) {
                    currentProject.addTask(taskText, dueText);
                } else {
                    personalTasks.add(new TaskItemData(taskText, dueText));
                }

                taskListPanel.revalidate();
                taskListPanel.repaint();
                taskInput.setText("");
                dueInput.setText("Due date");
            }
        });

        shareBtn.addActionListener(e -> JOptionPane.showMessageDialog(panel, "Tasks shared (test)."));

        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                filterTasks();
            }
        });

        showCompleted.addActionListener(e -> updateVisibility());

        return panel;
    }

    // Load tasks from a project
    public void loadProjectTasks(Project project) {
        if (taskListPanel == null) return;

        currentProject = project;
        tasks.clear();
        taskListPanel.removeAll();

        for (Project.Task t : project.getTasks()) {
            TaskItem item = new TaskItem(t.name, t.due);
            item.completed = t.completed;
            item.completeBox.setSelected(t.completed);
            tasks.add(item);
            taskListPanel.add(item.getPanel());
        }

        taskListPanel.revalidate();
        taskListPanel.repaint();
    }

    // Load personal tasks
    public void loadPersonalTasks() {
        if (taskListPanel == null) return;

        currentProject = null;
        tasks.clear();
        taskListPanel.removeAll();

        for (TaskItemData t : personalTasks) {
            TaskItem item = new TaskItem(t.name, t.due);
            item.completed = t.completed;
            tasks.add(item);
            taskListPanel.add(item.getPanel());
        }

        taskListPanel.revalidate();
        taskListPanel.repaint();
    }

    // Helper Methods
    private void filterTasks() {
        String keyword = searchField.getText().toLowerCase();
        for (TaskItem t : tasks) {
            t.getPanel().setVisible(t.getName().toLowerCase().contains(keyword));
        }
    }

    private void updateVisibility() {
        for (TaskItem t : tasks) {
            t.getPanel().setVisible(!t.isCompleted() && !showCompleted.isSelected() ? false : true);
        }
    }

    //TaskItem Class
    class TaskItem {
        private String name;
        private String due;
        private boolean completed;
        private JPanel panel;
        private JCheckBox completeBox;
        private JLabel nameLabel;
        private JButton editBtn, saveBtn, cancelBtn, deleteBtn;
        private JTextField editNameField, editDueField;

        public TaskItem(String name, String due) {
            this.name = name;
            this.due = due;
            createUI();
        }

        private void createUI() {
            panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            completeBox = new JCheckBox();
            nameLabel = new JLabel(name + " (Due: " + due + ")");
            editBtn = new JButton("Edit");
            saveBtn = new JButton("Save");
            cancelBtn = new JButton("Cancel");
            deleteBtn = new JButton("Delete");

            editNameField = new JTextField(name, 10);
            editDueField = new JTextField(due, 8);
            editNameField.setVisible(false);
            editDueField.setVisible(false);
            saveBtn.setVisible(false);
            cancelBtn.setVisible(false);

            panel.add(completeBox);
            panel.add(nameLabel);
            panel.add(editNameField);
            panel.add(editDueField);
            panel.add(editBtn);
            panel.add(saveBtn);
            panel.add(cancelBtn);
            panel.add(deleteBtn);

            // Actions
            completeBox.addActionListener(e -> {
                completed = completeBox.isSelected();
                updateVisibility();
            });

            editBtn.addActionListener(e -> {
                nameLabel.setVisible(false);
                editNameField.setVisible(true);
                editDueField.setVisible(true);
                editBtn.setVisible(false);
                saveBtn.setVisible(true);
                cancelBtn.setVisible(true);
            });

            saveBtn.addActionListener(e -> {
                name = editNameField.getText();
                due = editDueField.getText();
                nameLabel.setText(name + " (Due: " + due + ")");
                nameLabel.setVisible(true);
                editNameField.setVisible(false);
                editDueField.setVisible(false);
                editBtn.setVisible(true);
                saveBtn.setVisible(false);
                cancelBtn.setVisible(false);
            });

            cancelBtn.addActionListener(e -> {
                editNameField.setText(name);
                editDueField.setText(due);
                nameLabel.setVisible(true);
                editNameField.setVisible(false);
                editDueField.setVisible(false);
                editBtn.setVisible(true);
                saveBtn.setVisible(false);
                cancelBtn.setVisible(false);
            });

            deleteBtn.addActionListener(e -> {
                taskListPanel.remove(panel);
                tasks.remove(TaskItem.this);

                if (currentProject != null) {
                    currentProject.getTasks().removeIf(t -> t.name.equals(name) && t.due.equals(due));
                } else {
                    personalTasks.removeIf(t -> t.name.equals(name) && t.due.equals(due));
                }

                taskListPanel.revalidate();
                taskListPanel.repaint();
            });
        }

        public JPanel getPanel() { return panel; }
        public String getName() { return name; }
        public boolean isCompleted() { return completed; }
    }

    // Helper class for personal tasks storage
    class TaskItemData {
        String name;
        String due;
        boolean completed;
        TaskItemData(String n, String d) { name = n; due = d; completed = false; }
    }
}





