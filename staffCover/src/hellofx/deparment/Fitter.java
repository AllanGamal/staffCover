package hellofx.deparment;
import java.util.LinkedList;

public class Fitter  {

    private String name;
    private int ID;
    private boolean availability = true;
    private LinkedList<String> competency = new LinkedList<String>();

    /**
     * Constructor for the fitter class
     * @param dep - the department name as an object
     * @param name - the fitter name as a string
     */
    public Fitter(Object dep, String name) {
        
        this.name = name;
        // random 5 digit number
        int temp = (int)(Math.random() * 90000) + 10000;

        // length of dep teamlist
        int length = ((Department)dep).getTeamList().length;
        
        // generate a unique ID
        for (int i = 0; i < length; i++) {
            // if the ID is already in use
           for (int j = 0; j < ((Department)dep).getTeamList()[i].getFitterList().length; j++) {
               if (((Department)dep).getTeamList()[i].getFitterList()[j].getID() == temp) {
                   // generate a new ID
                   ID = (int)(Math.random() * 90000) + 10000;
                   // reset the counter
                   i = 0;
               }
           }
        }

        this.ID = temp;
        
    }

    /**
     * Method to get the fitter name
     * @return this.ID- the fitter name
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Method to get the fitter name
     * @return this.name - the fitter name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to set the fitter name
     * @param name - the fitter name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to add a competency to the fitters competency list
     * @param compentency - the fitter competency
     * @return this.availability - sets the fitter competency
     */
    public void addCompetency(String competency) {
        // check if the competency already exists in the list and add it if it doesn't
        for (int i = 0; i < this.competency.size(); i++) {
            if (this.competency.get(i) == competency) {
                System.out.println("Competency already exists");
                return;
            }
        }
        this.competency.add(competency);
    }

    /**
     * Method to remove a competency from the fitters competency list
     * @param competency
     */
    public void removeCompetency(String competency) {
        this.competency.remove(competency);
    }

    /**
     * Method to get the fitters competency list
     * @return this.competency - array as the fitters competency list
     */
    public String[] getCompetency() {
        return this.competency.toArray(new String[this.competency.size()]);
    }

    /**
     * Method to get the fitters availability
     * @return this.availability - state of the fitters availability
     */
    public boolean getAvailability() {
        return this.availability;
    }

    /**
     * Method to toggle the fitters availability
     */
    public void toggleAvailability() {
        this.availability = !this.availability;
    }


}
    

