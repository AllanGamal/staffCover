public class Main {
    public static void main(String[] args) {
        
        Department department = new Department("Johan");
        Team team = new Team("Team1");
        Team team1 = new Team("Team2");
        Team team3 = new Team("Team1");
        Fitter fitter = new Fitter(department, "Daniel");

        department.addTeam(team);
        team.setTeamName("Team 54");

        for (int i = 0; i < department.getTeamList().length; i++) {
            System.out.println(department.getTeamList()[i].getTeamName());
        }

       

        
}}