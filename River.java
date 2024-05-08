import java.util.Random;
import java.util.ArrayList;

/**
 * Models the overall river ecosystem
 * in the simulation.
 * 
 * @author Jackson Eshbaugh
 * @version 01/28/2024
 */
public class River {

    public Animal[] river;
    private AnimalArrayList doNotUpdate = new AnimalArrayList();
    
    /**
     * Generates a random river ecosystem of
     * specified length.
     * 
     * @param length The length of the river.
     */
    public River(int length) {
        this.river = new Animal[length];
        this.fillRiver();
    }
    
    /**
     * Randomly fills the river with fish,
     * bears, and empty (null) spots. To be used
     * in generating a new ecosystem.
     */
    private void fillRiver() {
        
        // Iterate through each slot in the river array
        for (int i = 0; i < river.length; ++i) {
            // Randomly decide what to place there
            Random r = new Random();
            int choice = r.nextInt(3);
            
            switch (choice) {
                case 0:
                    river[i] = null;
                    break;
                case 1:
                    river[i] = new Bear();
                    break;
                default: // (i.e., choice == 2)
                    river[i] = new Fish();
                    break;
            }
        }
        
    }
    /**
     * Gets the length of the river.
     * 
     * @return The length of the river
     * being modeled.
     */
    public int getLength() {
        return river.length;
    }
    
    /**
     * Finds the number of empty cells in the river.
     * 
     * @return The count of cells in the river
     * who are set to null
     */
    
    public int numEmpty() {
        
        int count = 0;
        
        for(Animal a : river) {
            if(a == null)
                count++;
        }

        return count;
    }
    
    /**
     * Attempts to add a new animal of age 0
     * and of random gender, who is the same type as
     * the specified animal to a random empty cell.
     * 
     * Helper method for {@link #processMove(int, int)} to fulfil
     * rule 4 in section 1.1 of the project specification.
     * 
     * @return True if the operation is successful, or false if
     * there aren't any empty cells.
     */
    public boolean addRandom(Animal animal) {
        if(numEmpty() == 0)
            return false;
            
        // Select the random cell to add the animal to
        Random r = new Random();
        int index = r.nextInt(numEmpty());
        
        // The index selected above takes into account only
        // cells who are currently null. In order to find the
        // referenced cell, iterate through the array and subtract
        // from the index whenever a cell is null, until index == 0.
        
        for (int i = 0; i < river.length; ++i) {
            if(river[i] == null) {
                if(index == 0) {
                    
                    // This is the cell that was selected.
                    // Now, find the type of the animal and
                    // create a new instance of one in this cell.
                    
                    if(animal instanceof Fish) {
                        river[i] = new Fish(0,
                            Animal.generateRandomGender());
                    } else { // (i.e., animal instanceof Bear)
                        river[i] = new Bear(0,
                            Animal.generateRandomGender());
                    }
                    
                    // Don't process a move for this animal until
                    // the next cycle.
                    doNotUpdate.add(river[i]);
                    
                    break;
                } else index--;
            }
        }
        
        return true;
    }
    
    /**
     * Updates one specific cell of the river. 
     * If the cell {@code river[i]} is null, no action is taken.
     * Otherwise:
     * <ol>
     *   <li>Checks if the animal should be updated (i.e., if the animal already moved this cycle). If not, no further action is taken.</li>
     *   <li>Checks the animal's age. If it's at the end of its lifespan, it dies.</li>
     *   <li>If the animal is still alive, decides if the animal will move—and if so, in which direction.</li>
     *   <li>Calls {@link #processMove(int, int)} to process the move taken by the animal and any consequeces of it.</li>
     * </ol>
     * 
     * @param i The index of the cell in the {@code river} array to be updated.
     */
    public void updateCell(int i) {
        
        // Ensure there is an animal in this cell.
        if(river[i] == null) return;
        
        // Make sure this animal hasn't already been updated.
        if(doNotUpdate.find(river[i]) != -1) return;
        
        // Don't let the animal be updated again.
        doNotUpdate.add(river[i]);
        
        // Increment the animal's age.
        if(!river[i].incrAge()) {
            // The animal has reached its lifespan and has died.
            river[i] = null;
            return;
        }
        
        // Choose a move:
        
        Random r = new Random();
        int choice = r.nextInt(3);
        
        switch(choice) {
            
            case 0:
                // Don't move.
                return;
            case 1:
                // Move left.
                
                // Find the target index, taking care to
                // allow for wrap-around in the array.
                int target = i - 1;

                
                if(target < 0)
                    target = river.length - 1;
                
                processMove(i, target);
                return;
            default:
                // (i.e., 2)
                // Move right.
                
                // Find the target index, taking care to
                // allow for wrap-around in the array.
                target = i + 1;
                
                if(target > river.length - 1)
                    target = 0;
                    
                processMove(i, target);
                return;
        }
        
    }
    
    /**
     * Helper method for {@link #updateCell(int)}.
     * Moves the Animal in question, processing any
     * consequences of these moves as well.
     * 
     * @param i The index of the animal to move (the same parameter as in {@link #updateCell(int)}).
     * @param targetIndex The index where the animal wants to move (an index one away from i, 
     * unless i is at the extrema of the array).
     */
    public void processMove(int i, int targetIndex) {
            
        if(river[targetIndex] == null) {
            // The cell is empty, move the animal here.
            river[targetIndex] = river[i];
            river[i] = null;
            return;
        }
        
        if((river[targetIndex] instanceof Fish) && (river[i] instanceof Bear)) {
            // The bear kills the fish, regardless of gender.
            river[targetIndex] = river[i];
            river[i] = null;
            return;
        }
        
        if((river[i] instanceof Fish) && (river[targetIndex] instanceof Bear)) {
            // The bear once again kills the fish. Effectively, the fish is simply
            // removed from the river array.
            river[i] = null;
            return;
        }
        
        // The animals are of the same species.
        
        if(river[i].getGender() != river[targetIndex].getGender()) {
            // Two animals of opposite genders have collided. They remain in their spaces
            // but a new animal of their type is added to the array (if possible).
            this.addRandom(river[i]);
            return;
        }
        
        // The animals are the same gender.
        
        if((river[i] instanceof Fish) && (river[targetIndex] instanceof Fish)) {
            // Fish of the same gender who collide don't do anything.
            return;
        }
        
        Bear cell = (Bear) river[i];
        Bear target = (Bear) river[targetIndex];
        
        if(cell.getStrength() > target.getStrength()) {
            // This bear kills the target bear.
            river[targetIndex] = river[i];
            river[i] = null;
            return;
        }
        
        if(cell.getStrength() < target.getStrength()) {
            // The target bear kills this bear.
            river[i] = null;
            return;
        }
        
        // Bears of the same gender and strength
        // simply remain in their spaces, so that case
        // isn't handled here.
    }
    
    /**
     * Performs one cycle of the simulation
     * by calling {@link #updateCell(int)}
     * on every cell in the {@code river} array.
     */
    public void updateRiver() {
        for(int i = 0; i < river.length; ++i) {
            updateCell(i);
        }
        
        // Clear the doNotUpdate ArrayList, prepare
        // for the next cycle.
        doNotUpdate.clear();
    }
    
    /**
     * Produces a string representation
     * of the river, using "---" to represent
     * empty cells and {@link Animal#toString()} for
     * animals.
     * 
     * @return The string representation of the river.
     */
    @Override
    public String toString() {
        String s = "";
        for(Animal a : river) {
            if(a == null)
                s += "--- ";
            else 
                s += a.toString() + " ";
        }
        
        // Get rid of the trailing space
        s = s.substring(0, s.length() - 1);
        
        return s;
    }
}