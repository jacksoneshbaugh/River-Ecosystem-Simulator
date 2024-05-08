import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Random;

/**
 * Test class for the {@link Fish} class.
 * 
 * @author Jackson Eshbaugh
 * @version 02/09/2024
 */
public class FishTest {

    /**
     * Creates a new FishTest object.
     */
    public FishTest() { }

    /**
     * Called before each test is run.
     */
    @BeforeEach
    public void setUp() { }

    /**
     * Called after each test has concluded
     */
    @AfterEach
    public void tearDown() { }
    
    /**
     * Tests the default constructor: {@link Fish#Fish()}.
     * Does the following three times:
     * <ol>
     *  <li>Create a new {@code Fish} using the default constructor.</li>
     *  <li>Checks the value of {@code age} to ensure it is within the expected range (0 to 4).</li>
     *  <li>Checks the value of {@code gender} to ensure it is either {@code MALE} or {@code FEMALE}.</li>
     * </ol>
     */
    @Test
    @DisplayName("Fish()")
    public void testDefaultConstructor() {
        
        for(int i = 0; i < 3; ++i) {
            
            Fish f = new Fish();
            
            assertTrue(f.getAge() >= 0 && f.getAge() <= 4); // Ensure age is in range.
            assertTrue(f.getGender() == Animal.Gender.MALE || f.getGender() == Animal.Gender.FEMALE); // Ensure gender is valid.
            
        }
        
    }
    
    /**
     * Tests the constructor: {@link Fish#Fish(int, Gender)}.
     * Does the following:
     * <ol>
     *  <li>Create a new {@code Fish} using the above constructor.</li>
     *  <li>Checks the value of {@code age} to ensure it is the value that it was set to.</li>
     *  <li>Checks the value of {@code gender} to ensure it is the value that it was set to.</li>
     *  <li>Create a new {@code Fish} using the above constructor, with an age that is out of bounds.</li>
     *  <li>Check the {@code Fish}'s age, ensuring that is 9 (the {@code Fish}'s maxAge)</li>
     *  <li>Check the {@code Fish}'s gender, ensuring that it is the value it was set to.</li>
     * </ol>
     */
    @Test
    @DisplayName("Fish(int, Gender)")
    public void testConstructor() {

        Fish f = new Fish(2, Animal.Gender.MALE);
            
        assertEquals(2, f.getAge()); // Ensure age is what it was set to.
        assertEquals(Animal.Gender.MALE, f.getGender()); // Ensure gender is as set.
        
        // Test invalid age parameter
        
        Fish g = new Fish(6, Animal.Gender.FEMALE);
        
        assertEquals(4, g.getAge()); // Ensure age is 4.
        assertEquals(Animal.Gender.FEMALE, g.getGender()); // Ensure gender is as set.
        
    }
    
    /**
     * Tests the {@link Fish#maxAge()} method:
     * <ol>
     *  <li>Tests a {@code Fish} who's age is less than the max.</li>
     *  <li>Tests a {@code Fish} who's age is the max.</li>
     * </ol>
     */
    @Test
    @DisplayName("maxAge()")
    public void testMaxAge() {
        Fish fish1 = new Fish(1, Animal.Gender.MALE);
        Fish fish2 = new Fish(4, Animal.Gender.MALE);
        
        // Only a fish who's age is 4 should have maxAge() return true.
        assertFalse(fish1.maxAge());
        assertTrue(fish2.maxAge());
    }
    
    /**
     * Tests the {@link Fish#getAge()} method.
     * Does the following 5 times:
     * <ol>
     *  <li>Generates a random number between 0 and 9 (inclusive).</li>
     *  <li>Creates a new {@code Fish} with the randomly generated age.</li>
     *  <li>Ensures that the {@code Fish#getAge()} method returns the age generated.</li>
     * </ol>
     */
    @Test
    @DisplayName("getAge()")
    public void testGetAge() {
        
        Random r = new Random();
        
        for(int i = 0; i < 5; ++i) {
            int age = r.nextInt(5); // Random age from 0 - 4.
            Fish f = new Fish(age, Animal.Gender.MALE);            
            assertEquals(age, f.getAge()); // Make sure the age returned is actually the one we specified.
        }
        
    }
    
    /**
     * Tests the {@link Fish#incrAge()} method: 
     * <ol>
     *  <li>Attempt to increment the age of a {@code Fish} who's age is less than
     *      {@code 9} (the maximum age of a {@code Fish}).</li>
     *  <li>Attempt to increment the age of a {@code Fish} who's age is {@code 9}.</li>
     * </ol>
     */
    @Test
    @DisplayName("incrAge()")
    public void testIncrAge() {
        
        Fish fish1 = new Fish(1, Animal.Gender.MALE);
        Fish fish2 = new Fish(4, Animal.Gender.MALE);
        
        assertTrue(fish1.incrAge());
        assertEquals(2, fish1.getAge());
        
        assertFalse(fish2.incrAge());
        assertEquals(4, fish2.getAge()); // Age shouldn't increment if incrAge() returns false.
        
    }
    
    /**
     * Tests the randomness of the random generation used
     * to randomly generate a {@code Fish}'s {@code age}
     * and {@code gender}.
     * 
     * Generates 100 random {@code Fish} objects. Then:
     * <ol>
     *  <li>Calculates the standard deviation and standard error of the ages of the {@code Fish} objects.</li>
     *  <li>Ensures that the mean age doesn't exceed +/- 2 * standard error (a 95% confidence interval).</li>
     *  <li>Calculates the percentages of the {@code Fish} objects' genders.</li>
     * </ol>
     */
    @Test
    @DisplayName("Randomness Test")
    public void testRandomness() {
        Fish[] fish = new Fish[100];
        for(int i = 0; i < 100; ++i) {
            fish[i] = new Fish();
        }
        
        // Summary Statistics
        
        int countMale = 0;
        int countFemale = 0;
        double meanAge = 0;
        
        for(Fish f : fish) {
            
            if(f.getGender() == Animal.Gender.MALE) {
                countMale++;
            } else {
                countFemale++;
            }
            
            meanAge += f.getAge();
            
        }
        
        meanAge /= 100.0;
        
        // Standard Deviation
        double standardDeviation = Math.sqrt(Math.pow(4, 2) / 12);
        double standardError = standardDeviation / 10; // (i.e., standardDeviation / sqrt(100))
        
        assertEquals(true, (meanAge > meanAge - (2 * standardError)) && (meanAge < meanAge + (2 * standardError)));
        System.out.println(countMale + "% male, " + countFemale + "% female");
    }
    
    /**
     * Tests the {@link Fish#toString()} method.
     * Does the following 5 times:
     * <ol>
     *  <li>Generates a random age and gender for the {@code Fish}.</li>
     *  <li>Creates the {@code Fish} object using the above two values.</li>
     *  <li>Ensures that the value returned by {@link Fish#toString()} contains
     *      each value that the {@code Fish} was instantiated with.</li>
     * </ol>
     */
    @Test
    @DisplayName("toString()")
    public void testToString() {
        Random r = new Random();
        
        for(int i = 0; i < 5; ++i) {
            int age = r.nextInt(5); // Random age from 0 - 4.

            Animal.Gender gender = Animal.generateRandomGender();
            char genderSymbol = (gender == Animal.Gender.MALE) ? 'M' : 'F'; // If gender isn't male, it must be female.
            
            Fish f = new Fish(age, gender);            
            
            String expected = "F" + genderSymbol + age;
            assertEquals(expected, f.toString());
        }
    }
    
}
