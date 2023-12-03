import java.util.List;
import java.util.ArrayList;
public class Course {
    private String id;
    private String cname;
    private String TCNIC;
    List <Student> students = new ArrayList<>();

    public Course(String id,String cname, String TCNIC)
    {
        this.id = id;
        this.cname = cname;
        this.TCNIC = TCNIC;
    }
}
