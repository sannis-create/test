//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.List;
//
//public class TaskManagerGUI {
//
//    private JFrame frame;
//    private JList<String> taskList;
//    private DefaultListModel<String> taskListModel;
//    private JTextArea skillsTextArea;
//    private JTextField taskToAddField;
//    private JTextField skillsToAddField;
//    private Map<String, Map<String, String>> taskData;
//
//    public TaskManagerGUI() {
//        taskData = new HashMap<>();
//
//        frame = new JFrame("Task Manager");
//        frame.setSize(500, 400);
//        frame.setLayout(new FlowLayout());
//
//        taskListModel = new DefaultListModel<>();
//        taskList = new JList<>(taskListModel);
//        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        taskList.addListSelectionListener(e -> {
//            if (!e.getValueIsAdjusting()) {
//                displaySkillsForSelectedTask();
//            }
//        });
//
//        skillsTextArea = new JTextArea(10, 30);
//
//        taskToAddField = new JTextField(20);
//        skillsToAddField = new JTextField(20);
//
//        JButton addTaskButton = new JButton("Add Task");
//        addTaskButton.addActionListener(new AddTaskButtonListener());
//
//        frame.add(new JScrollPane(taskList));
//        frame.add(skillsTextArea);
//        frame.add(new JLabel("Task Name:"));
//        frame.add(taskToAddField);
//        frame.add(new JLabel("Skills (format: skill:level, ...):"));
//        frame.add(skillsToAddField);
//        frame.add(addTaskButton);
//
//        loadTasks();
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
//
//    private void displaySkillsForSelectedTask() {
//        String selectedTask = taskList.getSelectedValue();
//        if (selectedTask != null) {
//            Map<String, String> skills = taskData.get(selectedTask);
//            skillsTextArea.setText("");
//            skills.forEach((skill, level) -> {
//                skillsTextArea.append(skill + ": " + level + "\n");
//            });
//        }
//    }
//
//    private void loadTasks() {
//        try {
//            List<String> lines = Files.readAllLines(Paths.get("tasks.txt"));
//            for (String line : lines) {
//                String[] parts = line.split("\\|");
//                String taskName = parts[0].trim();
//                taskListModel.addElement(taskName);
//                Map<String, String> skills = new HashMap<>();
//                for (String skillPair : parts[1].split(",")) {
//                    String[] skillParts = skillPair.split(":");
//                    skills.put(skillParts[0].trim(), skillParts[1].trim());
//                }
//                taskData.put(taskName, skills);
//            }
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(frame, "Error reading tasks.txt", "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//
//    private class AddTaskButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            String taskName = taskToAddField.getText().trim();
//            String skillsInput = skillsToAddField.getText().trim();
//
//            if (!taskName.isEmpty() && !skillsInput.isEmpty()) {
//                Map<String, String> skills = new HashMap<>();
//                for (String skillPair : skillsInput.split(",")) {
//                    String[] skillParts = skillPair.split(":");
//                    skills.put(skillParts[0].trim(), skillParts[1].trim());
//                }
//
//                taskData.put(taskName, skills);
//                taskListModel.addElement(taskName);
//
//                // Save to tasks.txt
//                try {
//                    StringBuilder sb = new StringBuilder();
//                    taskData.forEach((task, skillMap) -> {
//                        sb.append(task).append(" | ");
//                        skillMap.forEach((skill, level) -> {
//                            sb.append(skill).append(":").append(level).append(", ");
//                        });
//                        // Remove the trailing comma and space
//                        sb.setLength(sb.length() - 2);
//                        sb.append("\n");
//                    });
//                    Files.write(Paths.get("tasks.txt"), sb.toString().getBytes());
//                } catch (IOException ex) {
//                    JOptionPane.showMessageDialog(frame, "Error writing to tasks.txt", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//
//                taskToAddField.setText("");
//                skillsToAddField.setText("");
//            } else {
//                JOptionPane.showMessageDialog(frame, "Please enter task name and skills.", "Input Error", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new TaskManagerGUI());
//    }
//}
//
//
