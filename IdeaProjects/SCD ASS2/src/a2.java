import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class a2 extends JFrame {
    private JTextField resourceNameField;
    private JTextField skillNameField;
    private JTextField experienceField;
    private DefaultListModel<String> resourceListModel;
    private JList<String> resourceList;
    private DefaultListModel<String> skillListModel;
    private JList<String> skillList;
    private Map<String, Map<String, Integer>> resourceSkillsMap;

    public a2() {
        setTitle("Task Allocation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        resourceNameField = new JTextField(20);
        skillNameField = new JTextField(10);
        experienceField = new JTextField(5);
        resourceListModel = new DefaultListModel<>();
        resourceList = new JList<>(resourceListModel);
        skillListModel = new DefaultListModel<>();
        skillList = new JList<>(skillListModel);
        resourceSkillsMap = new HashMap<>();

        JButton addResourceButton = new JButton("Add Resource");
        JButton addSkillButton = new JButton("Add Skill");

        JPanel resourcePanel = new JPanel();
        resourcePanel.setLayout(new FlowLayout());
        resourcePanel.add(new JLabel("Resource:"));
        resourcePanel.add(resourceNameField);
        resourcePanel.add(addResourceButton);

        JPanel skillPanel = new JPanel();
        skillPanel.setLayout(new FlowLayout());
        skillPanel.add(new JLabel("Skill:"));
        skillPanel.add(skillNameField);
        skillPanel.add(new JLabel("Experience:"));
        skillPanel.add(experienceField);
        skillPanel.add(addSkillButton);

        add(resourcePanel, BorderLayout.NORTH);
        add(new JScrollPane(resourceList), BorderLayout.WEST);
        add(new JScrollPane(skillList), BorderLayout.CENTER);
        add(skillPanel, BorderLayout.SOUTH);

        addResourceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addResource();
            }
        });

        addSkillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSkill();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void addResource() {
        String resourceName = resourceNameField.getText().trim();
        if (!resourceName.isEmpty() && !resourceSkillsMap.containsKey(resourceName)) {
            resourceListModel.addElement(resourceName);
            resourceSkillsMap.put(resourceName, new HashMap<>());
            resourceNameField.setText("");
        }
    }

    private void addSkill() {
        String selectedResource = resourceList.getSelectedValue();
        if (selectedResource != null) {
            String skillName = skillNameField.getText().trim();
            String experienceText = experienceField.getText().trim();
            if (!skillName.isEmpty() && !experienceText.isEmpty()) {
                int experience = Integer.parseInt(experienceText);
                skillListModel.addElement(selectedResource + ": Skill - " + skillName + ", Experience - " + experience);
                resourceSkillsMap.get(selectedResource).put(skillName, experience);
                skillNameField.setText("");
                experienceField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new a2().setVisible(true);
            }
        });
    }
}
