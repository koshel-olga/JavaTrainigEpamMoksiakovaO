package main.java.moksiakova.lessons.lesson2;

/**
 * Parametrized class Cache. */
public class Cache<T> {
    /**
     * Elements in cache.*/
    private CacheElement[] cache;
    /** Size of array cache. */
    private int capacity;

    /** Constructor.
     * @param capacity size of array cache.*/
    Cache(int capacity) {
        this.cache = new CacheElement[capacity];
        this.capacity = capacity;
    }

    /**
     * Check element is exist in cache.
     * @param element  element type of T {@link CacheElement<>.element}.
     * @return boolean.
     * */
    public boolean isPresent(T element) {
        for(int i=0; i<this.capacity; i++) {
            if ((this.cache[i] != null) && (this.cache[i].getElement().equals(element))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check index of element is exist in cache.
     * @param index {@link CacheElement<>.index}.
     * @return boolean.
     * */
    public boolean isPresent(int index) {
        for(int i=0; i<this.capacity; i++) {
            if ((this.cache[i] != null) && (this.cache[i].getIndex() == index)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add element in cache.
     * @param element  element type of T {@link CacheElement<>.element}.
     * @param index {@link CacheElement<>.index}.
     * */
    void add(T element, int index) {
        CacheElement addElement = new CacheElement(element,index);
        int freeIndex = -1;
        for(int i=0; i<this.capacity; i++) {
            if (this.cache[i] == null) { freeIndex = i; break;}
        }
        if (freeIndex > 0) {
            this.cache[freeIndex] = addElement;
        } else {
            this.moveArrayToLeft(0);
            this.cache[this.capacity-1] = addElement;
        }
        System.out.println("add element "+element.toString()+" to cache");
    }

    /**
     * Delete element from cache.
     * @param element  element type of T {@link CacheElement<>.element}.
     * */
    public void delete(T element) {
        for( int i=0; i<this.capacity; i++) {
            if (this.cache[i].getElement().equals(element)) {
                this.moveArrayToLeft(i);
                break;
            }
        }
        this.cache[this.capacity-1] = null;
        System.out.println("delete element "+element.toString()+" from cache\n");
    }

    /**
     * Get element from cache an index.
     * @param index {@link CacheElement<>.index}.
     * @return element  type of T {@link CacheElement<>.element}.
     * */
    public T get(int index) {
        for( int i=0; i<this.capacity; i++) {
            if (this.cache[i] == null) {return null;}
            if (this.cache[i].getIndex() == index) {
                T element = (T) this.cache[i].getElement();
                this.moveArrayToLeft(i);
                this.add(element, index);
                return element;
            }
        }
        return null;
    }

    /**
     * Clear cache. */
    public void clear() {
        this.cache = new CacheElement[capacity];
    }

    /**
     * Move elements of array of cache to left.
     * @param indexFrom index of begin to move. */
    private void moveArrayToLeft(int indexFrom) {
        for(int i=indexFrom; i<this.capacity-1; i++) {
            this.cache[i] = this.cache[i+1];
        }
    }
}
