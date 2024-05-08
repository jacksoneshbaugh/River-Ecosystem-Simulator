import java.util.Random;

/**
 * Models a fish in the river ecosystem.
 * 
 * @author Jackson Eshbaugh
 * @version 1/27/2024
 */
public class Fish extends Animal {
    
    private final int MAX_AGE = 4;
    
    /**
     * Creates a fish of random age
     * (within a fish's normal
     * lifespan) and random gender.
     */
    public Fish() {
        // Pick a random age ...
        Random r = new Random();
        this.age = r.nextInt(MAX_AGE + 1);
        
        // ... and a random gender
        this.gender = Animal.generateRandomGender();
    }
    
    /**
     * Creates a fish of
     * specified age and gender. If
     * the specified age is larger than the
     * maxAge of a fish, the age is set to the
     * fish's max age.
     * 
     * @param age The age of the animal
     * @param gender The gender of the animal
     */
    public Fish(int age, Gender gender) {
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
     * Renders this animal as a
     * String value of the form
     * {@code FGA}, where {@code F} stands
     * for Fish, {@code G} is gender and
     * {@code A} is age.
     * 
     * @return the String value "FGA" described above.
     */
    @Override
    public String toString() {
        return "F" + super.toString();
    }
}