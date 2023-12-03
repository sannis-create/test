import javax.swing.*;
import java.awt.*;

public class SkillMatchingForm extends JFrame {
    private DefaultListModel<String> taskModel;
    private JList<String> taskList;
    private DefaultListModel<String> developerModel;
    private JList<String> developerList;
    private JRadioButton exactMatchRdButton;
    private JRadioButton skillOnlyMatchRdButton;

    public SkillMatchingForm() {
        setTitle("Skill Matching Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Task Panel
        JPanel taskPanel = new JPanel(new BorderLayout());
        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Developer Panel
        JPanel developerPanel = new JPanel(new BorderLayout());
        developerModel = new DefaultListModel<>();
        developerList = new JList<>(developerModel);
        developerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        developerPanel.add(new JScrollPane(developerList), BorderLayout.CENTER);

        // Match Options Panel
        JPanel matchOptionsPanel = new JPanel(new FlowLayout());
        exactMatchRdButton = new JRadioButton("Exact Match", true);
        skillOnlyMatchRdButton = new JRadioButton("Skill-only Match");
        ButtonGroup matchGroup = new ButtonGroup();
        matchGroup.add(exactMatchRdButton);
        matchGroup.add(skillOnlyMatchRdButton);
        matchOptionsPanel.add(new JLabel("Matching Strategy:"));
        matchOptionsPanel.add(exactMatchRdButton);
        matchOptionsPanel.add(skillOnlyMatchRdButton);

        // Generate Button
        JButton generateButton = new JButton("Generate");
        generateButton.addActionListener(e -> generateMatches());

        // Add Panels to the Frame
        add(taskPanel, BorderLayout.WEST);
        add(developerPanel, BorderLayout.EAST);
        add(matchOptionsPanel, BorderLayout.NORTH);
        add(generateButton, BorderLayout.SOUTH);

        // Show the Form
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateMatches() {
        String selectedTask = taskList.getSelectedValue();
        String selectedDeveloper = developerList.getSelectedValue();

        if (selectedTask != null && selectedDeveloper != null) {
            String matchingStrategy = exactMatchRdButton.isSelected() ? "Exact Match" : "Skill-only Match";
            JOptionPane.showMessageDialog(this, "Matching Strategy: " + matchingStrategy +
                    "\nTask: " + selectedTask + "\nDeveloper: " + selectedDeveloper, "Match Results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a task and a developer", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SkillMatchingForm::new);
    }
}
