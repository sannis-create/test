//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Map;
//
//public class MatchingGUI {
//
//    private JFrame frame;
//    private JTextArea resultOutputArea;
//    private JButton matchButton;
//    private JComboBox<String> matchingStrategyCombo;
//    private List<DeveloperAllocator.Resource> resources;
//    private List<DeveloperAllocator.Task> tasks;
//
//    public MatchingGUI() throws IOException {
//        resources = new ArrayList<>();
//        tasks = new ArrayList<>();
//        frame = new JFrame("Resource Task Matcher");
//        frame.setSize(600, 400);
//        frame.setLayout(new FlowLayout());
//
//        resources = readResourcesFromFile("resources.txt");
//        tasks = readTasksFromFile("tasks.txt");
//
//        // Strategy Selector
//        matchingStrategyCombo = new JComboBox<>(new String[]{"Exact Match", "Skill Only Match"});
//        matchingStrategyCombo.setSize(10,2);
//        frame.add(new JLabel("Select Matching Strategy:\n"));
//        frame.add(matchingStrategyCombo);
//
//        // Result Area
//        resultOutputArea = new JTextArea(10, 40);
//        resultOutputArea.setEditable(false);
//        frame.add(new JScrollPane(resultOutputArea));
//
//        // Match Button
//        matchButton = new JButton("Match Tasks with Resources");
//        frame.add(matchButton);
//
//        matchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String selectedStrategy = (String) matchingStrategyCombo.getSelectedItem();
//                DeveloperAllocator.MatchingStrategy strategy = (selectedStrategy.equals("Exact Match")) ? new DeveloperAllocator.ExactMatch() : new DeveloperAllocator.SkillOnlyMatch();
//
//                StringBuilder result = new StringBuilder();
//                for (DeveloperAllocator.Task task : tasks) {
//                    for (DeveloperAllocator.Resource resource : resources) {
//                        if (strategy.isMatch(resource, task)) {
//                            result.append(task.getName()).append(" matched with ").append(resource.getName()).append("\n");
//                        }
//                    }
//                }
//                resultOutputArea.setText(result.toString());
//            }
//        });
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    private List<DeveloperAllocator.Resource> readResourcesFromFile(String filename) throws IOException
//    {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(", ");
//                String resourceName = parts[0].trim();
//
//                List<DeveloperAllocator.Skill> skills = new ArrayList<>();
//                for (int i = 1; i < parts.length; i++) {
//                    String[] skillData = parts[i].split(":");
//                    String skillName = skillData[0].trim();
//                    int experienceLevel = Integer.parseInt(skillData[1].trim());
//
//                    skills.add(new DeveloperAllocator.Skill(skillName, experienceLevel));
//                }
//
//                resources.add(new DeveloperAllocator.Resource(resourceName, skills));
//            }
//        }
//
//        return resources;
//    }
//
//
//    private List<DeveloperAllocator.Task> readTasksFromFile(String filename) throws IOException {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(", ");
//                String taskName = parts[0].trim();
//
//                Map<String, String> taskSkills = new HashMap<>();
//                for (int i = 1; i < parts.length; i++) {
//                    String[] skillData = parts[i].split(":");
//                    String skillName = skillData[0].trim();
//                    String experienceLevel = skillData[1].trim();
//
//                    taskSkills.put(skillName, experienceLevel);
//                }
//
//                tasks.add(new DeveloperAllocator.Task(taskName, taskSkills));
//            }
//        }
//        return tasks;
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    new MatchingGUI();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }
//}
