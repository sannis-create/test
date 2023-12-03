import javax.swing.*;
import java.awt.*;

public class ResourceApplicantGUI {

    private JFrame frame;
    private JPanel panel;
    private JTextField nameField;
    private JTextField skillNameField;
    private JTextField experienceField;
    private JButton addButton;

    public ResourceApplicantGUI() {
        frame = new JFrame("Resource / Applicant Form");
        panel = new JPanel(new GridLayout(4, 2));

        // Create and add components
        panel.add(new JLabel("Name:"));
        nameField = new JTextField(15);
        panel.add(nameField);

        panel.add(new JLabel("Skill Name:"));
        skillNameField = new JTextField(15);
        panel.add(skillNameField);

        panel.add(new JLabel("Years of Experience:"));
        experienceField = new JTextField(15);
        panel.add(experienceField);

        addButton = new JButton("Add Resource");
        panel.add(new JLabel()); // empty label for spacing
        panel.add(addButton);

        // Action listener for addButton
        addButton.addActionListener(e -> {
            // You can handle the action here (e.g., save the details)
            String name = nameField.getText();
            String skillName = skillNameField.getText();
            String experience = experienceField.getText();

            System.out.println("Name: " + name);
            System.out.println("Skill Name: " + skillName);
            System.out.println("Years of Experience: " + experience);
        });

        frame.add(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResourceApplicantGUI());
    }
}
