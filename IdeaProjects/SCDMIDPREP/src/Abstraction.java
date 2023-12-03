 abstract class Animal
    {
        private String name;
        Animal()
        {
            System.out.println("Animal Created");
        }
        abstract void walk();
        public void born(String name)
        {
            this.name = name;
            System.out.println(name + " is born");
        }
    }
    class Chicken extends Animal
    {
        public void walk()
        {
            System.out.println("Chicken walks");
        }
    }
 public class Abstraction
 {
    public static void main(String []args)
    {
        Chicken ch = new Chicken();
        ch.born("Chicken");
        ch.walk();
    }
}


