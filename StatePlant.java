import java.util.Scanner;

public class StatePlant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        State s = new State();  

        s.read_input(scanner);         

        s.calc_plant();         

        System.out.print("Enter 1 to view a Map of the scenario, or 0 to exit: ");
        int choice = scanner.nextInt();
        
        if (choice == 1) {
           s.display_map();
        }
        
        scanner.close();
    }
}
