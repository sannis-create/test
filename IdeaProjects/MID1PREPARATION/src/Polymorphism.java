//
//    // compile time polymorphism = function overloading
//    // runtime polymorphism = function overriding (use in inheritance)
//    class Student
//    {
//        String name;
//        int age;
//        Student()  // default constructor
//        {
//            System.out.println("Constructor called");
//        }
//        Student(String name, int age)  // parametrized constructor
//        {
//            this.name= name;
//            this.age = age;
//        }
//        Student(Student s2)  // copy constructor
//        {
//            this.name = s2.name;
//            this.age = s2.age;
//        }
//        public void printInfo(String name)
//        {
//            System.out.println(name);
//        }
//        public void printInfo(int age)
//        {
//            System.out.println(age);
//        }
//        public void printInfo()
//        {
//            System.out.println(this.name +"\n" + this.age);
//        }
//    }
//    public class Polymorphism
//    {
//        public static void main(String []args)
//        {
//            Student s2 = new Student();
//            s2.name = "Sanya";
//            s2.age = 24;
//            // same method names ( different parameters)
//            s2.printInfo("Sana");  // string
//            s2.printInfo(12);  // integer
//            s2.printInfo(); // both
//        }
//    }
