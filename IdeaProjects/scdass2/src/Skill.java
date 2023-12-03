public class Skill  // Skill of Developer
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