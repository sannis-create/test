import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TaskAllocatorGUI extends JFrame {
    private JTextField resourceTextField;
    private JTextField taskTextField;
    private JTextArea resultTextArea;
    private List<DeveloperTaskAllocator.Resource> developers;
    private List<DeveloperTaskAllocator.Task> tasks;

    public TaskAllocatorGUI(List<DeveloperTaskAllocator.Resource> developers, List<DeveloperTaskAllocator.Task> tasks) {
        this.developers = developers;
        this.tasks = tasks;

        // Set up the JFrame
        setTitle("Task Allocator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panels
        JPanel inputPanel = new JPanel();
        JPanel resultPanel = new JPanel();

        // Create components
        JLabel resourceLabel = new JLabel("Resource:");
        JLabel taskLabel = new JLabel("Task:");
        resourceTextField = new JTextField(20);
        taskTextField = new JTextField(20);
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);

        // Create Allocate button
        JButton allocateButton = new JButton("Allocate Tasks");
        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user input from resourceTextField and taskTextField
                String resourceInput = resourceTextField.getText();
                String taskInput = taskTextField.getText();

                // Perform allocation using existing logic
                String allocationResult = allocateTasks(resourceInput, taskInput);

                // Display results in resultTextArea
                resultTextArea.setText(allocationResult);
            }
        });

        // Add components to panels
        inputPanel.add(resourceLabel);
        inputPanel.add(resourceTextField);
        inputPanel.add(taskLabel);
        inputPanel.add(taskTextField);
        inputPanel.add(allocateButton);

        resultPanel.add(resultTextArea);

        // Add panels to the JFrame
        add(inputPanel);
        add(resultPanel);

        // Set layout manager (e.g., GridLayout or BorderLayout) for the JFrame
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    }

    private String allocateTasks(String resourceInput, String taskInput) {
        // Parse resource and task inputs
        DeveloperTaskAllocator.Resource newResource = parseResource(resourceInput);
        DeveloperTaskAllocator.Task newTask = parseTask(taskInput);

        // Allocate tasks using existing logic
        DeveloperTaskAllocator.MatchingStrategy skillOnlyMatchStrategy = new DeveloperTaskAllocator.SkillOnlyMatch();
        DeveloperTaskAllocator.MatchingStrategy exactMatchStrategy = new DeveloperTaskAllocator.ExactMatch();

        StringBuilder result = new StringBuilder("Results:\n");

        // Allocate tasks based on skill match
        result.append("Based on Skill, Could be allocated to Developer(s):\n");
        for (DeveloperTaskAllocator.Resource dev : developers) {
            if (skillOnlyMatchStrategy.isMatch(dev, newTask)) {
                result.append("- ").append(dev.getName()).append("\n");
            }
        }

        // Allocate tasks based on expertise match
        result.append("Based on Expertise, Could be allocated to Developer(s):\n");
        for (DeveloperTaskAllocator.Resource dev : developers) {
            if (exactMatchStrategy.isMatch(dev, newTask)) {
                result.append("- ").append(dev.getName()).append("\n");
            }
        }

        return result.toString();
    }

    private DeveloperTaskAllocator.Resource parseResource(String resourceInput) {
        // Implement parsing logic for resource input if needed
        // For now, assuming resourceInput is in the format "Name | skill:exp, skill:exp, ..."
        String[] parts = resourceInput.split("\\|");
        String name = parts[0].trim();
        DeveloperTaskAllocator.Resource resource = new DeveloperTaskAllocator.Resource(name);

        if (parts.length > 1) {
            String[] skills = parts[1].split(",");
            for (String skill : skills) {
                String[] skillParts = skill.split(":");
                if (skillParts.length == 2) {
                    String skillName = skillParts[0].trim();
                    int experience = Integer.parseInt(skillParts[1].trim());
                    resource.addSkill(new DeveloperTaskAllocator.Skill(skillName, experience));
                }
            }
        }

        return resource;
    }

    private DeveloperTaskAllocator.Task parseTask(String taskInput) {
        // Implement parsing logic for task input if needed
        // For now, assuming taskInput is in the format "TaskName | skill:level, skill:level, ..."
        String[] parts = taskInput.split("\\|");
        String name = parts[0].trim();
        DeveloperTaskAllocator.Task task = new DeveloperTaskAllocator.Task(name);

        if (parts.length > 1) {
            String[] skills = parts[1].split(",");
            for (String skill : skills) {
                String[] skillParts = skill.split(":");
                if (skillParts.length == 2) {
                    String skillName = skillParts[0].trim();
                    String level = skillParts[1].trim();
                    task.addMustSkill(skillName, level);
                }
            }
        }

        return task;
    }

    public static void main(String[] args) {
        // Initialize developers and tasks (you may read them from files or use your existing code)
        List<DeveloperTaskAllocator.Resource> developers = new ArrayList<>(); // Initialize with your data
        List<DeveloperTaskAllocator.Task> tasks = new ArrayList<>(); // Initialize with your data

        // Example data (replace this with your actual data)
        DeveloperTaskAllocator.Resource dev1 = new DeveloperTaskAllocator.Resource("Ahmed");
        dev1.addSkill(new DeveloperTaskAllocator.Skill("c", 2));
        dev1.addSkill(new DeveloperTaskAllocator.Skill("c++", 3));
        dev1.addSkill(new DeveloperTaskAllocator.Skill("java", 1));

        DeveloperTaskAllocator.Resource dev2 = new DeveloperTaskAllocator.Resource("Ayesha");
        dev2.addSkill(new DeveloperTaskAllocator.Skill("c", 2));
        dev2.addSkill(new DeveloperTaskAllocator.Skill("c++", 2));
        dev2.addSkill(new DeveloperTaskAllocator.Skill("assembly", 2));

        developers.add(dev1);
        developers.add(dev2);

        DeveloperTaskAllocator.Task task1 = new DeveloperTaskAllocator.Task("Web Development");
        task1.addMustSkill("javascript", "e");
        task1.addMustSkill("java", "i");

        DeveloperTaskAllocator.Task task2 = new DeveloperTaskAllocator.Task("Data Analytics");
        task2.addMustSkill("python", "e");
        task2.addMustSkill("javascript", "i");

        tasks.add(task1);
        tasks.add(task2);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskAllocatorGUI(developers, tasks).setVisible(true);
            }
        });
    }

}
