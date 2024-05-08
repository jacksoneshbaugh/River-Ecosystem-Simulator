import java.util.Scanner;

/**
 * Simulates evolutions of river ecosystems
 * containing bears and fish repeatedly.
 * 
 * <i>(Project 1 for CS 150)</i>
 * 
 * @author Jackson Eshbaugh
 * @version 01/28/2024
 */
public class RiverSimulator {
    
    /**
     * Repeatedly simulates river ecosystem
     * evolutions until the user exits.
     * 
     * @param args not used.
     */
    public static void main(String[] args) {
    
        System.out.println("Welcome to CS 150 River Ecosystem Simulator!");
        
        boolean exit = false;
        Scanner s = new Scanner(System.in);
        
        // Perform as many simulations as the user wants.
        do {
            System.out.println("River Ecosystem Simulator");
            System.out.println("Please choose: 1 (random river) 2 (exit)");
            
            int selection = s.nextInt();
            
            if(selection == 2) exit = true;
            else if(selection == 1) {
                // Generate random river.
                
                System.out.println("Creating a random river...");
                System.out.println("Enter the river length (any integer bigger than 0): ");
                int size = s.nextInt();
                
                // Validate size
                while (size <= 0 ) {
                    System.out.println("Enter the river length (any integer bigger than 0): ");
                    size = s.nextInt();
                }
                
                System.out.println("Enter the number of cycles (any integer bigger than 0): ");
                int cycles = s.nextInt();
                
                // Validate cycles
                while (cycles <= 0 ) {
                    System.out.println("Enter the number of cycles (any integer bigger than 0): ");
                    cycles = s.nextInt();
                }
                
                // We have all data points. Create River.
                River river = new River(size);
                
                System.out.println("Initial river:");
                System.out.println(river.toString());
                
                for(int i = 0; i < cycles; ++i) {
                    river.updateRiver();
                    System.out.println("After cycle " + (i + 1));
                    System.out.println(river.toString());
                }
                
            } else System.out.println("Invalid selection. Please try again.\n");
            
        } while (!exit);
        
        System.out.println("Goodbye!");
        
    }
}