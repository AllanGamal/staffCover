import java.util.LinkedList; 
public class Department {


    private String department;  
    private LinkedList<Team> teamList = new LinkedList<Team>(); // list of teams in the department

    /**
     * Constructor for the department class
     * @param department
     */
    public Department(String department) {
        this.department = department;
    }
    
    /**
     * Method to get the department name
     * @return department - the department name
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Method to set the department name
     * @param department - the department name
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Method to add a team to the team list
     * Check if the team already exists
     * Adds the team to the list if it doesn't exist
     * @param teamName - name of the team
     */
    public void addTeam(Object team) {
        
       // check if the team already exists
        for (int i = 0; i < teamList.size(); i++) {
            if ((teamList.get(i).getTeamName() == ((Team)team).getTeamName())) {
                System.out.println("Team already exists");
                return;
            }
        }
    // add the object to the linked list if it is not already there
        this.teamList.add((Team)team);
        
    }

    /**
     * Method to remove a team from the team list
     * @param teamName - name of the team
     */
    public void removeTeam(String teamName) {
        for (int i = 0; i < teamList.size(); i++) {
            if ((teamList.get(i).getTeamName() == (teamName))) {
                teamList.remove(i);
                return;
            }
        }
        System.out.println(teamName + " does not exist");
    }

    /**
     * Get the value of teamlist (team)
     * @return the value of teamlist as an array
     */
    public Team[] getTeamList() {
        return teamList.toArray(new Team[teamList.size()]);
    }
    
}
 