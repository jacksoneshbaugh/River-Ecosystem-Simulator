import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Random;

/**
 * Test class for the {@link Bear} class.
 * 
 * @author Jackson Eshbaugh
 * @version 02/09/2024
 */
public class BearTest {

    /**
     * Creates a new BearTest object.
     */
    public BearTest() { }

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
     * Tests the default constructor: {@link Bear#Bear()}.
     * Does the following three times:
     * <ol>
     *  <li>Create a new {@code Bear} using the default constructor.</li>
     *  <li>Checks the value of {@code age} to ensure it is within the expected range (0 to 9).</li>
     *  <li>Checks the value of {@code gender} to ensure it is either {@code MALE} or {@code FEMALE}.</li>
     * </ol>
     */
    @Test
    @DisplayName("Bear()")
    public void testDefaultConstructor() {
        
        for(int i = 0; i < 3; ++i) {
            
            Bear b = new Bear();
            
            assertTrue(b.getAge() >= 0 && b.getAge() <= 9); // Ensure age is in range
            assertTrue(b.getGender() == Animal.Gender.MALE || b.getGender() == Animal.Gender.FEMALE); // Ensure gender is valid.
            
        }
        
    }
    
    /**
     * Tests the constructor: {@link Bear#Bear(int, Gender)}.
     * Does the following:
     * <ol>
     *  <li>Create a new {@code Bear} using the above constructor.</li>
     *  <li>Checks the value of {@code age} to ensure it is the value that it was set to.</li>
     *  <li>Checks the value of {@code gender} to ensure it is the value that it was set to.</li>
     *  <li>Create a new {@code Bear} using the above constructor, with an age that is out of bounds.</li>
     *  <li>Check the {@code Bear}'s age, ensuring that is 9 (the {@code Bear}'s maxAge)</li>
     *  <li>Check the {@code Bear}'s gender, ensuring that it is the value it was set to.</li>
     * </ol>
     */
    @Test
    @DisplayName("Bear(int, Gender)")
    public void testConstructor() {

        Bear b = new Bear(4, Animal.Gender.MALE);
            
        assertEquals(4, b.getAge()); // Ensure age is what it was set to.
        assertEquals(Animal.Gender.MALE, b.getGender()); // Ensure gender is as set.
        
        // Test invalid age parameter //
        
        Bear c = new Bear(15, Animal.Gender.FEMALE);
        
        assertEquals(9, c.getAge()); // Ensure age is 9.
        assertEquals(Animal.Gender.FEMALE, c.getGender()); // Ensure gender is as set.
        
    }
    
    /**
     * Tests the {@link Bear#maxAge()} method:
     * <ol>
     *  <li>Tests a {@code Bear} who's age is less than the max.</li>
     *  <li>Tests a {@code Bear} who's age is the max.</li>
     * </ol>
     */
    @Test
    @DisplayName("maxAge()")
    public void testMaxAge() {
        Bear bear1 = new Bear(2, Animal.Gender.MALE);
        Bear bear2 = new Bear(9, Animal.Gender.MALE);
        
        // Only a bear who's age is 9 should have maxAge() return true.
        assertFalse(bear1.maxAge());
        assertTrue(bear2.maxAge());
    }
    
    /**
     * Tests the {@link Bear#getAge()} method.
     * Does the following 5 times:
     * <ol>
     *  <li>Generates a random number between 0 and 9 (inclusive).</li>
     *  <li>Creates a new {@code Bear} with the randomly generated age.</li>
     *  <li>Ensures that the {@code Bear#getAge()} method returns the age generated.</li>
     * </ol>
     */
    @Test
    @DisplayName("getAge()")
    public void testGetAge() {
        
        Random r = new Random();
        
        for(int i = 0; i < 5; ++i) {
            int age = r.nextInt(10); // Random age from 0 - 9.
            Bear b = new Bear(age, Animal.Gender.MALE);            
            assertEquals(age, b.getAge()); // Make sure the age returned is actually the one we specified.
        }
        
    }
    
    /**
     * Tests the {@link Bear#incrAge()} method: 
     * <ol>
     *  <li>Attempt to increment the age of a {@code Bear} who's age is less than
     *      {@code 9} (the maximum age of a {@code Bear}).</li>
     *  <li>Attempt to increment the age of a {@code Bear} who's age is {@code 9}.</li>
     * </ol>
     */
    @Test
    @DisplayName("incrAge()")
    public void testIncrAge() {
        
        Bear bear1 = new Bear(4, Animal.Gender.MALE);
        Bear bear2 = new Bear(9, Animal.Gender.MALE);
        
        assertTrue(bear1.incrAge());
        assertEquals(5, bear1.getAge());
        
        assertFalse(bear2.incrAge());
        assertEquals(9, bear2.getAge()); // Age shouldn't increment if incrAge() returns false.
        
    }
    
    /**
     * Tests the {@link Bear#getStrength()} method.
     * <ol>
     *  <li>Create a {@code Bear} of each age ({@code 0} to {@code 9}).</li>
     *  <li>Check each {@code Bear}'s strength to ensure that they match up
     *      with what is expected for strength.</li>
     * </ol>
     */
    @Test
    @DisplayName("getStrength()")
    public void testGetStrength() {
        Bear[] bears = {
            new Bear(0, Animal.Gender.MALE),
            new Bear(1, Animal.Gender.MALE),
            new Bear(2, Animal.Gender.MALE),
            new Bear(3, Animal.Gender.MALE),
            new Bear(4, Animal.Gender.MALE),
            new Bear(5, Animal.Gender.MALE),
            new Bear(6, Animal.Gender.MALE),
            new Bear(7, Animal.Gender.MALE),
            new Bear(8, Animal.Gender.MALE),
            new Bear(9, Animal.Gender.MALE)
        };
        
        // in ascending order of age, a Bear's strength value at that age.
        int[] strengthValues = {1, 2, 3, 4, 5, 4, 3, 2, 1, 0}; 
        
        // Compare bears and defined strength values to ensure they are the same.
        for(int i = 0; i < bears.length; ++i) {
            assertEquals(strengthValues[i], bears[i].getStrength());
        }
        
    }
    
    /**
     * Tests the randomness of the random generation used
     * to randomly generate a {@code Bear}'s {@code age}
     * and {@code gender}.
     * 
     * Generates 100 random {@code Bear} objects. Then:
     * <ol>
     *  <li>Calculates the standard deviation and standard error of the ages of the {@code Bear}s.</li>
     *  <li>Ensures that the mean age doesn't exceed +/- 2 * standard error (a 95% confidence interval).</li>
     *  <li>Calculates the percentages of the {@code Bear}s' genders.</li>
     * </ol>
     */
    @Test
    @DisplayName("Randomness Test")
    public void testRandomness() {
        Bear[] bears = new Bear[100];
        for(int i = 0; i < 100; ++i) {
            bears[i] = new Bear();
        }
        
        // Summary Statistics
        
        int countMale = 0;
        int countFemale = 0;
        double meanAge = 0;
        
        for(Bear b : bears) {
            
            if(b.getGender() == Animal.Gender.MALE) {
                countMale++;
            } else {
                countFemale++;
            }
            
            meanAge += b.getAge();
            
        }
        
        meanAge /= 100.0;
        
        // Standard Deviation
        double standardDeviation = Math.sqrt(Math.pow(4, 2) / 12);
        double standardError = standardDeviation / 10; // (i.e., standardDeviation / sqrt(100))
        
        assertEquals(true, (meanAge > meanAge - (2 * standardError)) && (meanAge < meanAge + (2 * standardError)));
        System.out.println(countMale + "% male, " + countFemale + "% female");
    }
    
    /**
     * Tests the {@link Bear#toString()} method.
     * Does the following 5 times:
     * <ol>
     *  <li>Generates a random age and gender for the {@code Bear}.</li>
     *  <li>Creates the {@code Bear} object using the above two values.</li>
     *  <li>Ensures that the value returned by {@link Bear#toString()} contains
     *      each value that the {@code Bear} was instantiated with.</li>
     * </ol>
     */
    @Test
    @DisplayName("toString()")
    public void testToString() {
        Random r = new Random();
        
        for(int i = 0; i < 5; ++i) {
            int age = r.nextInt(10); // Random age from 0 - 9.

            Animal.Gender gender = Animal.generateRandomGender();
            char genderSymbol = (gender == Animal.Gender.MALE) ? 'M' : 'F'; // If gender isn't male, it must be female.
            
            Bear b = new Bear(age, gender);            
            
            String expected = "B" + genderSymbol + age;
            assertEquals(expected, b.toString());
        }
    }
}
