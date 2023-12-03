// inheritance extending a class functionality to others so that they can use it
// inheritance is of 4 types in java
// single level inheritance shape and then triangle i.e., (single base single derived class)
// multi level inheritance shape extends triangle which is extended by EquilateralTriangle
// i.e., (single base single derived class and that derived is base class for another derived class)
// hierarchical inheritance shape is extended by triangle and rectangle (multiple derived classes)
// Hybrid inheritance (like the one below - combination of first 3)
class Shape
{
    String color;
    int length;
    int height;
    void printColor()
    {
        System.out.println("Shape color is "+ this.color);
    }
}
class Triangle extends Shape   // IS A
{
    void area(int l, int h)
    {
        this.length = l;
        this.height = h;
        System.out.println("Triangle has area: "+0.5*l*h);
    }
}
class EquilateralTriangle extends Triangle
{
    void area(int l, int h)
    {
        System.out.println("Equilateral Triangle has area: " + 0.5*l*h);
    }
}
class Rectangle extends Shape
{
    public void area(int l, int h)
    {
        this.length = l;
        this.height = h;
        System.out.println("Rectangle has area: "+l*h);
    }
}
public class Inheritance
{
    public static void main(String[] args)
    {
        Triangle t1 = new Triangle();
        int length = 11;
        int height = 20;
        t1.color = "blue";
        t1.printColor();
        t1.area(length,height);
        EquilateralTriangle eq = new EquilateralTriangle();
        eq.color = "red";
        eq.printColor();
        eq.area(length,height);
        Rectangle rec = new Rectangle();
        rec.color = "purple";
        rec.printColor();
        rec.area(length,height);
    }
}
