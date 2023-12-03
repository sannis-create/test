//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ResourceManagementGUI extends JFrame {
//    private static List<Resource> resources = new ArrayList<>();
//
//    private JTextField resourceNameField;
//    private JTextField skillNameField;
//    private JTextField experienceField;
//    private JTextArea resourceListArea;
//
//    public ResourceManagementGUI(List<Resource> resources) {
//        setTitle("Resource Management");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(400, 400);
//        setLayout(new BorderLayout());
//
//        JPanel addResourcePanel = createAddResourcePanel();
//        add(addResourcePanel, BorderLayout.NORTH);
//
//        resourceListArea = new JTextArea();
//        resourceListArea.setEditable(false);
//        JScrollPane scrollPane = new JScrollPane(resourceListArea);
//        add(scrollPane, BorderLayout.CENTER);
//    }
//
//    private JPanel createAddResourcePanel() {
//        JPanel panel = new JPanel(new GridLayout(3, 2));
//
//        JLabel resourceNameLabel = new JLabel("Resource Name:");
//        resourceNameField = new JTextField();
//        JLabel skillNameLabel = new JLabel("Skill Name:");
//        skillNameField = new JTextField();
//        JLabel experienceLabel = new JLabel("Experience (years):");
//        experienceField = new JTextField();
//
//        JButton addResourceButton = new JButton("Add Resource");
//        addResourceButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addResource();
//            }
//        });
//
//        panel.add(resourceNameLabel);
//        panel.add(resourceNameField);
//        panel.add(skillNameLabel);
//        panel.add(skillNameField);
//        panel.add(experienceLabel);
//        panel.add(experienceField);
//        panel.add(new JLabel()); // Empty label for spacing
//        panel.add(addResourceButton);
//
//        return panel;
//    }
//
//    private void addResource() {
//        String resourceName = resourceNameField.getText();
//        String skillName = skillNameField.getText();
//        int experience = Integer.parseInt(experienceField.getText());
//
//        Resource resource = findResourceByName(resourceName);
//        if (resource == null) {
//            resource = new Resource(resourceName);
//            resources.add(resource);
//        }
//
//        Skill skill = new Skill(skillName, experience);
//        resource.addSkill(skill);
//
//        updateResourceList();
//        clearFields();
//    }
//
//    private Resource findResourceByName(String name) {
//        for (Resource resource : resources) {
//            if (resource.getName().equalsIgnoreCase(name)) {
//                return resource;
//            }
//        }
//        return null;
//    }
//
//    public void updateResourceList() {
//        resourceListArea.setText("Resources:\n");
//        for (Resource resource : resources) {
//            resourceListArea.append(resource.getName() + ":\n");
//            for (Skill skill : resource.getSkills()) {
//                resourceListArea.append("  " + skill.getName() + " (Exp: " + skill.getExperienceYears() + " years)\n");
//            }
//        }
//    }
//
//    private void clearFields() {
//        resourceNameField.setText("");
//        skillNameField.setText("");
//        experienceField.setText("");
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                ResourceManagementGUI app = new ResourceManagementGUI(resources);
//                app.setVisible(true);
//            }
//        });
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ResourceManagementGUI extends JPanel {
    private List<Resource> resources;

    private JTextField resourceNameField;
    private DefaultListModel<String> resourceListModel;
    private JList<String> resourceList;

    public ResourceManagementGUI(List<Resource> resources) {
        this.resources = resources;

        setLayout(new BorderLayout());

        // Create resource input panel
        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Create resource list panel
        JPanel listPanel = createListPanel();
        add(listPanel, BorderLayout.CENTER);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JLabel nameLabel = new JLabel("Resource:");
        resourceNameField = new JTextField(20);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addResource();
            }
        });

        panel.add(nameLabel);
        panel.add(resourceNameField);
        panel.add(addButton);

        return panel;
    }

    private JPanel createListPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        resourceListModel = new DefaultListModel<>();
        resourceList = new JList<>(resourceListModel);
        JScrollPane scrollPane = new JScrollPane(resourceList);

        panel.add(new JLabel("Resource List:"), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void addResource() {
        String resourceName = resourceNameField.getText().trim();
        if (!resourceName.isEmpty()) {
            Resource resource = new Resource(resourceName);
            resources.add(resource);
            resourceListModel.addElement(resourceName);
            resourceNameField.setText("");
        }
    }

    public void updateResourceList() {
        resourceListModel.clear();
        for (Resource resource : resources) {
            resourceListModel.addElement(resource.getName());
        }
    }
}
