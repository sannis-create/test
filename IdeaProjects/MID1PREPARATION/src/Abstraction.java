// abstraction using abstract classes -- not pure abstraction
abstract class Animal
{
    public String name;
    Animal()
    {
        System.out.println("New Animal created");
    }
    abstract public void walk();
    public void eats(String name)
    {
        this.name = name;
        System.out.println(this.name + " eats too");
    }
}
class Horse extends Animal
{
    public void walk()
    {
        System.out.println("it walks on 4 legs");
    }
}
class Chicken extends Animal
{
    public void walk()
    {
        System.out.println("it walks on 2 legs");
    }
}
public class Abstraction
{
    public static void main(String []args) {
//    Animal an = new Animal(); // no new obj of this class would be created
        Horse hors = new Horse();
        hors.walk();
        hors.eats("Horse");
        Chicken ch = new Chicken();
        ch.eats("Chicken");
        ch.walk();
    }
}

