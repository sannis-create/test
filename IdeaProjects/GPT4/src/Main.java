import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Skill {
    private String name;
    private int experienceYears;

    public Skill(String name, int experienceYears) {
        this.name = name;
        this.experienceYears = experienceYears;
    }

    public String getName() {
        return name;
    }

    public int getExperienceYears() {
        return experienceYears;
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
            if (skill.getName().equals(skillName) && skill.getExperienceYears() >= requiredExperience) {
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

interface MatchingStrategy {
    List<Resource> findMatches(Task task, List<Resource> resources);
}

class ExactMatch implements MatchingStrategy {
    @Override
    public List<Resource> findMatches(Task task, List<Resource> resources) {
        List<Resource> matchingResources = new ArrayList<>();
        for (Resource resource : resources) {
            boolean allSkillsMatch = true;
            for (Map.Entry<String, Character> entry : task.getSkillRequirements().entrySet()) {
                String skillName = entry.getKey();
                char requiredLevel = entry.getValue();
                if (!resource.hasSkill(skillName, getExperienceFromLevel(requiredLevel))) {
                    allSkillsMatch = false;
                    break;
                }
            }
            if (allSkillsMatch) {
                matchingResources.add(resource);
            }
        }
        return matchingResources;
    }

    private int getExperienceFromLevel(char level) {
        switch (level) {
            case 'b':
                return 1;
            case 'i':
                return 3;
            case 'e':
                return 5;
            default:
                return 0;
        }
    }
}

class SkillOnlyMatch implements MatchingStrategy {
    @Override
    public List<Resource> findMatches(Task task, List<Resource> resources) {
        List<Resource> matchingResources = new ArrayList<>();
        for (Resource resource : resources) {
            boolean skillMatch = false;
            for (Map.Entry<String, Character> entry : task.getSkillRequirements().entrySet()) {
                String skillName = entry.getKey();
                if (resource.hasSkill(skillName, 1)) {
                    skillMatch = true;
                    break;
                }
            }
            if (skillMatch) {
                matchingResources.add(resource);
            }
        }
        return matchingResources;
    }
}
