package Session6B;

import java.util.ArrayList;
import jdk.nashorn.internal.objects.NativeArray;

/**
 * @author Do Huynh Anh Vu Coder
 * @version 1
 * @Date 8/02/2022
 */
public class Student {

    private ArrayList<StudentName> List;

    /**
     * Create new array
     */
    public Student() {
        this.List = new ArrayList<StudentName>();
    }


    public Student(ArrayList<StudentName> List) {
        this.List = List;
    }

    /**
     * Add name to array
     */
    public void addname(StudentName sv) {
        this.List.add(sv);
    }

    /**
     * Display list name in array
     */
    public void inStudent() {
        for (StudentName student : List) {
            System.out.println("-" + student);
        }
    }

    /**
     * Find name in array
     */
    public void searchStudent(String ten) {
        for (StudentName student : List) {
            if (student.getName().indexOf(ten) >= 0) {
                System.out.println("+" + student);
            } else {
                System.out.println("No result found!");
            }
        }
    }

}
