import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JFrame {
    private JButton addResourceButton;
    private JButton addTaskButton;
    private JButton generateMatchButton;

    public MainForm() {
        setTitle("Task Resource Matching System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addResourceButton = new JButton("Add Resource");
        addTaskButton = new JButton("Add Task");
        generateMatchButton = new JButton("Generate Match");

        addResourceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResourceForm resourceForm = new ResourceForm();
                resourceForm.setVisible(true);
            }
        });

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TaskForm taskForm = new TaskForm();
                taskForm.setVisible(true);
            }
        });

        generateMatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement code to perform skill matching based on strategies
                SkillMatchingForm skillMatchingForm = new SkillMatchingForm();
                skillMatchingForm.setVisible(true);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(addResourceButton);
        panel.add(addTaskButton);
        panel.add(generateMatchButton);

        add(panel, BorderLayout.CENTER);
    }
}
