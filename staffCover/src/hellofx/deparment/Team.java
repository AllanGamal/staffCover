package hellofx.deparment;
import java.util.LinkedList;

public class Team {

    private String teamName;
    private LinkedList<Fitter> fitterList = new LinkedList<Fitter>();
    private LinkedList<String> stationList = new LinkedList<String>();

    /**
     * Constructor for the team class
     * 
     * @param teamName
     */
    public Team(String teamName) {
        this.teamName = teamName;

    }

    /**
     * Method to change the team name
     * 
     * @param teamName
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Method to get the team name
     * 
     * @return teamName - the team name
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Method to add a fitter to the fitter list
     * Check if the fitter already exists
     * Adds the fitter to the list if it doesn't exist
     * Checks all this in the Fitter class by ID
     * 
     * @param fitter - name of the fitter
     */
    public void addFitter(Fitter fitter) {
        // check if the fitter already exists by id
        for (int i = 0; i < fitterList.size(); i++) {
            if ((fitterList.get(i).getID() == (fitter.getID()))) {
                System.out.println("Fitter already exists");
                return;
            }
        }
        this.fitterList.add(fitter);
    }

    /**
     * Method to remove a fitter from the fitter list
     * Compares the fitter id to the id in the list
     * 
     * @param fitter - name of the fitter
     */
    public void removeFitter(Fitter fitter) {
        for (int i = 0; i < fitterList.size(); i++) {
            if ((fitterList.get(i).getID() == (fitter.getID()))) {
                fitterList.remove(i);
                return;
            }
        }
    }

    /**
     * Method to move a fitter from one team to another
     * 
     * @param fitter     - name of the fitter
     * @param targetTeam - name of the team to move the fitter to
     */
    public void moveFitter(Fitter fitter, Team targetTeam) {
        for (int i = 0; i < fitterList.size(); i++) {
            if ((fitterList.get(i).getID() == (fitter.getID()))) {
                fitterList.remove(i);
                targetTeam.addFitter(fitter);
                return;
            }
        }
    }

    /**
     * Method to get the fitter list
     * 
     * @return fitterArray - array of the fitter list
     */
    public Fitter[] getFitterList() {
        Fitter[] fitterArray = new Fitter[fitterList.size()];
        for (int i = 0; i < fitterList.size(); i++) {
            fitterArray[i] = fitterList.get(i);
        }
        return fitterArray;
    }

    /**
     * Add a station to the station list
     * checks if the station already exists
     * 
     * @param department - name of the department
     * @param station    - name of the station
     */
    // check if the station already exists in any of the teams and add it if it
    // doesn't
    public void addStation(Object department, String station) {
        for (int i = 0; i < ((Department) department).getTeamList().length; i++) {
            for (int j = 0; j < ((Department) department).getTeamList()[i].getStationList().length; j++) {
                if (((Department) department).getTeamList()[i].getStationList()[j] == station) {
                    System.out.println("Station already exists");
                    return;
                }
            }
        }
        this.stationList.add(station);
    }

    /**
     * Get the value of stationlist (station)
     * 
     * @return the value of stationlist as an array
     */
    public String[] getStationList() {
        return stationList.toArray(new String[stationList.size()]);
    }

    /**
     * Remove a station from the station list
     * 
     * @param station - name of the station
     */
    public void removeStation(String station) {
        for (int i = 0; i < stationList.size(); i++) {
            if (stationList.get(i) == station) {
                stationList.remove(i);
                return;
            }
        }

    }
}
