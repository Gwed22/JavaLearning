package Session6B;

import java.util.Objects;

/**
 * @author Do Huynh Anh Vu Coder
 * @version 1
 * @Date 8/02/2022
 */
public class StudentName {
    private String name;

    /**
     *
     */
    public StudentName(String name) {
        this.name = name;
    }

    /**
     * get name
     */
    public String getName() {
        return name;
    }

    /**
     * set name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name ;
    }
    @Override
    public int hashCode(){
        return Objects.hash(name);
    }
    
}
