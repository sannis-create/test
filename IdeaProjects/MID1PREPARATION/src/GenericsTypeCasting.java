import java.io.*;
import java.util.ArrayList;
import java.util.Collections;  // Import the Collections class
public class GenericsTypeCasting
{
        public static void main (String[] args)
        {
            ArrayList<String> al =new ArrayList<String>();
            al.add("Vivek Yadav");
            al.add("Vivek Yadav");
            al.add("Vivek Yadav");
            al.set(0, "Opel");
            String name =al.get(0);
            System.out.println(name);
            System.out.println(al.size());
            System.out.println(al);   //or
            for (String i : al) {
                System.out.println(i);
            }
            ArrayList<Integer> ala =new ArrayList<>();
            ala.add(1);
            ala.add(0);
            Collections.sort(ala);  // Sort
            for (Integer i : ala) {
                System.out.println(i);
            }
            al.remove(0);
            System.out.println(al);
            System.out.println("GFG!");
        }
    }
