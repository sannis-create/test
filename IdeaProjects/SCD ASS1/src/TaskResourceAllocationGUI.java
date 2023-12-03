import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TaskResourceAllocationGUI extends JFrame {
    private JTextField resourceNameField;
    private JList<String> resourceList;
    private DefaultListModel<String> resourceListModel;

    private JTextField skillField;
    private JTextField experienceField;
    private JList<String> skillList;
    private DefaultListModel<String> skillListModel;

    private JTextField taskNameField;
    private JList<String> taskList;
    private DefaultListModel<String> taskListModel;

    private JComboBox<String> skillLevelComboBox;

    private JTextArea resultTextArea;
    private JRadioButton exactMatchRadioButton;
    private JRadioButton skillOnlyMatchRadioButton;

    private List<DeveloperTaskAllocator.Resource> resources;
    private List<DeveloperTaskAllocator.Task> tasks;

    public TaskResourceAllocationGUI() {
        // Initialize resources and tasks lists
        resources = new ArrayList<>();
        tasks = new ArrayList<>();

        // Set up the main frame
        setTitle("Task and Resource Allocation");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and add components
        createResourcePanel();
        createSkillPanel();
        createTaskPanel();
        createMatchingPanel();

        // Create result text area
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultTextArea);

        // Create the Generate button
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateMatchingResult();
            }
        });

        // Add components to the main frame
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(resultScrollPane, BorderLayout.CENTER);
        mainPanel.add(generateButton, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void createResourcePanel() {
        // Create and configure the resource panel components
        JPanel resourcePanel = new JPanel(new BorderLayout());
        resourceNameField = new JTextField(20);
        JButton addResourceButton = new JButton("Add");
        resourceListModel = new DefaultListModel<>();
        resourceList = new JList<>(resourceListModel);

        addResourceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addResource();
            }
        });

        // Add components to the resource panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Resource: "));
        inputPanel.add(resourceNameField);
        inputPanel.add(addResourceButton);

        resourcePanel.add(inputPanel, BorderLayout.NORTH);
        resourcePanel.add(new JScrollPane(resourceList), BorderLayout.CENTER);

        // Add the resource panel to the main frame
        add(resourcePanel, BorderLayout.WEST);
    }

    private void createSkillPanel() {
        // Create and configure the skill panel components
        JPanel skillPanel = new JPanel(new BorderLayout());
        skillField = new JTextField(15);
        experienceField = new JTextField(5);
        JButton addSkillButton = new JButton("Add");
        skillListModel = new DefaultListModel<>();
        skillList = new JList<>(skillListModel);

        addSkillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSkill();
            }
        });

        // Add components to the skill panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Skill: "));
        inputPanel.add(skillField);
        inputPanel.add(new JLabel("Experience: "));
        inputPanel.add(experienceField);
        inputPanel.add(addSkillButton);

        skillPanel.add(inputPanel, BorderLayout.NORTH);
        skillPanel.add(new JScrollPane(skillList), BorderLayout.CENTER);

        // Add the skill panel to the main frame
        add(skillPanel, BorderLayout.CENTER);
    }

    private void createTaskPanel() {
        // Create and configure the task panel components
        JPanel taskPanel = new JPanel(new BorderLayout());
        taskNameField = new JTextField(20);
        JButton addTaskButton = new JButton("Add");
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        // Create the skill level combo box
        String[] skillLevels = {"Beginner", "Intermediate", "Expert"};
        skillLevelComboBox = new JComboBox<>(skillLevels);

        // Add components to the task panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Task: "));
        inputPanel.add(taskNameField);
        inputPanel.add(addTaskButton);

        taskPanel.add(inputPanel, BorderLayout.NORTH);
        taskPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Add the task panel to the main frame
        add(taskPanel, BorderLayout.EAST);
    }

    private void createMatchingPanel() {
        // Create and configure the matching panel components
        JPanel matchingPanel = new JPanel(new BorderLayout());

        JPanel radioPanel = new JPanel(new FlowLayout());
        exactMatchRadioButton = new JRadioButton("Exact Match");
        skillOnlyMatchRadioButton = new JRadioButton("Skill-only Match");
        ButtonGroup matchingGroup = new ButtonGroup();
        matchingGroup.add(exactMatchRadioButton);
        matchingGroup.add(skillOnlyMatchRadioButton);
        exactMatchRadioButton.setSelected(true);

        radioPanel.add(exactMatchRadioButton);
        radioPanel.add(skillOnlyMatchRadioButton);

        // Add components to the matching panel
        matchingPanel.add(radioPanel, BorderLayout.NORTH);

        // Add the matching panel to the main frame
        add(matchingPanel, BorderLayout.SOUTH);
    }

    private void addResource() {
        String resourceName = resourceNameField.getText().trim();
        if (!resourceName.isEmpty()) {
            DeveloperTaskAllocator.Resource resource = new DeveloperTaskAllocator.Resource(resourceName);
            resources.add(resource);
            resourceListModel.addElement(resourceName);
            resourceNameField.setText("");
        }
    }

    private void addSkill() {
        String skillName = skillField.getText().trim();
        String experienceText = experienceField.getText().trim();
        if (!skillName.isEmpty() && !experienceText.isEmpty()) {
            int experience = Integer.parseInt(experienceText);
            DeveloperTaskAllocator.Skill skill = new DeveloperTaskAllocator.Skill(skillName, experience);
            skillListModel.addElement(skillName + " " + experience);
            skillField.setText("");
            experienceField.setText("");
        }
    }

    private void addTask() {
        String taskName = taskNameField.getText().trim();
        if (!taskName.isEmpty()) {
            DeveloperTaskAllocator.Task task = new DeveloperTaskAllocator.Task(taskName);
            tasks.add(task);
            taskListModel.addElement(taskName);
            taskNameField.setText("");
        }
    }

    private void generateMatchingResult() {
        resultTextArea.setText(""); // Clear the result area

        DeveloperTaskAllocator.MatchingStrategy matchingStrategy;
        if (exactMatchRadioButton.isSelected()) {
            matchingStrategy = new DeveloperTaskAllocator.ExactMatch();
        } else {
            matchingStrategy = new DeveloperTaskAllocator.SkillOnlyMatch();
        }

        for (DeveloperTaskAllocator.Task task : tasks) {
            StringBuilder resultText = new StringBuilder(task.getName() + "\n");
            for (DeveloperTaskAllocator.Resource resource : resources) {
                if (matchingStrategy.isMatch(resource, task)) {
                    resultText.append(resource.getName()).append("\n");
                }
            }
            resultText.append("\n");
            resultTextArea.append(resultText.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TaskResourceAllocationGUI gui = new TaskResourceAllocationGUI();
                gui.setVisible(true);
            }
        });
    }
}
