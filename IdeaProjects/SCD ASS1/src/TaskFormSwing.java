//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class TaskFormSwing extends JFrame {
//    private List<Task> tasks;
//    private List<ResourceFormSwing.Resource> resources;
//    private JTextField taskTextField;
//    private DefaultListModel<String> taskListModel;
//    private JList<String> taskList;
//
//    private JTextField skillNameTextField;
//    private JComboBox<String> skillLevelComboBox;
//    private JButton addSkillButton;
//
//    public TaskFormSwing() {
//        tasks = new ArrayList<>();
//
//        initComponents();
//    }
//
//    private void initComponents() {
//        setTitle("Task Form");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new GridLayout(2, 2));
//
//        // Task Panel
//        JPanel taskPanel = new JPanel(new BorderLayout());
//
//        taskListModel = new DefaultListModel<>();
//        taskList = new JList<>(taskListModel);
//        JScrollPane taskScrollPane = new JScrollPane(taskList);
//        taskPanel.add(taskScrollPane, BorderLayout.CENTER);
//
//        taskTextField = new JTextField();
//        taskPanel.add(taskTextField, BorderLayout.PAGE_END);
//
//        JButton addTaskButton = new JButton("Add Task");
//        addTaskButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addTask();
//            }
//        });
//        taskPanel.add(addTaskButton, BorderLayout.LINE_END);
//
//        add(taskPanel);
//
//        // Skill Panel
//        JPanel skillPanel = new JPanel(new BorderLayout());
//
//        skillNameTextField = new JTextField();
//        skillLevelComboBox = new JComboBox<>(new String[]{"Beginner", "Intermediate", "Expert"});
//        addSkillButton = new JButton("Add Skill");
//        addSkillButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addSkill();
//            }
//        });
//
//        skillPanel.add(skillNameTextField, BorderLayout.CENTER);
//        skillPanel.add(skillLevelComboBox, BorderLayout.LINE_END);
//        skillPanel.add(addSkillButton, BorderLayout.PAGE_END);
//
//        add(skillPanel);
//
//        pack();
//    }
//
//    private void addTask() {
//        String taskName = taskTextField.getText().trim();
//        if (!taskName.isEmpty()) {
//            Task task = new Task(taskName);
//            tasks.add(task);
//            taskListModel.addElement(taskName);
//            taskTextField.setText("");
//        }
//    }
//
//    private void addSkill() {
//        int selectedIndex = taskList.getSelectedIndex();
//        if (selectedIndex >= 0) {
//            String skillName = skillNameTextField.getText().trim();
//            String skillLevel = (String) skillLevelComboBox.getSelectedItem();
//
//            if (!skillName.isEmpty()) {
//                tasks.get(selectedIndex).addMustSkill(skillName, getExpLevel(skillLevel));
//                skillNameTextField.setText("");
//            }
//        }
//    }
//
//    private String getExpLevel(String level) {
//        switch (level) {
//            case "Beginner":
//                return "b";
//            case "Intermediate":
//                return "i";
//            case "Expert":
//                return "e";
//            default:
//                return "";
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            TaskFormSwing taskFormSwing = new TaskFormSwing();
//            taskFormSwing.setVisible(true);
//        });
//    }
//
//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setResources(List<ResourceFormSwing.Resource> resources) {
//        this.resources = resources;
//    }
//
//    public List<ResourceFormSwing.Resource> getResources() {
//        return resources;
//    }
//
//    public static class Task extends DeveloperTaskAllocator.Task {
//        private final String name;
//        private Map<String, String> mustHaveSkills;
//
//        public Task(String name) {
//            this.name = name;
//            this.mustHaveSkills = new HashMap<>();
//        }
//
//        public void addMustSkill(String skillName, String level) {
//            mustHaveSkills.put(skillName, level);
//        }
//
//        @Override
//        public String toString() {
//            return name;
//        }
//
//        public String getName()
//        {
//            return this.name;
//        }
//    }
//}
