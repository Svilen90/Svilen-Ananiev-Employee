import java.io.Serializable;
import java.util.Objects;

public class Project implements Serializable {
    private static int numberOfProjects = 0;
    private int id;

    /**
     * Default constructor
     */
    public Project(){
        this.id = numberOfProjects;
        projectsIncrement();
    }

    public Project(int id){
        this.id = id;
        projectsIncrement();
    }

    public int getProjectId(){
        return this.id;
    }

    public static int getNumberOfProjects(){
        return numberOfProjects;
    }
    private static void projectsIncrement() {
        numberOfProjects++;
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;

        if(obj == null ) return false;

        if(this.getClass() != obj.getClass()) return false;

        var other = (Project) obj;

        return Objects.equals(this.id, other.id);
    }

}
