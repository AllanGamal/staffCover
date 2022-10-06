public class Main {
    public static void main(String[] args) {
        

        /************ TESTING PURPOSES *************/
        Department department = new Department("Johan");
        Team team1 = new Team("Team1");
        Team team2 = new Team("Team2");
        Fitter fitter1 = new Fitter(department, "Daniel");
        Fitter fitter2 = new Fitter(department, "Danny");

        department.addTeam(team1);
        department.addTeam(team2);

        team1.addStation(department, "Station1");
        team1.addStation(department, "Station2");
        team1.addStation(department, "Station3");
        team1.addStation(department, "Station4");

        team2.addStation(department, "Station5");
        team2.addStation(department, "Station6");
        team2.addStation(department, "Station7");
        team2.addStation(department, "Station2");

        for (int i = 0; i < department.getTeamList().length; i++) {
            System.out.println("Team: " + department.getTeamList()[i].getTeamName());
            for (int j = 0; j < department.getTeamList()[i].getStationList().length; j++) {
                System.out.println("Station: " + department.getTeamList()[i].getStationList()[j]);
            }
        }
        
}}