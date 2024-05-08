import java.util.Random;

/**
 * Models a bear in the river ecosystem.
 * 
 * @author Jackson Eshbaugh
 * @version 1/27/2024
 */
public class Bear extends Animal {
    
    private final int MAX_AGE = 9;
    
    /**
     * Creates a bear of random age
     * (within a bear's normal
     * lifespan) and random gender.
     */
    public Bear() {
        // Pick a random age ...
        Random r = new Random();
        this.age = r.nextInt(MAX_AGE + 1);
        
        // ... and a random gender
        this.gender = Animal.generateRandomGender();
    }
    
    /**
     * Creates a bear of specified age and gender. 
     * If the specified age is larger than the
     * maximum age of a bear, the age is set to that
     * maximum.
     * 
     * @param age The age of the animal
     * @param gender The gender of the animal
     */
    public Bear(int age, Gender gender) {
        super(age, gender);
        if(this.age > MAX_AGE)
            this.age = MAX_AGE;
    }
    
    @Override
    public boolean maxAge() {
        return age == MAX_AGE;
    }
    
    @Override
    public boolean incrAge() {
        
        // Don't increment age if at max age.
        if(maxAge()) return false;
        
        age++;
        return true;
        
    }  
    
    /**
     * Get this bear's strength,
     * a function of its age.
     * 
     * @return The strength of the bear, an
     * integer value from 0 to 5.
     */
    public int getStrength() {
        
        if(age <= 4) return age + 1;
        return 9 - age;

    }
    
    /**
     * Renders this animal as a
     * String value of the form
     * {@code BGA}, where {@code B} stands
     * for Bear, {@code G} is gender and
     * {@code A} is age.
     * 
     * @return the String value "BGA" described above.
     */
    @Override
    public String toString() {
        return "B" + super.toString();
    }
}
