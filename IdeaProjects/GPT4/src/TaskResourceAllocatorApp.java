import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JList;

public class TaskResourceAllocatorApp extends JFrame {
    private List<Resource> resources = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();

    private ResourceManagementGUI resourceManagementGUI;
    private TaskManagementGUI taskManagementGUI;
    private MatchingDisplayGUI matchingDisplayGUI;

    public TaskResourceAllocatorApp(List<Resource> resources, List<Task> tasks )
    {
        this.resources = resources;
        this.tasks = tasks;
    }

    public TaskResourceAllocatorApp() {
        setTitle("Task Resource Allocator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridLayout(2, 1));

        // Create instances of the GUI components
        matchingDisplayGUI = new MatchingDisplayGUI(resources, tasks);
        resourceManagementGUI = new ResourceManagementGUI(resources);
        taskManagementGUI = new TaskManagementGUI(tasks,matchingDisplayGUI);


        // Add the GUI components to the content pane
        getContentPane().add(resourceManagementGUI);
        getContentPane().add(taskManagementGUI);
//        getContentPane().add(matchingDisplayGUI);

        // Load resources and tasks from files (if needed)
        loadResourcesFromFile("resources.txt");
        loadTasksFromFile("tasks.txt");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TaskResourceAllocatorApp app = new TaskResourceAllocatorApp();
            app.setVisible(true);
        });
    }

    // Load resources from a file
    private void loadResourcesFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String resourceName = parts[0].trim();
                    String[] skills = parts[1].trim().split(",");
                    Resource resource = new Resource(resourceName);
                    for (String skill : skills) {
                        String[] skillParts = skill.trim().split(":");
                        if (skillParts.length == 2) {
                            String skillName = skillParts[0].trim();
                            int experience = Integer.parseInt(skillParts[1].trim());
                            resource.addSkill(new Skill(skillName, experience));
                        }
                    }
                    resources.add(resource);
                }
            }
            resourceManagementGUI.updateResourceList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Load tasks from a file
    private void loadTasksFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String taskName = parts[0].trim();
                    String[] skillRequirements = parts[1].trim().split(",");
                    Task task = new Task(taskName);
                    for (String skillRequirement : skillRequirements) {
                        String[] skillParts = skillRequirement.trim().split(":");
                        if (skillParts.length == 2) {
                            String skillName = skillParts[0].trim();
                            char skillLevel = skillParts[1].trim().charAt(0);
                            task.addSkillRequirement(skillName, skillLevel);
                        }
                    }
                    tasks.add(task);
                }
            }
            taskManagementGUI.updateTaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save resources to a file (if needed)
    private void saveResourcesToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Resource resource : resources) {
                writer.write(resource.getName() + " | ");
                List<Skill> skills = resource.getSkills();
                for (int i = 0; i < skills.size(); i++) {
                    Skill skill = skills.get(i);
                    writer.write(skill.getName() + ":" + skill.getExperienceYears());
                    if (i < skills.size() - 1) {
                        writer.write(", ");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save tasks to a file (if needed)
    private void saveTasksToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                writer.write(task.getName() + " | ");
                Map<String, Character> skillRequirements = task.getSkillRequirements();
                List<String> skillStrings = new ArrayList<>();
                for (Map.Entry<String, Character> entry : skillRequirements.entrySet()) {
                    skillStrings.add(entry.getKey() + ":" + entry.getValue());
                }
                writer.write(String.join(", ", skillStrings));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
