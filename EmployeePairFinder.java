import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class EmployeePairFinder {
    private static final List<EmployeeProject> EMPLOYEE_PROJECTS_LIST = new ArrayList<>();
    private static final String SOURCE_FILES_PATH = System.getProperty("user.dir") + "\\src";

    public void fileParser(String fileName) throws FileNotFoundException {
        if(fileName == null) throw new FileNotFoundException();

        File file = new File(SOURCE_FILES_PATH + "\\"+ fileName.trim());

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))) {

            String line;
            String[] data;
            EmployeeProject employeeProject;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                line = line.replaceAll("\\s","");
                data = line.split(",");

                if (data.length == 4 && data[0].matches("\\d{3}") && data[1].matches("\\d{2}") && data[2].matches("^\\d{4}-\\d{2}-\\d{2}$") && (data[3].matches("^\\d{4}-\\d{2}-\\d{2}$") || data[3].toLowerCase().equals("null"))){
                    employeeProject = new EmployeeProject(Integer.parseInt(data[0]),Integer.parseInt(data[1]),LocalDate.parse(data[2]),data[3].toLowerCase().equals("null") ? LocalDate.now() : LocalDate.parse(data[3]));
                    EMPLOYEE_PROJECTS_LIST.add(employeeProject);
                }
            }
        }
    }

    private static Period getEmployeeProjectsPeriod(EmployeeProject e1, EmployeeProject e2){
        if(e1 == null || e2 == null) return Period.of(0,0,0);

        LocalDate sDate1 = e1.getStartDate();
        LocalDate eDate1 = e1.getEndDate();
        LocalDate sDate2 = e2.getStartDate();
        LocalDate eDate2 = e2.getEndDate();

        if(sDate1.compareTo(sDate2) > 0){
            if(eDate2.compareTo(sDate1) > 0)
                if(eDate1.compareTo(eDate2) < 0)
                    return Period.between(sDate1,eDate1);
                else
                    return Period.between(sDate1,eDate2);
        }

        if(sDate1.compareTo(sDate2) < 0){
            if(eDate1.compareTo(sDate2) > 0){
                if(eDate1.compareTo(eDate2) > 0)
                    return Period.between(sDate2,eDate2);
                else
                    return Period.between(sDate2,eDate1);
            }
        }

        if(sDate1.compareTo(sDate2) == 0){
            if(eDate1.compareTo(eDate2) < 0)
                return Period.between(sDate1,eDate1);
            else
                return Period.between(eDate2,sDate2);
        }

        return Period.of(0,0,0);

    }

    public List<EmployeeProject> getEmployeesPair(){

        if(EMPLOYEE_PROJECTS_LIST.isEmpty()) return null;
        var tmp = new HashMap<Integer,List<EmployeeProject>>();
        var allProjects = EmployeeProject.getAllProjects();


        for (Project p : allProjects){
            var projectEmployees = new ArrayList<EmployeeProject>();
            EMPLOYEE_PROJECTS_LIST.forEach((employeeProject -> {
                if(employeeProject.getProjectId() == p.getProjectId()){

                    projectEmployees.add(employeeProject);
                }
            }));
            tmp.put(p.getProjectId(),projectEmployees);
        }

        List<EmployeeProject> employeePair = new ArrayList<>();

        tmp.forEach((k,v)->{
        Period p1,p2;
            for(int i = 0; i < v.size(); i++){
                for(int j = 0; j < v.size(); j++){
                    if(i==j) continue;

                    if(employeePair.isEmpty()){
                        employeePair.add(v.get(i));
                        employeePair.add(v.get(j));

                    }
                    else{
                        p1 = getEmployeeProjectsPeriod(employeePair.get(0), employeePair.get(1));
                        p2 = getEmployeeProjectsPeriod(v.get(i), v.get(j));
                        if((((p1.getYears() * 365) + (p1.getMonths() * 12)) + p1.getDays()) < (((p2.getYears() * 365) + (p2.getMonths() * 12)) + p2.getDays())){
                            employeePair.clear();
                            employeePair.add(v.get(i));
                            employeePair.add(v.get(j));
                        }
                    }
                }
            }
        });
        return employeePair;
    }
}
