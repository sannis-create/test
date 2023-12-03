import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class TaskResourceMatchingGUI extends JFrame {
    private DefaultListModel<String> resultModel;
    private JList<String> resultList;
    private JButton generateButton;

    private List<DeveloperTaskAllocator.Resource> developers;
    private List<DeveloperTaskAllocator.Task> tasks;

    public TaskResourceMatchingGUI() {
        setTitle("Task Resource Matching");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        developers = new ArrayList<>();
        tasks = new ArrayList<DeveloperTaskAllocator.Task>();
        resultModel = new DefaultListModel<>();
        resultList = new JList<>(resultModel);
        generateButton = new JButton("Generate");

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform skill matching here and display the results
                resultModel.clear(); // Clear previous results

                DeveloperTaskAllocator.MatchingStrategy skillOnlyMatchStrategy = new DeveloperTaskAllocator.SkillOnlyMatch();
                DeveloperTaskAllocator.MatchingStrategy exactMatchStrategy = new DeveloperTaskAllocator.ExactMatch();

                for (DeveloperTaskAllocator.Task task : tasks) {
                    resultModel.addElement("Task: " + task.getName());

                    resultModel.addElement("Based on Skill, Could be allocated to Developer(s):");
                    for (DeveloperTaskAllocator.Resource dev : developers) {
                        if (skillOnlyMatchStrategy.isMatch(dev, task)) {
                            resultModel.addElement("- " + dev.getName());
                        }
                    }

                    resultModel.addElement("Based on Expertise, Could be allocated to Developer(s):");
                    for (DeveloperTaskAllocator.Resource dev : developers) {
                        if (exactMatchStrategy.isMatch(dev, task)) {
                            resultModel.addElement("- " + dev.getName());
                        }
                    }
                    resultModel.addElement(""); // Add an empty line for separation
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(resultList), BorderLayout.CENTER);
        panel.add(generateButton, BorderLayout.SOUTH);

        add(panel);
    }

    public void addResource(DeveloperTaskAllocator.Resource resource) {
        developers.add(resource);
    }

    public void addTask(DeveloperTaskAllocator.Task task) {
        tasks.add(task);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TaskResourceMatchingGUI mainForm = new TaskResourceMatchingGUI();
            mainForm.setVisible(true);
        });
    }
}
