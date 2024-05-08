/**
 * Implementation of {@link AnimalList} that uses an array-based
 * approach. An array is grown with the list as the list grows, and a
 * seperate size variable allows for array length to exceed list
 * length.
 * 
 * @author Jackson Eshbaugh
 * @version 02/10/2024
 */
public class AnimalArrayList implements AnimalList {
    
    private Animal[] animals;
    private int size, growthFactor;
    
    /**
     * Creates a new {@code AnimalArrayList} with
     * a {@code capacity} unit long array to start.
     * 
     * @param capacity the starting capacity of the {@link Animal} array
     */
    public AnimalArrayList(int capacity) {
        
        // Must have a positive capacity
        if(capacity < 0)
            capacity = 10;
        
        this.animals = new Animal[capacity];
        this.growthFactor = capacity;
        this.size = 0;
    }
    
    /**
     * Creates a new {@code AnimalArrayList} with
     * a {@code 10} unit long array to start.
     */
    public AnimalArrayList() {
        this(10);
    }
    
    /**
     * Gets the number of empty slots in the array.
     * 
     * @return the number of empty slots in the array
     */
    private int empty() {
        return animals.length - this.size();
    }

    /**
     * Resizes the array to a newer (practically, larger) size, and
     * copies all the data into the new array.
     * 
     * @param newSize the new size of the array
     */
    private void resize(int newSize) {
        Animal[] newArray = new Animal[newSize];
        
        for(int i = 0; i < size; ++i) {
            newArray[i] = animals[i];
        }
        
        animals = newArray;
    }
    
    public int size() {
        return this.size;
    }
    
    public void add(Animal animal) {
        
        // Don't try to add null to the array.
        if(animal == null) {
            return;
        }
        
        if(size == animals.length) {
            // Need to resize the array.
            resize(size + growthFactor);
        }
        
        // Simply add the animal, and increase the size by 1.
        animals[size] = animal;
        size++;
    }
    
    public Animal remove(int index) {
        if(index <= 0 && index >= size) {
            // The index is out of range.
            throw new IndexOutOfBoundsException(
                "Index out of bounds for AnimalArrayList.");
        }
        
        Animal a = animals[index];
        
        // Note: if the loop is skipped, the last item in the list
        // has been removed. Updating size is enough for this, as it will be
        // overwritten by the next object added to the array.
        for(int i = index; i < size - 1; ++i) {
            animals[i] = animals[i + 1];
        }
        
        // Update the length of the array.
        size--;
        
        // Size down the array, if needed
        if(empty() > growthFactor && animals.length > growthFactor) {
            resize(animals.length - growthFactor);
        }
        
        return a;
    }
    
    public Animal get(int index) {
        if(index < 0 || index >= size) {
            // The index is out of range.
            throw new IndexOutOfBoundsException("Index out of bounds for AnimalArrayList.");
        }
        
        return animals[index];
    }
    
    public void clear() {
        animals = new Animal[growthFactor];
        size = 0;
    }
    
    public int find(Animal animal) {
        
        // If animal is null, return -1 (not in the list)
        if(animal == null) {
            return -1;
        }
        
        for(int i = 0; i < size; ++i) {
            // If we find the animal, return its index
            // and exit the loop (and method).
            if(animals[i].equals(animal))
                return i;
        }
        
        // If the loop completes, the animal wasn't found.
        return -1;
    }
    
    public void remove(Animal animal) {
        int index = find(animal);
         
        if(index > -1) {
            // The animal was found in the array,
            // so remove it.
            remove(index);
        }
    }
}