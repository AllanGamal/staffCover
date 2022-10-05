import java.util.LinkedList;

public class Fitter  {

    private String name;
    private int id;
    private boolean availability = true;
    private LinkedList<String> competency = new LinkedList<String>();

    public Fitter(Object dep, String name) {
        
        this.name = name;
        // random 5 digit number
        int temp = (int)(Math.random() * 90000) + 10000;

        // length of dep teamlist
        int length = ((Department)dep).getTeamList().length;
        
        // generate a unique id
        for (int i = 0; i < length; i++) {
            // if the id is already in use
           for (int j = 0; j < ((Department)dep).getTeamList()[i].getFitterList().length; j++) {
               if (((Department)dep).getTeamList()[i].getFitterList()[j].getId() == temp) {
                   // generate a new id
                   id = (int)(Math.random() * 90000) + 10000;
                   // reset the counter
                   i = 0;
               }
           }
        }

        this.id = temp;
        
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCompetency(String competency) {
        this.competency.add(competency);
    }

    public void removeCompetency(String competency) {
        this.competency.remove(competency);
    }

    public String[] getCompetency() {
        return this.competency.toArray(new String[this.competency.size()]);
    }

    public void toggleAvailability() {
        this.availability = !this.availability;
    }

}
    

