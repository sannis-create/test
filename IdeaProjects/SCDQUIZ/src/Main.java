//import java.util.Scanner;
//import java.util.*;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class Main {
//    public static void main(String[] args) {
//        String obj;
//        String delimeter = ",";
//        String file = "Student - Sheet1.txt";
//        String file1 = "Teacher - Sheet1.txt";
//        String file2 = "Course - Sheet1.txt";
//
//        List<Student> Students = new ArrayList<>();
//        List<Teacher> Teachers = new ArrayList<>();
//        List<Course> Courses = new ArrayList<>();
//try
//{
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            while ((obj = reader.readLine()) != null) {
//                String[] data = obj.split(delimeter);
//                if (data.length == 2) {
//                    String name = data[0].trim();
//                    int rollno = Integer.parseInt(data[1].trim());
//                    Student person = new Student(name, rollno);
//                    Students.add(person);
//                }
//            }
//        }
//        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1))) {
//            while ((obj = reader1.readLine()) != null) {
//                String[] data1 = obj.split(delimeter);
//                if (data1.length == 2) {
//                    String cnic = data1[0].trim();
//                    String name = data1[1].trim();
//                    Teacher person1 = new Teacher(cnic, name);
//                    Teachers.add(person1);
//                }
//            }
//        }
//        try (BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {
//            while ((obj = reader2.readLine()) != null) {
//                String[] data3 = obj.split(delimeter);
//                if (data3.length == 3) {
//                    int id = Integer.parseInt(data3[0].trim());
//                    String cnic = data3[2].trim();
//                    String name = data3[1].trim();
//                    Teacher person3 = new Teacher(cnic, name);
//                    Teachers.add(person3);
//                }
//            }
//        }
//    }
//     catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
//    }
//}