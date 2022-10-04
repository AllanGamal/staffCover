import java.util.LinkedList; 
public class Department {


    private String department;
    // make a linked list of objects
    private LinkedList<Team> teamList = new LinkedList<Team>();

    // Getters and setters
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // add an object to the linked list
    public void addTeam(String teamName) {
        Team team = new Team(teamName);
        teamList.add(team);
    }

    public Team[] getTeamList() {
        return teamList.toArray(new Team[teamList.size()]);
    }

   

    



    

    
}
 