//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ResourceFormSwing extends JFrame {
//    private List<Resource> resources;
//
//    private JTextField resourceTextField;
//    private DefaultListModel<String> resourceListModel;
//    private JList<String> resourceList;
//
//    private JTextField skillNameTextField;
//    private JTextField skillExpTextField;
//    private JButton addSkillButton;
//
//    public ResourceFormSwing() {
//        resources = new ArrayList<>();
//        initComponents();
//    }
//
//    private void initComponents() {
//        setTitle("Resource Form");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new GridLayout(2, 2));
//
//        // Resource Panel
//        JPanel resourcePanel = new JPanel(new BorderLayout());
//
//        resourceListModel = new DefaultListModel<>();
//        resourceList = new JList<>(resourceListModel);
//        JScrollPane resourceScrollPane = new JScrollPane(resourceList);
//        resourcePanel.add(resourceScrollPane, BorderLayout.CENTER);
//
//        resourceTextField = new JTextField();
//        resourcePanel.add(resourceTextField, BorderLayout.PAGE_END);
//
//        JButton addResourceButton = new JButton("Add Resource");
//        addResourceButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addResource();
//            }
//        });
//        resourcePanel.add(addResourceButton, BorderLayout.LINE_END);
//
//        add(resourcePanel);
//
//        // Skill Panel
//        JPanel skillPanel = new JPanel(new BorderLayout());
//
//        skillNameTextField = new JTextField();
//        skillExpTextField = new JTextField();
//        addSkillButton = new JButton("Add Skill");
//        addSkillButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                addSkill();
//            }
//        });
//
//        skillPanel.add(skillNameTextField, BorderLayout.CENTER);
//        skillPanel.add(skillExpTextField, BorderLayout.LINE_END);
//        skillPanel.add(addSkillButton, BorderLayout.PAGE_END);
//
//        add(skillPanel);
//
//        pack();
//    }
//
//    private void addResource() {
//        String resourceName = resourceTextField.getText().trim();
//        if (!resourceName.isEmpty()) {
//            Resource resource = new Resource(resourceName);
//            resources.add(resource);
//            resourceListModel.addElement(resourceName);
//            resourceTextField.setText("");
//        }
//    }
//
//    private void addSkill() {
//        int selectedIndex = resourceList.getSelectedIndex();
//        if (selectedIndex >= 0) {
//            String skillName = skillNameTextField.getText().trim();
//            String skillExp = skillExpTextField.getText().trim();
//
//            if (!skillName.isEmpty() && !skillExp.isEmpty()) {
//                Skill skill = new Skill(skillName, Integer.parseInt(skillExp));
//                resources.get(selectedIndex).addSkill(skill);
//                skillNameTextField.setText("");
//                skillExpTextField.setText("");
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            ResourceFormSwing resourceFormSwing = new ResourceFormSwing();
//            resourceFormSwing.setVisible(true);
//        });
//    }
//
//    public List<Resource> getResources() {
//        return resources;
//    }
//
//    public void setResources(List<Resource> resources) {
//        this.resources = resources;
//    }
//
//    public static class Resource extends DeveloperTaskAllocator.Resource {
//        private final String name;
//        private List<Skill> skills;
//
//        public Resource(String name) {
//            this.name = name;
//            this.skills = new ArrayList<>();
//        }
//
//        public void addSkill(Skill skill) {
//            skills.add(skill);
//        }
//
//        @Override
//        public String toString() {
//            return name;
//        }
//
//        public String getName() {
//            return this.name;
//        }
//    }
//
//    private static class Skill {
//        private final String name;
//        private final int exp;
//
//        public Skill(String name, int exp) {
//            this.name = name;
//            this.exp = exp;
//        }
//
//        @Override
//        public String toString() {
//            return name + " (" + exp + " years)";
//        }
//    }
//}
