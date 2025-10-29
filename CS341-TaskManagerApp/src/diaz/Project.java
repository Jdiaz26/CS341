package diaz;

import java.util.ArrayList;

public class Project {
    private String name;
    private ArrayList<Task> tasks;

    public Project(String name) {
        this.name = name;
        tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(String name, String due) {
        tasks.add(new Task(name, due));
    }


    public static class Task {
        public String name;
        public String due;
        public boolean completed;

        public Task(String name, String due) {
            this.name = name;
            this.due = due;
            this.completed = false;
        }
    }
}



