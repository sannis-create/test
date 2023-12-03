import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;
import java.time.Period;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;


public class Main
{
        private String name;
        private LocalDate manufactured;

        public void setname(String name)
        {
            this.name = name;
        }
        public String getName()
        {
            return name;
        }
        public void setManufactured(LocalDate im)
        {
    this.manufactured=im;
        }

    public LocalDate getManufactured() {
        return manufactured;
    }

    public int getAge()
    {
        return Period.between(getManufactured(), LocalDate.now()).getYears();
    }
    //    class MyGen<T>{
//        T obj;
//        void add(T obj){this.obj=obj;}
//        T get(){return obj;}
//    }
//
//    public enum Seasons {Summer,Winter,Autumn,Spring};
    public static void main(String[] args) throws IOException, IndexOutOfBoundsException, NullPointerException {
//        MyGen<Integer> m = new MyGen<Integer>();
//        m.add(2);
//        System.out.println(m.get());

//    ArrayList list = new ArrayList(); // without generics
//        list.add("hi");
//        String s = (String)list.get(0); // type cast
//
//        System.out.println(list);
//
//
//        ArrayList <String> fruits = new ArrayList <>();  // generics
//        fruits.add("Hi");
//        fruits.add("2");
//        System.out.println(fruits);
//        System.out.println(fruits.get(1));
//        for(Seasons s1: Seasons.values())
//        {
//            System.out.println(s1);
//        }
//        fruits.remove("Hi");
//        fruits.clear();
//        String [] stringss = new String[1];
//        try {
//            stringss[0] = "1";
//            stringss[1] = "1";
//            stringss[2] = "1";
//        }
////        catch (Exception e)
////        {
////           throw new IOException();
////        }
//        catch (IndexOutOfBoundsException e)
//        {
//            throw new ArrayIndexOutOfBoundsException();
//        }
//        catch (NullPointerException e)
//        {
//            throw new NullPointerException();
//        }
        Main car4 =new Main();
        car4.setname("Sania");
        car4.setManufactured(LocalDate.of(2001,10,10));
        Main car5 = new Main();
        car5.setname("Zain");
        car5.setManufactured(LocalDate.of(1995,6,15));
        for(Main car:new Main[]{car4,car5}) // dynamic array and give it values
        {
           System.out.println(car.getName() +" "+ car.getAge());
        }
    }
}