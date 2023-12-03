//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.*;
//import java.nio.file.*;
//import java.util.*;
//import java.util.List;
//
//public class ResourceManagementGUI {
//
//    private JFrame frame;
//    private JList<String> resourceList;
//    private DefaultListModel<String> resourceListModel;
//    private JTextArea skillsTextArea;
//    private JTextField resourceNameField, skillField, experienceField;
//    private JButton addButton, saveButton;
//    private Map<String, Map<String, Integer>> resourceData;
//
//    public ResourceManagementGUI() {
//        frame = new JFrame("Resource Management");
//        frame.setSize(600, 400);
//        frame.setLayout(new FlowLayout());
//
//        resourceListModel = new DefaultListModel<>();
//        resourceList = new JList<>(resourceListModel);
//        resourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//
//        JScrollPane listScrollPane = new JScrollPane(resourceList);
//        listScrollPane.setPreferredSize(new Dimension(150, 300));
//        frame.add(listScrollPane);
//
//        skillsTextArea = new JTextArea();
//        skillsTextArea.setEditable(false);
//        JScrollPane skillsScrollPane = new JScrollPane(skillsTextArea);
//        skillsScrollPane.setPreferredSize(new Dimension(200, 300));
//        frame.add(skillsScrollPane);
//
//        JLabel resourceNameLabel = new JLabel("Resource Name:");
//        frame.add(resourceNameLabel);
//        resourceNameField = new JTextField(20);
//        frame.add(resourceNameField);
//
//        JLabel skillLabel = new JLabel("Skill:");
//        frame.add(skillLabel);
//        skillField = new JTextField(20);
//        frame.add(skillField);
//
//        JLabel experienceLabel = new JLabel("Experience Level:");
//        frame.add(experienceLabel);
//        experienceField = new JTextField(5);
//        frame.add(experienceField);
//
//        addButton = new JButton("Add Skill");
//        frame.add(addButton);
//
//        saveButton = new JButton("Save Changes");
//        frame.add(saveButton);
//
//        resourceList.addListSelectionListener(e -> {
//            if (!e.getValueIsAdjusting()) {
//                String selectedResource = resourceList.getSelectedValue();
//                if (selectedResource != null && resourceData.containsKey(selectedResource)) {
//                    Map<String, Integer> skills = resourceData.get(selectedResource);
//                    StringBuilder sb = new StringBuilder();
//                    for (Map.Entry<String, Integer> entry : skills.entrySet()) {
//                        sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
//                    }
//                    skillsTextArea.setText(sb.toString());
//                }
//            }
//        });
//
//        addButton.addActionListener(e -> {
//            String resourceName = resourceNameField.getText().trim();
//            String skillName = skillField.getText().trim();
//            String experience = experienceField.getText().trim();
//
//            if (!resourceName.isEmpty() && !skillName.isEmpty() && !experience.isEmpty()) {
//                int experienceLevel;
//                try {
//                    experienceLevel = Integer.parseInt(experience);
//                    if (!resourceData.containsKey(resourceName)) {
//                        resourceData.put(resourceName, new HashMap<>());
//                        resourceListModel.addElement(resourceName);
//                    }
//                    resourceData.get(resourceName).put(skillName, experienceLevel);
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(frame, "Invalid experience level. Please enter a number.");
//                }
//            }
//        });
//
//        saveButton.addActionListener(e -> saveDataToFile());
//
//        loadDataFromFile();
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
//
//    private void loadDataFromFile() {
//        resourceData = new HashMap<>();
//
//        try {
//            List<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("resources.txt")));
//            for (String line : lines) {
//                String[] parts = line.split("\\|");
//                String resourceName = parts[0].trim();
//                resourceListModel.addElement(resourceName);
//                Map<String, Integer> skills = new HashMap<>();
//                for (String skillPair : parts[1].split(",")) {
//                    String[] skillParts = skillPair.split(":");
//                    skills.put(skillParts[0].trim(), Integer.parseInt(skillParts[1].trim()));
//                }
//                resourceData.put(resourceName, skills);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(frame, "Error reading from resources.txt.");
//        }
//    }
//
//    private void saveDataToFile() {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("resources.txt"));
//            for (Map.Entry<String, Map<String, Integer>> entry : resourceData.entrySet()) {
//                writer.write(entry.getKey() + " | ");
//                Map<String, Integer> skills = entry.getValue();
//                List<String> skillStrings = new ArrayList<>();
//
//                for (Map.Entry<String, Integer> skillEntry : skills.entrySet()) {
//                    skillStrings.add(skillEntry.getKey() + ":" + skillEntry.getValue());
//                }
//                writer.write(String.join(", ", skillStrings));
//                writer.newLine();
//            }
//            writer.close();
//            JOptionPane.showMessageDialog(frame, "Data saved to resources.txt.");
//        } catch (IOException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(frame, "Error writing to resources.txt.");
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new ResourceManagementGUI());
//    }
//}
//
