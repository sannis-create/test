import java.util.HashMap;
import java.util.Map;

public class Task  // tasks to perform as a dev
{
    private final String name; // name of task e.g; Web Development
    private Map<String, String> mustHaveSkills; // multiple skills required for that Task

    public Task() // default constructor
    {
        this.name="/0";
    }
    public Task(String name)  // parameterized constructor
    {
        this.name = name;
        this.mustHaveSkills = new HashMap<>();
    }

    public void addMustSkill(String skillName, String level)
    // addition of skill to perform a certain Task plus expertise level
    {
        mustHaveSkills.put(skillName, level);
    }

    public String getName()  // get name of the Task
    {
        return name;
    }

    public Map<String, String> getMustHaveSkills()
    // get all skills required to accomplish a Task
    {
        return mustHaveSkills;
    }
}
