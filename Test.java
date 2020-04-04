import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        EmployeePairFinder finder = new EmployeePairFinder();
        System.out.println("Enter file name:");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        List<EmployeeProject> pairs;
        try {
            finder.fileParser(fileName);
            pairs = finder.getEmployeesPair();
            if(pairs.isEmpty()){
                System.out.println("No pairs were found!");
            }
            else {
                System.out.println("The ID's of the Employees, worked the longest together over the same project are:");
                System.out.println("ID: " + pairs.get(0).getEmployeeId() + " AND ID: " + pairs.get(1).getEmployeeId() );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
