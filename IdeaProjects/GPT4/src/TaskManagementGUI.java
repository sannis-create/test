//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class TaskManagementGUI extends JFrame {
//    private static List<Task> tasks = new ArrayList<>();
//
//    private JTextField taskNameField;
//    private JTextField skillNameField;
//    private JComboBox<String> skillLevelComboBox;
//    private JTextArea taskListArea;
//
//    public TaskManagementGUI(List<Task> tasks) {
//        setTitle("Task Management");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(400, 400);
//        setLayout(new BorderLayout());
//
//        JPanel addTaskPanel = createAddTaskPanel();
//        add(addTaskPanel, BorderLayout.NORTH);
//
//        taskListArea = new JTextArea();
//        taskListArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(taskListArea);
//        add(scrollPane, BorderLayout.CENTER);
//    }
//
//    private JPanel createAddTaskPanel() {
//        JPanel panel = new JPanel(new GridLayout(3, 2));
//
//        JLabel taskNameLabel = new JLabel("Task Name:");
//        taskNameField = new JTextField();
//        JLabel skillNameLabel = new JLabel("Skill Name:");
//        skillNameField = new JTextField();
//        JLabel skillLevelLabel = new JLabel("Skill Level:");
//        String[] skillLevels = {"Beginner", "Intermediate", "Expert"};
//        skillLevelComboBox = new JComboBox<>(skillLevels);
//
//        JButton addTaskButton = new JButton("Add Task");
//        addTaskButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addTask();
//            }
//        });
//
//        panel.add(taskNameLabel);
//        panel.add(taskNameField);
//        panel.add(skillNameLabel);
//        panel.add(skillNameField);
//        panel.add(skillLevelLabel);
//        panel.add(skillLevelComboBox);
//        panel.add(new JLabel()); // Empty label for spacing
//        panel.add(addTaskButton);
//
//        return panel;
//    }
//
//    private void addTask() {
//        String taskName = taskNameField.getText();
//        String skillName = skillNameField.getText();
//        String selectedSkillLevel = (String) skillLevelComboBox.getSelectedItem();
//        char skillLevel = mapSkillLevel(selectedSkillLevel);
//
//        Task task = findTaskByName(taskName);
//        if (task == null) {
//            task = new Task(taskName);
//            tasks.add(task);
//        }
//        task.addSkillRequirement(skillName, skillLevel);
//
//        updateTaskList();
//        clearFields();
//    }
//
//    private Task findTaskByName(String name) {
//        for (Task task : tasks) {
//            if (task.getName().equalsIgnoreCase(name)) {
//                return task;
//            }
//        }
//        return null;
//    }
//
//    private char mapSkillLevel(String skillLevel) {
//        switch (skillLevel) {
//            case "Beginner":
//                return 'b';
//            case "Intermediate":
//                return 'i';
//            case "Expert":
//                return 'e';
//            default:
//                return 'b'; // Default to Beginner
//        }
//    }
//
//    public void updateTaskList() {
//        taskListArea.setText("Tasks:\n");
//        for (Task task : tasks) {
//            taskListArea.append(task.getName() + ":\n");
//            for (Map.Entry<String, Character> entry : task.getSkillRequirements().entrySet()) {
//                String skillName = entry.getKey();
//                char requiredLevel = entry.getValue();
//                taskListArea.append("  " + skillName + " (Level: " + requiredLevel + ")\n");
//            }
//        }
//    }
//
//    private void clearFields() {
//        taskNameField.setText("");
//        skillNameField.setText("");
//        skillLevelComboBox.setSelectedIndex(0);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                TaskManagementGUI app = new TaskManagementGUI(tasks);
//                app.setVisible(true);
//            }
//        });
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaskManagementGUI extends JPanel {
    private List<Task> tasks;

    private JTextField taskNameField;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public TaskManagementGUI(List<Task> tasks, MatchingDisplayGUI matchingDisplayGUI) {
        this.tasks = tasks;

        setLayout(new BorderLayout());

        // Create task input panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Create task list panel
        JPanel listPanel = createListPanel();
        add(listPanel, BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JLabel nameLabel = new JLabel("Task:");
        taskNameField = new JTextField(20);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        panel.add(nameLabel);
        panel.add(taskNameField);
        panel.add(addButton);

        return panel;
    }

    private JPanel createListPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        panel.add(new JLabel("Task List:"), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void addTask() {
        String taskName = taskNameField.getText().trim();
        if (!taskName.isEmpty()) {
            Task task = new Task(taskName);
            tasks.add(task);
            taskListModel.addElement(taskName);
            taskNameField.setText("");
        }
    }

    public void updateTaskList() {
        taskListModel.clear();
        for (Task task : tasks) {
            taskListModel.addElement(task.getName());
        }
    }
}
