import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Random;

/**
 * Test Class for {@link AnimalArrayList}.
 * 
 * Ensures that all functionality is working properly for {@link AnimalArrayList} objects.
 * 
 * @author Jackson Eshbaugh
 * @version 02/10/2024
 */
public class AnimalArrayListTest {
    
    AnimalArrayList a;

    /**
     * Creates a new {@code AnimalArrayListTest} object.
     */
    public AnimalArrayListTest() {
    }

    /**
     * Called before each test is called.
     */
    @BeforeEach
    public void setUp() {
        a = new AnimalArrayList();
    }

    /**
     * Called after each test is called.
     */
    @AfterEach
    public void tearDown() { }
    
    
    /**
     * Tests the {@link AnimalArrayList#AnimalArrayList()} constructor:
     * Enusres a newly created list has a {@code size} of {@code 0}.
     */
    @Test
    @DisplayName("AnimalArrayList()")
    public void testDefaultConstructor() {
        
        AnimalArrayList list = new AnimalArrayList();
        assertEquals(0, list.size());
        
    }
    
    /**
     * Tests the {@link AnimalArrayList#AnimalArrayList(int)} constructor:
     * Creates random int values, then initiates animal array lists to their sizes.
     * Finally, ensures that the {@code size} value does not change based on this.
     */
    @Test
    @DisplayName("AnimalArrayList(int)")
    public void testConstructorInt() {
        
        Random r = new Random();
        
        // Test this 10 times.
        for (int i = 0; i < 10; ++i)  {
            AnimalArrayList list = new AnimalArrayList(r.nextInt(101));
            assertEquals(0, list.size());
        }
    }
    
    
    /**
     * Tests the {@link AnimalArrayList#add(Animal)} method:
     * <ol>
     *  <li>Adds a {@code Fish} to the list.</li>
     *  <li>Adds some {@code Bear}s to the list.</li>
     *  <li>Tries to add {@code null} to the list.</li>
     * </ol>
     */
    @Test
    @DisplayName("add(Animal)")
    public void testAdd() {
        
        // Add a Fish
        a.add(new Fish());
        assertEquals(1, a.size());
        
        // Add 3 bears
        a.add(new Bear());
        a.add(new Bear());
        a.add(new Bear());
        assertEquals(4, a.size());
        
        // Try to add null
        a.add(null);
        assertEquals(4, a.size());
        
    }
    
    /**
     * Tests the {@link AnimalArrayList#remove(Animal)} method.
     * From a prepared (pre-filled) list:
     * <ol>
     *  <li>Tries to remove an {@code Animal} that is in the list.</li>
     *  <li>Tries to remove an {@code Animal} that isn't in the list.</li>
     *  <li>Tries to remove {@code null} from the list.</li>
     * </ol>
     */
    @Test
    @DisplayName("remove(Animal)")
    public void testRemoveAnimal() {
        // Prime the array
        Fish f = new Fish(2, Animal.Gender.MALE);
        Bear b = new Bear();
        a.add(f);
        a.add(new Fish(3, Animal.Gender.MALE));
        a.add(new Fish(3, Animal.Gender.MALE));
        a.add(new Fish(3, Animal.Gender.MALE));
        assertEquals(4, a.size());
        
        // Try to remove Fish f
        a.remove(f);
        assertEquals(3, a.size());
        
        // Try to remove Bear b
        a.remove(b);
        assertEquals(3, a.size());
        
        // Try to remove null
        a.remove(null);
        assertEquals(3, a.size());
    }
    
    /**
     * Tests the {@link AnimalArrayList#remove(int)} method.
     * From a prepared (pre-filled) list:
     * <ol>
     *  <li>Tries to remove a valid index from the list.</li>
     *  <li>Tries to remove an invalid index from the list.</li>
     *  <li>Tries to remove a negative index from the list.</li>
     * </ol>
     */
    @Test
    @DisplayName("remove(int)")
    public void testRemoveIndex() {
        // Prime the array
        Fish f = new Fish(2, Animal.Gender.MALE);
        Fish f1 = new Fish(), f2 = new Fish();
        a.add(f);
        a.add(f1);
        a.add(new Fish(3, Animal.Gender.MALE));
        a.add(f2);
        assertEquals(4, a.size());
        
        // Try to remove valid indicies:
        // 3, 1, 0
        
        assertEquals(f2, a.remove(3));
        assertEquals(f1, a.remove(1));
        assertEquals(f, a.remove(0));
        
        // Try to remove an invalid index:
        // 100, -2
        // Expect two exeptions to be thrown.
        
        boolean negativeBoundary = false, positiveBoundary = false;
        
        try {
            a.remove(100);
        } catch (IndexOutOfBoundsException e) {
            positiveBoundary = true;
        }
        
        try {
            a.remove(-2);
        } catch (IndexOutOfBoundsException e) {
            negativeBoundary = true;
        }
        
        assertTrue(negativeBoundary);
        assertTrue(positiveBoundary);
    }
    
    /**
     * Tests the {@link AnimalArrayList#find(Animal)} method:
     * <ol>
     *  <li>Tries to find an {@code Animal} that's in the list.</li>
     *  <li>Tries to find an {@code Animal} that isn't in the list.</li>
     *  <li>Tries to find a {@code null} value in the list.</li>
     * </ol>
     */
    @Test
    @DisplayName("find(Animal)")
    public void testFind() {
        // Prime the array
        
        Fish f = new Fish();        
        a.add(f);
        
        // Finding an animal in the array
        assertEquals(0, a.find(f));
        
        // Trying to find an animal that isn't in the array
        assertEquals(-1, a.find(new Bear()));
        
        // Trying to find null in the array
        assertEquals(-1, a.find(null));
    }
    
    /**
     * Tests the {@link AnimalArrayList#size()} method:
     * <ol>
     *  <li>Verifies the size of a new list is {@code 0}.</li>
     *  <li>Checks the list size after adding items.</li>
     *  <li>Checks the list size after removing items.</li>
     * </ol>
     */
    @Test
    @DisplayName("size()")
    public void testSize() {
        // Ensure that an empty list's size is 0.
        assertEquals(0, a.size());
        
        // Add items, then ensure size has updated correctly.
        a.add(new Fish());
        assertEquals(1, a.size());
        a.add(new Fish());
        assertEquals(2, a.size());
        a.add(new Fish());
        assertEquals(3, a.size());
        
        // Remove items, then ensure size has updated correctly.
        a.remove(0);
        assertEquals(2, a.size());
        a.remove(0);
        assertEquals(1, a.size());
        a.remove(0);
        assertEquals(0, a.size());
    }
    
    /**
     * Tests the {@link AnimalArrayList#clear()} method:
     * <ol>
     *  <li>Tests an empty list.</li>
     *  <li>Tests a list with items (should have a {@code size}
     *      of 0 and accessing any item shouldn't work after
     *      clearing it)</li>
     * </ol>
     */
    @Test
    @DisplayName("clear()")
    public void testClear() {
        
        // Empty List
        
        a.clear();
        assertEquals(0, a.size());
        
        // List with items:
        
        Fish f = new Fish();
        
        a.add(f);
        a.add(f);
        a.add(f);
        
        assertEquals(3, a.size());
        assertEquals(f, a.get(0));
        
        a.clear();
        
        assertEquals(0, a.size());
        
        boolean threwException = false;
        
        try {
            assertEquals(f, a.get(0));
        } catch (IndexOutOfBoundsException e) {
            threwException = true;
        }
        
        assertTrue(threwException); // There should be no index 0; the list should be empty.
        
    }
    
    /**
     * Tests the {@link AnimalArrayList#get(int)} method:
     * <ol>
     *   <li>Tests an {@code index} too large for the list's boundaries.</li>
     *   <li>Tests an {@code index} too small for the list's boundaries.</li>
     *   <li>Tests an {@code index} within the list's boundaries.</li>
     *   <li>Tests an {@code index} at the beginning the list's boundary (i.e., {@code 0}).</li>
     *   <li>Tests an {@code index} at the end of the list's boundary (i.e., {@link AnimalArrayList#size()} {@code - 1}).</li>
     * </ol>
     */
    @Test
    @DisplayName("get() Tests")
    public void testGet() {
        boolean lowerBoundException = false, upperBoundException = false;
        
        Fish fish1 = new Fish(), fish2 = new Fish();
        Bear bear1 = new Bear(), bear2 = new Bear();
        
        a.add(fish1);
        a.add(bear1);
        a.add(fish2);
        a.add(bear2);
        
        try {
            a.get(-1);
        } catch (Exception e) {
            lowerBoundException = true;
        }
        
        try {
            a.get(a.size());
        } catch (Exception e) {
            upperBoundException = true;
        }
        
        assertTrue(lowerBoundException);
        assertTrue(upperBoundException);
        
        // Middle of the list
        assertEquals(fish2, a.get(2));
        assertEquals(bear1, a.get(1));
        
        // Beginning and end of list
        assertEquals(fish1, a.get(0));
        assertEquals(bear2, a.get(a.size() - 1));
        
    }
    
    /**
     * Tests the {@link AnimalArrayList#resize(int)} method:
     * <ol>
     *  <li>Grows an array by adding values and ensures
     *      that each value can still be added.</li>
     *  <li>Removes these values and ensures that each is removed.</li>
     * </ol>
     */
    @Test
    @DisplayName("resize(int)")
    public void testResize() {
        
        // By default, AnimalArrayList will grow or shrink
        // by a factor of 10 each time. Add 15 elements, ensuring that each
        // is added.
        
        for(int i = 0; i < 16; ++i) {
            a.add(new Fish());
            assertEquals(i + 1, a.size());
        }
        
        // Now, remove each of these and make sure that they
        // are actually removed.
        
        for(int i = 15; i >= 0; --i) {
            assertEquals(i + 1, a.size());
            a.remove(i);
        }
        
    }
}
