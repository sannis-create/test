import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskForm extends JFrame {
    private JTextField taskNameField;
    private JTextField skillNameField;
    private JComboBox<String> skillLevelComboBox;
    private JButton addButton;
    private TaskResourceMatchingGUI mainForm;

    public TaskForm() { // TaskResourceMatchingGUI mainForm
        this.mainForm = mainForm;

        setTitle("Add Task");
        setSize(400, 150);
        setLocationRelativeTo(null);

        taskNameField = new JTextField(20);
        skillNameField = new JTextField(10);
        skillLevelComboBox = new JComboBox<>(new String[]{"Beginner", "Intermediate", "Expert"});
        addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Task:"));
        panel.add(taskNameField);
        panel.add(new JLabel("Skill:"));
        panel.add(skillNameField);
        panel.add(new JLabel("Level:"));
        panel.add(skillLevelComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addTask() {
        String taskName = taskNameField.getText().trim();
        String skillName = skillNameField.getText().trim();
        String skillLevel = (String) skillLevelComboBox.getSelectedItem();

        if (!taskName.isEmpty() && !skillName.isEmpty()) {
            // Create a new task with the provided information
            DeveloperTaskAllocator.Task task = new DeveloperTaskAllocator.Task(taskName);
            task.addMustSkill(skillName, skillLevel);

            // Add the task to the main form
            mainForm.addTask(task);

            // Close the task form
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
