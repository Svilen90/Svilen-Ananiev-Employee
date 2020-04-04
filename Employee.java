import java.io.Serializable;
import java.util.Objects;

/**
 * Employee class to hold information about employees
 * @author Svilen Ananiev
 */
public class Employee implements Serializable{
    private static int numberOfEmployees = 0;
    private int id;
    /**
     * Default constructor
     */
    public Employee(){
        this.id = numberOfEmployees;
        employeesIncrement();
    }

    public Employee(int id){
        this.id = id;
        employeesIncrement();
    }

    public int getEmoloyeeId(){
        return this.id;
    }

    public static int getNumberOfEmployees(){
        return numberOfEmployees;
    }

    private static void employeesIncrement(){
        numberOfEmployees++;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;

        if(obj == null ) return false;

        if(this.getClass() != obj.getClass()) return false;

        var other = (Employee) obj;

        return Objects.equals(this.id, other.id);
    }
}
