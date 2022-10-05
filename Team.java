import java.util.LinkedList;

public class Team {

    private String teamName;
    private LinkedList<Fitter> fitterList = new LinkedList<Fitter>();
    private LinkedList<String> stationList = new LinkedList<String>();
    
    
    public Team(String teamName) {
        this.teamName = teamName;
        
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void addFitter(Fitter fitter) {
        fitterList.add(fitter);
    }

    public void removeFitter(Fitter fitter) {
        fitterList.remove(fitter);
    }

    public Fitter[] getFitterList() {
        Fitter[] fitterArray = new Fitter[fitterList.size()];
        for (int i = 0; i < fitterList.size(); i++) {
            fitterArray[i] = fitterList.get(i);
        }
        return fitterArray;
    }
     /**
     * Add a station to the station list
     * @return
     */
    public void addStation(String station) {
        stationList.add(station);
    }

    
    /**
     * Get the value of stationlist (station)
     * @return
     */
    public String[] getStationList() {
        return stationList.toArray(new String[stationList.size()]);
    }


}
