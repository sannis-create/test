import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillMatchingForm extends JFrame {
    private JRadioButton exactMatchRadioButton;
    private JRadioButton skillOnlyMatchRadioButton;
    private JButton generateButton;

    public SkillMatchingForm() {
        setTitle("Skill Matching");
        setSize(400, 200);
        setLocationRelativeTo(null);

        exactMatchRadioButton = new JRadioButton("Exact Match");
        skillOnlyMatchRadioButton = new JRadioButton("Skill-only Match");
        generateButton = new JButton("Generate");

        ButtonGroup matchingGroup = new ButtonGroup();
        matchingGroup.add(exactMatchRadioButton);
        matchingGroup.add(skillOnlyMatchRadioButton);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement code to perform skill matching based on the selected strategy
                // Display the results in a dialog or on the main form
                dispose(); // Close the skill matching form
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(exactMatchRadioButton);
        panel.add(skillOnlyMatchRadioButton);
        panel.add(generateButton);

        add(panel, BorderLayout.CENTER);
    }
}
