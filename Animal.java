import java.util.Random;

/**
 * 
 * Models an animal in the river ecosystem.
 * 
 * @author Jackson Eshbaugh
 * @version 1/27/2024
 */
public abstract class Animal {

    /**
     * Generates a random gender.
     * There is a uniformly distributed
     * chance for each gender.
     * 
     * @return The random gender, either {@code Gender#MALE}
     * or {@code Gender#FEMALE}.
     */
    public static Gender generateRandomGender() {

        Random r = new Random();

        int randomGender = r.nextInt(2);
        switch (randomGender) {
            case 0:
                return Gender.MALE;
            default:
                return Gender.FEMALE;
        }

    }

    /**
     * Represents the gender of
     * an animal.
     */
    protected enum Gender {
        MALE, FEMALE;
    }

    protected Gender gender;
    protected int age;

    /**
     * Creates an animal of
     * random age and gender.
     */
    public Animal() {
        Random r = new Random();
        this.gender = generateRandomGender();
        this.age = r.nextInt();
    }

    /**
     * Creates an animal of
     * specified age and gender.
     * 
     * @param age The age of the animal
     * @param gender The gender of the animal
     */
    public Animal(int age, Gender gender) {
        this.age = age;
        this.gender = gender;
    }

    /**
     * Gets the age of
     * this animal.
     * 
     * @return The animal's age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Gets the gender
     * of this animal.
     * 
     * @return The animal's gender
     */
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Determines whether this animal is
     * at its maximum age.
     * 
     * @return True if the animal is at its max age, false otherwise.
     */
    abstract boolean maxAge();

    /**
     * Increments the animal age by
     * one, if possible.
     * 
     * @return True if the operation is successful, false 
     * if the operation failed and the age remains as it was.
     */
    abstract boolean incrAge();

    /**
     * Renders this animal as a
     * String value of the form
     * {@code GA}, where {@code G}
     * is gender and {@code A} is age.
     * 
     * @return the String value "GA" described above.
     */
    public String toString() {

        String string = "";

        switch(gender) {
            case MALE:
                string += "M";
                break;
            default:
                string += "F";
                break;
        }

        string += age;

        return string;
    }
}