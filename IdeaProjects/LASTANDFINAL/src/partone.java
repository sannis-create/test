import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class partone extends JFrame {
    private DefaultListModel<String> resourceListModel;
    private JList<String> resourceList;
    private JTextField resourceNameTextField;

    private DefaultListModel<String> skillListModel;
    private JList<String> skillList;
    private JTextField skillNameTextField;
    private JTextField experienceTextField;

    private Map<String, List<Skill>> resourcesMap;

    public partone() {
        setTitle("Resource Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        resourcesMap = new HashMap<>();

        // Resource Panel
        JPanel resourcePanel = new JPanel(new BorderLayout());
        resourceListModel = new DefaultListModel<>();
        resourceList = new JList<>(resourceListModel);
        resourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resourceList.addListSelectionListener(e -> displaySkills());
        resourcePanel.add(new JScrollPane(resourceList), BorderLayout.CENTER);

        JPanel addResourcePanel = new JPanel(new FlowLayout());
        resourceNameTextField = new JTextField(15);
        JButton addResourceButton = new JButton("Add Resource");
        addResourceButton.addActionListener(e -> addResource());
        addResourcePanel.add(new JLabel("Resource:"));
        addResourcePanel.add(resourceNameTextField);
        addResourcePanel.add(addResourceButton);

        resourcePanel.add(addResourcePanel, BorderLayout.SOUTH);

        // Skill Panel
        JPanel skillPanel = new JPanel(new BorderLayout());
        skillListModel = new DefaultListModel<>();
        skillList = new JList<>(skillListModel);
        skillList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        skillPanel.add(new JScrollPane(skillList), BorderLayout.CENTER);

        JPanel addSkillPanel = new JPanel(new FlowLayout());
        skillNameTextField = new JTextField(10);
        experienceTextField = new JTextField(5);
        JButton addSkillButton = new JButton("Add Skill");
        addSkillButton.addActionListener(e -> addSkill());
        addSkillPanel.add(new JLabel("Skill:"));
        addSkillPanel.add(skillNameTextField);
        addSkillPanel.add(new JLabel("Experience:"));
        addSkillPanel.add(experienceTextField);
        addSkillPanel.add(addSkillButton);

        skillPanel.add(addSkillPanel, BorderLayout.SOUTH);

        // Add Panels to the Frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, resourcePanel, skillPanel);
        splitPane.setResizeWeight(0.5);
        add(splitPane);

        // Show the Form
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addResource() {
        String resourceName = resourceNameTextField.getText().trim();
        if (!resourceName.isEmpty()) {
            resourceListModel.addElement(resourceName);
            resourceNameTextField.setText("enter resource");
            resourcesMap.put(resourceName, new ArrayList<>());
        }
    }

    private void addSkill() {
        String selectedResource = resourceList.getSelectedValue();
        if (selectedResource != null) {
            String skillName = skillNameTextField.getText().trim();
            String experienceStr = experienceTextField.getText().trim();

            if (!skillName.isEmpty() && !experienceStr.isEmpty()) {
                int experience = Integer.parseInt(experienceStr);
                Skill skill = new Skill(skillName, experience);

                List<Skill> skills = resourcesMap.get(selectedResource);
                skills.add(skill);

                updateSkillList(selectedResource);
                skillNameTextField.setText("");
                experienceTextField.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a resource", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displaySkills() {
        String selectedResource = resourceList.getSelectedValue();
        if (selectedResource != null) {
            updateSkillList(selectedResource);
        }
    }

    private void updateSkillList(String resource) {
        List<Skill> skills = resourcesMap.get(resource);
        skillListModel.clear();
        if (skills != null) {
            for (Skill skill : skills) {
                skillListModel.addElement(skill.getName() + " " + skill.getExp());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(partone::new);
    }

    private static class Skill {
        private final String name;
        private final int exp;

        public Skill(String name, int exp) {
            this.name = name;
            this.exp = exp;
        }

        public String getName() {
            return name;
        }

        public int getExp() {
            return exp;
        }
    }
    class Resource {
        private String name;
        private List<Skill> skills;

        public Resource(String name) {
            this.name = name;
            this.skills = new ArrayList<>();
        }

        public void addSkill(Skill skill) {
            skills.add(skill);
        }

        public boolean hasSkill(String skillName, int requiredExperience) {
            for (Skill skill : skills) {
                if (skill.getName().equals(skillName) && skill.getExp() >= requiredExperience) {
                    return true;
                }
            }
            return false;
        }

        public String getName() {
            return name;
        }

        public List<Skill> getSkills()
        {
            return this.skills;
        }
    }

    class Task {
        private String name;
        private Map<String, Character> skillRequirements;

        public Task(String name) {
            this.name = name;
            this.skillRequirements = new HashMap<>();
        }

        public void addSkillRequirement(String skillName, char level) {
            skillRequirements.put(skillName, level);
        }

        public String getName() {
            return name;
        }

        public Map<String, Character> getSkillRequirements() {
            return skillRequirements;
        }
    }
}
