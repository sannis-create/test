import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Driver {
        public static void main(String[] args) {
//            String c;
//            Scanner obj = new Scanner();
//            System.out.println(" 1- Search Student by Roll Number");
//            System.out.println(" 2- Write this New Entered Marks Data into a New csv file.");
//            System.out.println("3- Edit a Student by Roll Number");
//            System.out.println("4- Delete a Student by Roll Number)");
//            System.out.println("5- Calculate Course Grade by Course_Id )");
//            while(c!="0")
//            {
//                if (c == "1") {
//                }
//                if(c== "2") {
//                }
//                if(c=="3"){
//                }
//                if(c=="4") {
//                }
//                if(c=="5"){
//                }
//
//            }
            String filePath = "Student - Sheet1.txt";
            String filePath1 = "Teacher - Sheet1.txt";
            String filePath2 = "Course - Sheet1.txt";
            List<Student> Students = new ArrayList<>();
            List<Teacher> Teachers = new ArrayList<>();
            List<Course> Courses = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Split the line into fields using space as the delimiter
                    String[] fields = line.split(",");

                    // Check if the line contains all three fields (name, roll number, and grade)
                    if (fields.length == 2) {
                        String name = fields[0];
                        String rollNumber = fields[1];
                        System.out.println(name+ " " + rollNumber);
                        Student person = new Student(name,rollNumber);
                        Students.add(person);
//                        Student person1 = new Student("11","Sania");
//                        Students.add(person1);
//                        Students.contains("1");
                    }
                    try (FileWriter writer = new FileWriter("Students.txt")) {
                        writer.write("All data added\n");
                        writer.write(Students.toString());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (BufferedReader reader1 = new BufferedReader(new FileReader(filePath1))) {
                String line;
                while ((line = reader1.readLine()) != null) {
                    // Split the line into fields using space as the delimiter
                    String[] fields = line.split(",");

                    // Check if the line contains all three fields (name, roll number, and grade)
                    if (fields.length == 2) {
                        String cnic = fields[0];
                        String name = fields[1];
                        System.out.println(cnic+ "   " + name);
                        Teacher person = new Teacher(cnic,name);
                        Teachers.add(person);
                        try (FileWriter writer = new FileWriter("Teachers.txt")) {
                            writer.write("All data added\n");
                            writer.write(Teachers.toString());
                        }
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            try (BufferedReader reader2 = new BufferedReader(new FileReader(filePath2))) {
                String line;
                while ((line = reader2.readLine()) != null) {
                    // Split the line into fields using space as the delimiter
                    String[] fields = line.split(",");

                    // Check if the line contains all three fields (name, roll number, and grade)
                    if (fields.length == 3) {
                        String id = fields[0];
                        String cname = fields[1];
                        String cnic = fields[2];
                        System.out.println(id + "  " + cname+ "  " + cnic);
                        Course person = new Course(id,cname,cnic);
                        Courses.add(person);
                        try (FileWriter writer = new FileWriter("Courses.txt")) {
                            writer.write("All data added\n");
                            writer.write(Courses.toString());
                        }
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }


            //part 10
            Students.clear();
            System.out.println("ArrayList after clearing: " + Students);
        }
    }