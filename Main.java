public class Main {
    public static void main(String[] args) {
        Department department = new Department();
        department.addTeam("Team 1");
        department.addTeam("Team 2");
        department.addTeam("Team 3");
        
        // print the teamname of the linked list
        for (Team team : department.getTeamList()) {
            System.out.println(team.getTeamName());
        }}
}