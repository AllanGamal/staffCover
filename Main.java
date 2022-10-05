public class Main {
    public static void main(String[] args) {
        
        
        

        Department department = new Department("Johan");
        Team team = new Team("Team1");
        Fitter fitter = new Fitter(department, "Daniel");

        department.addTeam(team);
        team.addFitter(fitter);

        int test = fitter.getId();

        System.out.print(test);

        // print every team name of the department
        for (int i = 0; i < department.getTeamList().length; i++) {
            System.out.println(department.getTeamList()[i].getTeamName());
        }
}}