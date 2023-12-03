import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Resource
{
    private String name; // name of the resource
    private List<Skill> skills;
    // list of skills required for a particular resource
    // Association (has a relationship)
    // every developer has skill(s)

    public Resource(String name) // parameterized constructor
    {
        this.name = name;
        this.skills = new ArrayList<>();
    }


    public void addSkill(Skill skill)  // adding a skill to a resource(dev)
    {
        skills.add(skill);
    }

    public String getSkills()  // get skills for a particular resource
    {
        return skills.toString();
    }

    public String getName()  // get name of resource
    {
        return name;
    }

    public Resource()
    {
        List<Resource> developers = new ArrayList<>(); // list of developers
        List<Task> tasks = new ArrayList<>(); // list of tasks
        // file reading as java.io.*
            // reading each char of the file so that no issues would occur in file handling
            try (BufferedReader devSkillReader = new BufferedReader(new FileReader("Developers.txt"))) {
                String line; // each line of the file till reached eof
                while ((line = devSkillReader.readLine()) != null)  // read file line by line
                {
                    // for each line
                    String[] parts = line.split("\\|"); // name part of developer
                    String devName = parts[0].trim();
                    String[] devSkills = parts[1].split(","); // skill + experience check
                    Resource developer = new Resource(devName); // new developer added

                    for (int i = 0; i < devSkills.length; i++) {
                        String skilly = devSkills[i]; // for all skills that dev has
                        String[] skillParts = skilly.trim().split(":"); // skill identified
                        String skillName = skillParts[0].trim();
                        int expYears = Integer.parseInt(skillParts[1].trim()); // experience in years
                        Skill skill = new Skill(skillName, expYears); // new skill of that dev appears in list
                        developer.addSkill(skill); // that skill is added to his portfolio
                    }

                    developers.add(developer); // developer added in list
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    }