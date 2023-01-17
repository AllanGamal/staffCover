package hellofx.deparment;

import java.util.*;
import hellofx.deparment.Department;
import hellofx.deparment.Team;
import hellofx.deparment.Fitter;

public class Department {

    private String department;
    private ArrayList[] combo;
    private LinkedList<Team> teamList = new LinkedList<Team>(); // list of teams in the department

    /**
     * Constructor for the department class
     * 
     * @param department
     */
    public Department(String department) {
        this.department = department;
    }

    /**
     * Method to get the department name
     * 
     * @return department - the department name
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Method to set the department name
     * 
     * @param department - the department name
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Method to add a team to the team list
     * Check if the team already exists
     * Adds the team to the list if it doesn't exist
     * 
     * @param teamName - name of the team
     */
    public void addTeam(Object team) {

        // check if the team already exists
        for (int i = 0; i < teamList.size(); i++) {
            if ((teamList.get(i).getTeamName() == ((Team) team).getTeamName())) {
                System.out.println("Team already exists");
                return;
            }
        }
        // add the object to the linked list if it is not already there
        this.teamList.add((Team) team);

    }

    /**
     * Method to remove a team from the team list
     * 
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
     * 
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
            // If the combination includes at least one fitter from each station
            // or if it includes fitters from n-1, n-2, or n-3 stations, add it to the list
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
                // print the arraylist

                return out;

            // If found move to next element in that
            // array
            indices[next]++;

            // For all arrays to the right of this
            // array current index again points to
            // first element
            for (int i = next + 1; i < n; i++) {
                indices[i] = 0;
            }

        }

    }

    /**
     * Method to print the team list
     * 
     * @return teamList - the team list
     */
    public ArrayList<ArrayList<String>> getCombo() {

        // get the number of stations in the department
        int numStations = 0; // number of stations in the department
        for (int i = 0; i < this.getTeamList().length; i++) { // loop through every team
            numStations += this.getTeamList()[i].getStationList().length; // add the number of stations in each team to
                                                                          // the total
        }
        List<String>[] arr = new LinkedList[numStations]; // create an array of linked lists for each station

        for (int i = 0; i < numStations; i++) {
            // make a new linked list for each station
            arr[i] = new LinkedList<String>();
        }

        int count = 0;

        // add all fitters to the array of linked lists
        for (int i = 0; i < this.getTeamList().length; i++) { // loop through every team
            for (int j = 0; j < this.getTeamList()[i].getStationList().length; j++) { // loop through every station in
                                                                                      // the team
                for (int x = 0; x < this.getTeamList().length; x++) { // loop through every team
                    for (int y = 0; y < this.getTeamList()[x].getFitterList().length; y++) { // loop through every
                                                                                             // fitter in the team
                        if (this.getTeamList()[x].getFitterList()[y].getAvailability()
                                && this.getTeamList()[x].getFitterList()[y]
                                        .gotCompetency(this.getTeamList()[i].getStationList()[j])) { // if the fitter is
                                                                                                     // available and
                                                                                                     // has the
                                                                                                     // competency
                            arr[count].add(this.getTeamList()[x].getFitterList()[y].getName()); // add the fitter to the
                                                                                                // linked list
                        }
                    }
                }
                count++; // increment the counter for the next station
            }
        }

        // make a clone of the array

        LinkedList<String>[] arr2 = new LinkedList[numStations];

        for (int i = 0; i < arr.length; i++) {
            arr2[i] = new LinkedList<>(); // create a new LinkedList for each element in the new array
            arr2[i].addAll(arr[i]); // add all elements from the original array to the new array
        }

        //

        /*
         * System.out.println("--------- Stationer matchade med fitters ---------");
         * // print the array
         * for (int i = 0; i < arr2.length; i++) {
         * System.out.println(arr2[i]);
         * }
         */

        // Number of arrays
        int n = arr2.length;

        ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();

        // To keep track of next element in
        // each of the n arrays
        int[] indices = new int[n];

        // Initialize with first element's index
        for (int i = 0; i < n; i++)
            indices[i] = 0;

        while (true) {

            // For every combination, add the first element of each array to the arraylist
            ArrayList<String> temp = new ArrayList<String>();
            int cnt = 0;
            for (int i = 0; i < n; i++) {

                // if the array already contains the element, remove the list
                // try {
                try {
                    if (temp.contains(arr2[i].get(indices[i]))) {
                        break;
                    }
                    temp.add(arr2[i].get(indices[i])); //
                } catch (Exception e) {
                    // if an exception occurs (e.g., index out of bounds), add "null1" to the temp
                    // list
                    System.out.println("Error");
                    return null;
                }
                cnt++;
            }
            if (cnt == n) {
                // add the combination to the output list if it is valid
                out.add(temp);
            }

            // Find the rightmost array that has more
            // elements left after the current element
            // in that array
            int next = n - 1;
            while (next >= 0 &&
                    (indices[next] + 1 >= arr2[next].size()))
                next--;

            // No such array is found so no more
            // combinations left
            if (next < 0) {
                /*
                 * // print out
                 * System.out.println("----------- Möjliga kombinationer ---------");
                 * for (int i = 0; i < out.size(); i++) {
                 * System.out.println(out.get(i));
                 * }
                 */

                return out;
            }

            // If found move to next element in that
            // array
            indices[next]++;

            // For all arrays to the right of this
            // array current index again points to
            // first element
            for (int i = next + 1; i < n; i++) {
                indices[i] = 0;
            }

        }
    }

    public ArrayList<ArrayList<String>> getMultipleCombos() {
        String fitter = "";

        ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < 2; i++) {

            // if out is empty
            // add a fitter with all competencies
            if (out == null || out.size() == 0 && i != 0) {
                if (i == 1) {
                    fitter = "TOM-1";
                } else if (i == 2) {
                    fitter = "TOM-2";
                } else if (i == 3) {
                    fitter = "TOM-3";
                } else if (i == 4) {
                    fitter = "TOM-4";
                } else if (i == 5) {
                    fitter = "TOM-5";
                }

                Fitter emptyFitter = new Fitter(this, fitter);

                // add all the stations to the fitter competency list
                for (int j = 0; j < this.getTeamList().length; j++) { // loop through every team
                    for (int k = 0; k < this.getTeamList()[j].getStationList().length; k++) { // loop through every
                                                                                              // station in the team
                        emptyFitter.addCompetency(this.getTeamList()[j].getStationList()[k]);

                    }
                }

                // add the fitter to the first team
                this.getTeamList()[0].addFitter(emptyFitter);
            }

            out = this.getCombo();
        }
        int count = 0;

        // add " --- " + station name to the end of each fitter
        for (int i = 0; i < this.getTeamList().length; i++) {
            for (int j = 0; j < this.getTeamList()[i].getStationList().length; j++) {
                String stationName = this.getTeamList()[i].getStationList()[j];
                for (int k = 0; k < out.size(); k++) {
                    out.get(k).set(count, out.get(k).get(count) + " --- " + stationName);
                }
                count++;
            }
        }

        removeTomFitters();
        return out;
    }

    public void removeTomFitters() {
        for (int i = 0; i < this.getTeamList().length; i++) { // loop through every team
            for (int j = 0; j < this.getTeamList()[i].getFitterList().length; j++) { // loop through every fitter in the
                                                                                     // team
                if (this.getTeamList()[i].getFitterList()[j].getName().contains("TOM-")) { // if the fitter is a tom
                                                                                           // fitter
                    this.getTeamList()[i].removeFitter(this.getTeamList()[i].getFitterList()[j]); // remove the fitter
                }
            }
        }
    }

}
