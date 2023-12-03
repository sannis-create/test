import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


    public class CSVFIileHandling {
        public static void main(String[] args)
        {
            String obj;
            String delimeter = ",";
            String file = "list - Sheet1.csv";
            List<String> Student = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(file)))
            {
                while ((obj = reader.readLine()) != null)
                {
                    String[] data = obj.split(delimeter);
                    if (data.length == 5)
                    {
                        String name = data[0].trim();
                        int age = Integer.parseInt(data[1].trim());
                        String location = data[2].trim();
                        int scdMarks = Integer.parseInt(data[3].trim());
                        int pfMarks = Integer.parseInt(data[4].trim());
//                        String person = new String(name, age, location, scdMarks, pfMarks);
//                        Student.add(person);
//                        Student.add(person);
                    }
                }

                int scdmarks = 0;
                int pfmarks = 0;
//                for (String person : Student)
//                {
//                    scdmarks += person.getScdMarks();
//                    pfmarks += person.getPfMarks();
//                }
                double avgScd = (double) scdmarks / Student.size();
                double avgPf = (double) pfmarks / Student.size();


                try (FileWriter writer = new FileWriter("average_marks.csv")) {
                    writer.write("Average SCD Marks,Average PF Marks\n");
                    writer.write(avgScd + "," + avgPf);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
