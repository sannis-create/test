// pure abstraction - no implementation in interface
interface Animals
{
    int eyes = 2;

    void walk();
    default void Animals()
    {
        System.out.println("Hello");
    }

}
interface Herbivore
{
    int legs = 4;

}
class Horses implements Animals, Herbivore  // multiple inheritance
{
    static String name;
    public void walk()
    {
        System.out.println("it walks");
    }
    public void changeName(String name)
    {
        this.name = name;
    }
}
public class InterfaceAbstraction
{
    public static void main(String []args)
    {
        Horses hors = new Horses();
        hors.Animals();
        hors.walk();
        Horses.name = "Bella";
        System.out.println(hors.name);
        hors.changeName("Mithoo");
        System.out.println(hors.name);
        System.out.println(hors.eyes+ " eyes");
        System.out.println(hors.legs+ " legs");
    }
}
