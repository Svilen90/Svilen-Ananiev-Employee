import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeProject {

    private static final List<Employee> EMPLOYEES = new ArrayList<>();
    private static final List<Project> PROJECTS = new ArrayList<>();

    private int employeeId;
    private int projectId;
    private LocalDate startDate;
    private LocalDate endDate;

    public EmployeeProject(){
        this.employeeId = 0;
        this.projectId = 0;
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
    }

    public EmployeeProject(int eId, int pId, LocalDate sDate, LocalDate eDate){
        this.employeeId = addEmployee(eId);
        this.projectId = addProject(pId);
        this.startDate = sDate == null ? LocalDate.now() : sDate;
        this.endDate = eDate == null ? LocalDate.now() : eDate;
    }

    private static boolean employeeExists(int empId){
        for (Employee emp : EMPLOYEES){
            if(emp.getEmoloyeeId() == empId) return true;
        }
        return false;
    }

    private static int addEmployee(int empId){
        if(!employeeExists(empId)){
            var employee = new Employee(empId);
            EMPLOYEES.add(employee);
            return employee.getEmoloyeeId();
        }
        return empId;
    }

    private static boolean projectExists(int pId){
        for (Project p : PROJECTS){
            if(p.getProjectId() == pId) return true;
        }
        return false;
    }

    private static int addProject(int pId){
        if(!projectExists(pId)){
            var project = new Project(pId);
            PROJECTS.add(project);
            return project.getProjectId();
        }
        return pId;
    }
    public int getEmployeeId() {
        return employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public static Employee getEmployee(int employeeId){
        return EMPLOYEES.stream()
                .filter(e-> e.getEmoloyeeId() == employeeId).findAny()
                .orElse(null);
    }

    public static Project getProject(int projectId){
        return PROJECTS.stream()
                .filter(e-> e.getProjectId() == projectId).findAny()
                .orElse(null);
    }

    public static List<Employee> getAllEmployees(){
        return EMPLOYEES;
    }

    public static List<Project> getAllProjects(){
        return PROJECTS;
    }
}
