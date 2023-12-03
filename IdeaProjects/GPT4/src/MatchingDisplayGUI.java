//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MatchingDisplayGUI extends JFrame {
//    private List<Resource> resources;
//    private List<Task> tasks;
//
//    private JComboBox<String> taskComboBox;
//    private JComboBox<String> strategyComboBox;
//    private JTextArea matchedResourcesArea;
//
//    public MatchingDisplayGUI(List<Resource> resources, List<Task> tasks) {
//        this.resources = resources;
//        this.tasks = tasks;
//
//        setTitle("Skill Matching and Display");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(400, 400);
//        setLayout(new BorderLayout());
//
//        JPanel matchingPanel = createMatchingPanel();
//        add(matchingPanel, BorderLayout.NORTH);
//
//        matchedResourcesArea = new JTextArea();
//        matchedResourcesArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(matchedResourcesArea);
//        add(scrollPane, BorderLayout.CENTER);
//    }
//
//    private JPanel createMatchingPanel() {
//        JPanel panel = new JPanel(new GridLayout(3, 2));
//
//        JLabel taskLabel = new JLabel("Select Task:");
//        taskComboBox = new JComboBox<>();
//        for (Task task : tasks) {
//            taskComboBox.addItem(task.getName());
//        }
//
//        JLabel strategyLabel = new JLabel("Select Strategy:");
//        strategyComboBox = new JComboBox<>(new String[]{"Exact Match", "Skill-Only Match"});
//
//        JButton matchButton = new JButton("Match");
//        matchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                performMatching();
//            }
//        });
//
//        panel.add(taskLabel);
//        panel.add(taskComboBox);
//        panel.add(strategyLabel);
//        panel.add(strategyComboBox);
//        panel.add(new JLabel()); // Empty label for spacing
//        panel.add(matchButton);
//
//        return panel;
//    }
//
//    private void performMatching() {
//        matchedResourcesArea.setText("");
//        String selectedTaskName = (String) taskComboBox.getSelectedItem();
//        Task selectedTask = findTaskByName(selectedTaskName);
//
//        if (selectedTask != null) {
//            String selectedStrategy = (String) strategyComboBox.getSelectedItem();
//            MatchingStrategy matchingStrategy;
//            if (selectedStrategy.equals("Exact Match")) {
//                matchingStrategy = new ExactMatch();
//            } else {
//                matchingStrategy = new SkillOnlyMatch();
//            }
//
//            List<Resource> matchedResources = matchingStrategy.findMatches(selectedTask, resources);
//            if (matchedResources.isEmpty()) {
//                matchedResourcesArea.setText("No resources match the selected task.");
//            } else {
//                matchedResourcesArea.append("Matched Resources for Task '" + selectedTaskName + "':\n");
//                for (Resource resource : matchedResources) {
//                    matchedResourcesArea.append("- " + resource.getName() + "\n");
//                }
//            }
//        } else {
//            matchedResourcesArea.setText("Invalid Task selected.");
//        }
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
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                // Initialize resources and tasks (read from files or other sources)
//                List<Resource> resources = new ArrayList<>();
//                List<Task> tasks = new ArrayList<>();
//                TaskResourceAllocatorApp app = new TaskResourceAllocatorApp(resources, tasks);
//
//                MatchingDisplayGUI matchingDisplayGUI = new MatchingDisplayGUI(resources, tasks);
//                matchingDisplayGUI.setVisible(true);
//            }
//        });
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MatchingDisplayGUI extends JPanel {
    private List<Resource> resources;
    private List<Task> tasks;

    private JComboBox<String> taskComboBox;
    private JComboBox<String> strategyComboBox;
    private JTextArea matchedResourcesArea;

    public MatchingDisplayGUI(List<Resource> resources, List<Task> tasks) {
        this.resources = resources;
        this.tasks = tasks;

        setLayout(new BorderLayout());

        // Create matching panel
        JPanel matchingPanel = createMatchingPanel();
        add(matchingPanel, BorderLayout.NORTH);

        // Create matched resources display area
        matchedResourcesArea = new JTextArea();
        matchedResourcesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(matchedResourcesArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createMatchingPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JLabel taskLabel = new JLabel("Select Task:");
        taskComboBox = new JComboBox<>();
        for (Task task : tasks) {
            taskComboBox.addItem(task.getName());
        }

        JLabel strategyLabel = new JLabel("Select Strategy:");
        strategyComboBox = new JComboBox<>(new String[]{"Exact Match", "Skill-Only Match"});

        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performMatching();
            }
        });

        panel.add(taskLabel);
        panel.add(taskComboBox);
        panel.add(strategyLabel);
        panel.add(strategyComboBox);
        panel.add(generateButton);

        return panel;
    }

    private void performMatching() {
        Object selectedTaskItem = taskComboBox.getSelectedItem();
        Object selectedStrategyItem = strategyComboBox.getSelectedItem();

        if (selectedTaskItem != null && selectedStrategyItem != null) {
            String selectedTaskName = selectedTaskItem.toString();
            Task selectedTask = null;
            for (Task task : tasks) {
                if (task.getName().equals(selectedTaskName)) {
                    selectedTask = task;
                    break;
                }
            }

            if (selectedTask != null) {
                String strategy = selectedStrategyItem.toString();
                MatchingStrategy matchingStrategy = (strategy.equals("Exact Match")) ?
                        new ExactMatch() : new SkillOnlyMatch();

                List<Resource> matchedResources = matchingStrategy.findMatches(selectedTask,resources);

                // Display matched resources in the text area
                StringBuilder matchedResourcesText = new StringBuilder();
                matchedResourcesText.append("Matched Resources:\n");
                for (Resource resource : matchedResources) {
                    matchedResourcesText.append("- ").append(resource.getName()).append("\n");
                }
                matchedResourcesArea.setText(matchedResourcesText.toString());
            }
        } else {
            // Handle the case when an item is not selected
            JOptionPane.showMessageDialog(this, "Please select a task and a strategy.");
        }
    }

    public void updateTaskComboBox() {
        taskComboBox.removeAllItems();
        for (Task task : tasks) {
            taskComboBox.addItem(task.getName());
        }
    }
}
