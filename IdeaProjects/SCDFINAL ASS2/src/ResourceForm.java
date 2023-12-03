import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResourceForm extends JFrame {
    private JTextField resourceNameField;
    private JTextField skillNameField;
    private JTextField experienceField;
    private JButton addButton;
    private TaskResourceMatchingGUI mainForm;

    public ResourceForm() { //TaskResourceMatchingGUI mainForm
        this.mainForm = mainForm;

        setTitle("Add Resource");
        setSize(400, 150);
        setLocationRelativeTo(null);

        resourceNameField = new JTextField(20);
        skillNameField = new JTextField(10);
        experienceField = new JTextField(10);
        addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addResource();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Resource:"));
        panel.add(resourceNameField);
        panel.add(new JLabel("Skill:"));
        panel.add(skillNameField);
        panel.add(new JLabel("Experience:"));
        panel.add(experienceField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addResource() {
        String resourceName = resourceNameField.getText().trim();
        String skillName = skillNameField.getText().trim();
        int experience = Integer.parseInt(experienceField.getText().trim());

        if (!resourceName.isEmpty() && !skillName.isEmpty()) {
            // Create a new resource with the provided information
            DeveloperTaskAllocator.Resource resource = new DeveloperTaskAllocator.Resource(resourceName);
            DeveloperTaskAllocator.Skill skill = new DeveloperTaskAllocator.Skill(skillName, experience);
            resource.addSkill(skill);

            // Add the resource to the main form
            mainForm.addResource(resource);

            // Close the resource form
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
