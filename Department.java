import java.util.*; 
public class Department {


    private String department;  
    private ArrayList[] combo;
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
    
    /**
     * Method to pr
     * 
     * @param arr
     * @return 
     */
    public ArrayList<String> comboWombo(LinkedList<String>[] arr) {
        // Number of arrays
        int n = arr.length;

        ArrayList<String> out = new ArrayList<String>();
        

        // To keep track of next element in
        // each of the n arrays
        int[] indices = new int[n];

        // Initialize with first element's index
        for (int i = 0; i < n; i++)
            indices[i] = 0;


        while (true) {

            // For every combination, add the first element of each array to the arraylist
            ArrayList<String> temp = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                // if the array already contains the element, remove the list 
                if (temp.contains(arr[i].get(indices[i]))) {
                    break;
                } 
                temp.add(arr[i].get(indices[i]));
                
            }
            // Add the combination to the arraylist if it is not already there
            // If n-3 then three is missing and so on
            if (temp.size() >= n) {
                out.add(temp.toString());
            }

            // Find the rightmost array that has more
            // elements left after the current element
            // in that array
            int next = n - 1;
            while (next >= 0 &&
                    (indices[next] + 1 >= arr[next].size()))
                next--;

            // No such array is found so no more
            // combinations left
            if (next < 0)
                return out;

            // If found move to next element in that
            // array
            indices[next]++;

            // For all arrays to the right of this
            // array current index again points to
            // first element
            for (int i = next + 1; i < n; i++)
                indices[i] = 0;
        }
    }

    /**
     * Method to print the team list
     * @return teamList - the team list
     */
    public ArrayList<String> getCombo() {
        // get the number of stations in the department
        int numStations = 0;
        
        for (int i = 0; i < this.getTeamList().length; i++) {
            numStations += this.getTeamList()[i].getStationList().length; 
        }
        LinkedList<String> []arr = new LinkedList[numStations];
        System.out.println(numStations);
        
        for (int i = 0; i < numStations; i++) {
            // make a new linked list for each station
            arr[i] = new LinkedList<String>();

        }

        // loop through every fitter and add them to the linked list if the competency is matched
        for (int i = 0; i < this.getTeamList().length; i++) {
            for (int j = 0; j < this.getTeamList()[i].getStationList().length; j++) {
                for (int k = 0; k < this.getTeamList()[i].getFitterList().length; k++) {
                    for (int l = 0; l < this.getTeamList()[i].getFitterList()[k].getCompetency().length; l++) {
                        if (this.getTeamList()[i].getStationList()[j] == this.getTeamList()[i].getFitterList()[k].getCompetency()[l]) {
                            arr[j].add(this.getTeamList()[i].getFitterList()[k].getName());
                        }
                    }
                    
                }
            }
        }

        return comboWombo(arr);
    }

}
