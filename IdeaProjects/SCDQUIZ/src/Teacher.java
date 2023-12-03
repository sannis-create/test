import java.util.ArrayList;
import java.util.List;
public class Teacher {
    private String CNIC;
    private String name;
    Course cid;
    List <Student> student;

    public Teacher(String CNIC,String name)
    {
        this.name = name;
        this.CNIC = CNIC;
    }
//    public void setMarks(Student id, Course cid, int marks)
//    {
//        student.set(id,marks);
//    }
}
