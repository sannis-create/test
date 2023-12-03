import java.io.*;   // for handling file operations
import java.util.*;  // for handling Data collections(list, maps) data structures

public class DeveloperTaskAllocator
{
    // Customized Exception classes defined
    public static class Res_IOException extends Exception
    {
        // IO Exception is handled here
        public Res_IOException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    public static class Res_Exception extends Exception
    {
        // Resource Allocation Exception handling
        public Res_Exception(String message)
        {
            super(message);
        }
    }

    public static class Skill  // Skill of Developer
    {
        private final String name;  // name of skill
        private final int exp; // experience in integer form

        public Skill() // default constructor
        {
            this.name = "/0";
            this.exp =0;
        }
        public Skill(String name, int experience) // parameterized constructor
        {
            this.name = name;
            this.exp = experience;
        }

        public String getName() // getter of name
        {
            return this.name; // name of skill
        }

        public int getExp()  // getter of experience
        {
            return this.exp; // experience of developer in that skill
        }
    }

    public static class Task  // tasks to perform as a dev
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


   // resource required certain skills
    public static class Resource
   {
        private final String name; // name of the resource
        private List<Skill> skills;
        // list of skills required for a particular resource
        // Association (has a relationship)
       // every developer has skill(s)

       public Resource()  // default constructor
       {
           this.name = "/0"; //name of dev
       }
        public Resource(String name) // parameterized constructor
        {
            this.name = name;
            this.skills = new ArrayList<>();
        }

        public void addSkill(Skill skill)  // adding a skill to a resource(dev)
        {
            skills.add(skill);
        }

        public List<Skill> getSkills()  // get skills for a particular resource
        {
            return skills;
        }

        public String getName()  // get name of resource
        {
            return name;
        }
    }

    // made this abstract because 2 matching strategies to implement
    public abstract static class MatchingStrategy
    {
        // Match to a resource (exact (expertise+ skill)/skill based (not expertise based))
        public abstract boolean isMatch(Resource resource, Task task);
        // returns the status whether match exists or not (true/false)
    }

    public static class SkillOnlyMatch extends MatchingStrategy
    {
        @Override // overriding due to 2 implementations
        public boolean isMatch(Resource developer, Task task) {
            Map<String, String> taskSkills = task.getMustHaveSkills(); // skills to accomplish task
            List<Skill> devSkills = developer.getSkills();  // skills of dev

            for (int i = 0; i < taskSkills.size(); i++) //for all skills required for a single task
            {
                String skillName = taskSkills.keySet().toArray(new String[0])[i];

                boolean flag = false;
                //matching criterion check
                // skill plus experience both are check for a perfect match
                for (int j = 0; j < devSkills.size(); j++)
                {
                    Skill skill = devSkills.get(j);
                    if (skill.getName().equals(skillName)) { // no experience check here
                        // expertise level not main here
                        flag = true; // if true then break (dev found out
                        break;
                    }
                }
                if (!flag) {
                    return false; // if skill is not found or experience not met
                }
            }

            return true;
        }
    }


    // Exactly the same match skill + expertise both matches
    public static class ExactMatch extends MatchingStrategy
    {
        @Override // overriding due to 2 implementations
        public boolean isMatch(Resource developer, Task task)
        {
            Map<String, String> taskSkills = task.getMustHaveSkills(); // skills to accomplish task
            List<Skill> devSkills = developer.getSkills(); // skills of dev

            for (int i = 0; i < taskSkills.size(); i++) //for all skills required for a single task
            {
                String skillName = taskSkills.keySet().toArray(new String[0])[i];
                String optLevel = taskSkills.get(skillName);

                boolean flag = false;
                for (int j = 0; j < devSkills.size(); j++)
                {
                    //matching criterion check
                    Skill skill = devSkills.get(j);
                    // skill plus experience both are check for a perfect match
                    if (skill.getName().equals(skillName) && skill.getExp() >= getExpLevel(optLevel))
                    {
                        flag = true; // if true then break
                        break;
                    }
                }

                if (!flag)  // if skill is not found or experience not met
                {
                    return false;
                }
            }
            return true;
        }

        private int getExpLevel(String ch)
        {
            switch (ch)
            {
                case "b":  // beginner dev case
                    return 1;
                case "i":  // intermediate dev case
                    return 2;
                case "e":  // experienced dev case (expert)
                    return 3;
                default:  // otherwise the person is not a developer
                    return 0;
            }
        }
    }


    public static void main(String[] args)
    {
        List<Resource> developers = new ArrayList<>(); // list of developers
        List<Task> tasks = new ArrayList<>(); // list of tasks

        try { // file reading as java.io.*
            // reading each char of the file so that no issues would occur in file handling
            try (BufferedReader devSkillReader = new BufferedReader(new FileReader("Developers.txt")))
            {
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
            }
            catch (IOException e)
            {  // customized exception thrown
                throw new Res_IOException("Error reading resource file: " + e.getMessage(), e);
            }

            // reading each char of the file so that no issues would occur in file handling
            // reading tasks and the experience required to accomplish
            try (BufferedReader taskReader = new BufferedReader(new FileReader("tasks.txt"))) {
                String line;
                // each line of the file till reached eof
                if ((line = taskReader.readLine()) != null) {
                    do {
                        String[] parts = line.split("\\|");  // name part of task
                        String taskName = parts[0].trim();
                        String[] skillerData = parts[1].split(","); //// expertise in b,i,e
                        Task task = new Task(taskName); // new Task in town

                        for (int i = 0; i < skillerData.length; i++) {
                            String skillData = skillerData[i];
                            String[] skillParts = skillData.trim().split(":"); // skill to accomplish task
                            String skillName = skillParts[0].trim();
                            String skillLvl = skillParts[1].trim(); //skill expertise in b,i,e
                            task.addMustSkill(skillName, skillLvl); // adding skill to that specific task
                        }

                        tasks.add(task); // new Tasks to perform
                    } while ((line = taskReader.readLine()) != null); // till reached eof
                }
            } catch (IOException e) {  // exception handling for InputOpening of file
                throw new Res_IOException("file reading disrupted: " + e.getMessage(), e);
            }

           // 2 matching strategies both are to check
            MatchingStrategy skillOnlyMatchStrategy = new SkillOnlyMatch(); // skill dev for the task
            MatchingStrategy exactMatchStrategy = new ExactMatch(); // exact dev for the task


            // Allocate tasks to developers
            for (int i = 0; i < tasks.size(); i++) // for all tasks in list
            {
                Task task = tasks.get(i);
                System.out.println("Task |  " + task.getName()); // printing task first
                System.out.println("Based on Skill, Could be allocated to Developer(s)::");
                for (int j = 0; j < developers.size(); j++) { // or in latter case
                    Resource dev = developers.get(j);
                    if (skillOnlyMatchStrategy.isMatch(dev, task)) { // could be given to skilled personnel
                        System.out.println("- " + dev.getName());
                    }
                }

                System.out.println("Based on Expertise, Could be allocated to Developer(s):"); // this task could be given to
                for (int j = 0; j < developers.size(); j++)
                {
                    Resource dev = developers.get(j);
                    if (exactMatchStrategy.isMatch(dev, task)) // right person for right job
                    {
                        System.out.println("- " + dev.getName());
                    }
                }
                System.out.println('\n');
            }
        }
        catch (Res_IOException e) { // exception handling
            System.out.println("resource not allocated properly while I/O " + e.getMessage());
        }
    }
}