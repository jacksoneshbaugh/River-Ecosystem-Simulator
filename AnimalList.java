/**
 * Provides the structure for which to create a
 * list of multiple {@link Animal} objects, which can grow and change
 * in size.
 * 
 * @author Jackson Eshbaugh
 * @version 02/10/2024
 */
public interface AnimalList {
    
    /**
     * Appends an animal to the end of the list.
     * 
     * @param animal the {@link Animal} object to add to the list
     */
    void add(Animal animal);
    
    /**
     * Searches the list for {@code animal}
     * and removes it from the list, if found.
     * 
     * @param animal the {@link Animal} to remove from the list
     */
    void remove(Animal animal);
    
    /**
     * Removes an animal from the list
     * by its index.
     * 
     * @param index the index to remove from the list
     * @return the {@code Animal} that was removed from the list
     * @throws IndexOutOfBoundsException when the {@code index} is too small
     * or too large
     */
    Animal remove(int index);
    
    /**
     * Gets the list element at the specifed index.
     * 
     * @throws IndexOutOfBoundsException when the index is not in the boundary of [0, size() -1].
     * @param index the index from which to retrieve an element
     * @return the element at the specified index
     * 
     */
    Animal get(int index);
    
    /**
     * Finds the specified {@code animal} in the list, 
     * and returns its index.
     * 
     * @param animal the {@link Animal} to find in the list
     * @return the index of {@code animal} or {@code -1} if {@code animal}
     * was not found
     */
    int find(Animal animal);
    
    /**
     * Clears the list, returning it to its
     * default, empty state.
     */
    void clear();
    
    /**
     * Gets the current size of the list.
     * 
     * @return the current size of the list
     */
    int size();
}