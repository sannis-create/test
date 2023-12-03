
public class Student {
    private String name;
    private String rollno;
    Course cid;
    String grade;
    int marks;

    public Student(String name,String rollno)
    {
        this.name = name;
        this.rollno = rollno;
    }
    public void calGrade(int marks)
    {
        if(marks >= 90)
        {
          grade = "A";
        }
        if(marks >= 70)
        {
            grade = "B";
        }
        if(marks >= 60)
        {
            grade = "C";
        }
        if(marks >= 50)
        {
            grade = "D";
        }
        if(marks <50)
        {
            grade = "F";
        }
    }
}