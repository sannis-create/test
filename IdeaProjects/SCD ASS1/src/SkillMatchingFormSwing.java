//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class SkillMatchingFormSwing extends JFrame {
//    private List<ResourceFormSwing.Resource> resources;
//    private List<TaskFormSwing.Task> tasks;
//
//    private JRadioButton exactMatchRadioButton;
//    private JRadioButton skillOnlyMatchRadioButton;
//    private JButton generateButton;
//
//    public SkillMatchingFormSwing(List<ResourceFormSwing.Resource> resources, List<TaskFormSwing.Task> tasks) {
//        this.resources = resources;
//        this.tasks = tasks;
//
//        initComponents();
//    }
//
//    private void initComponents() {
//        setTitle("Skill Matching Form");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new GridLayout(3, 1));
//
//        // Matching Strategy Panel
//        JPanel strategyPanel = new JPanel(new FlowLayout());
//
//        exactMatchRadioButton = new JRadioButton("Exact Match");
//        skillOnlyMatchRadioButton = new JRadioButton("Skill-only Match");
//
//        ButtonGroup buttonGroup = new ButtonGroup();
//        buttonGroup.add(exactMatchRadioButton);
//        buttonGroup.add(skillOnlyMatchRadioButton);
//
//        strategyPanel.add(exactMatchRadioButton);
//        strategyPanel.add(skillOnlyMatchRadioButton);
//
//        add(strategyPanel);
//
//        // Generate Button Panel
//        JPanel generatePanel = new JPanel(new FlowLayout());
//
//        generateButton = new JButton("Generate");
//        generateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                generateMatching();
//            }
//        });
//
//        generatePanel.add(generateButton);
//
//        add(generatePanel);
//
//        pack();
//    }
//
//    private void generateMatching() {
//        boolean isExactMatch = exactMatchRadioButton.isSelected();
//        boolean isSkillOnlyMatch = skillOnlyMatchRadioButton.isSelected();
//
//        if (isExactMatch || isSkillOnlyMatch) {
//            DeveloperTaskAllocator.MatchingStrategy matchingStrategy;
//            if (isExactMatch) {
//                matchingStrategy = new DeveloperTaskAllocator.ExactMatch();
//            } else {
//                matchingStrategy = new DeveloperTaskAllocator.SkillOnlyMatch();
//            }
//
//            for (TaskFormSwing.Task task : tasks) {
//                System.out.println("Task |  " + task.getName());
//                System.out.println("Based on Strategy, Could be allocated to Developer(s):");
//
//                for (DeveloperTaskAllocator.Resource resource : resources) {
//                    if (matchingStrategy.isMatch(resource, task)) {
//                        System.out.println("- " + resource.getName());
//                    }
//                }
//
//                System.out.println('\n');
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Please select a matching strategy.");
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            // For testing purposes, you can create instances of ResourceFormSwing and TaskFormSwing
//            // and pass their resources and tasks to SkillMatchingFormSwing constructor.
//            ResourceFormSwing resourceFormSwing = new ResourceFormSwing();
//            resourceFormSwing.setVisible(true);
//
//            TaskFormSwing taskFormSwing = new TaskFormSwing();
//            taskFormSwing.setVisible(true);
//
//            SkillMatchingFormSwing skillMatchingFormSwing =
//                    new SkillMatchingFormSwing(resourceFormSwing.getResources(), taskFormSwing.getTasks());
//            skillMatchingFormSwing.setVisible(true);
//        });
//    }
//}
