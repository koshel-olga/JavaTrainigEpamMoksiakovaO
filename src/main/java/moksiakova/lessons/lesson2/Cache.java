package moksiakova.lessons.lesson2;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * Parametrized class Cache. */
@Slf4j
public class Cache<T> {
    /**
     * Elements in cache.*/
    @Getter
    private CacheElement[] cache;
    /** Size of array cache. */
    @Getter
    private int capacity;


    /** Constructor.
     * @param capacity size of array cache.*/
    public Cache(int capacity) {
        if ( capacity < 0 ) {
            log.error(String.format("Illegal argument capacity %d", capacity));
            throw new IllegalCacheArgumentException(String.format("Illegal argument capacity %d", capacity));}
        this.capacity = capacity;
        this.cache = new CacheElement[capacity];
    }

    /**
     * Check element is exist in cache.
     * @param element  element type of T {@link CacheElement<>.element}.
     * @return boolean.
     * */
    public boolean isPresent(T element) {
        for(int i=0; i<this.capacity; i++) {
            if ( this.cache[i] != null && this.cache[i].getElement().equals(element) ) {
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
            if ( this.cache[i] != null && this.cache[i].getIndex() == index ) {
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
    public void add(T element, int index) {
        CacheElement addElement = new CacheElement(element,index);
        for(int i=0; i<this.capacity; i++) {
            if (this.cache[i] == null) {
                this.cache[i] = addElement;
                log.info("add element {} to cache\n",element.toString());
                return;
            }
        }
        this.moveArrayToLeft(0);
        this.cache[this.capacity-1] = addElement;
        log.info("add element {} to cache\n",element.toString());
    }

    /**
     * Delete element from cache.
     * @param element  element type of T {@link CacheElement<>.element}.
     * */
    public void delete(T element) {
        for( int i=0; i<this.capacity; i++) {
            if ( this.cache[i].getElement().equals(element) ) {
                this.moveArrayToLeft(i);
                this.cache[this.capacity-1] = null;
                log.info("delete element {} from cache\n",element.toString());
                break;
            }
        }
    }

    /**
     * Get element from cache an index.
     * @param index {@link CacheElement<>.index}.
     * @return element  type of T {@link CacheElement<>.element}.
     * */
    public T get(int index) {
        for( int i=0; i<this.capacity; i++) {
            if ( this.cache[i] == null ) { return null; }
            if ( this.cache[i].getIndex() == index ) {
                T element = (T) this.cache[i].getElement();
                this.delete(element);
                this.add(element, index);
                return element;
            }
        }
        return null;
    }

    /**
     * Clear cache. */
    public void clear() {
        for( int i=0; i<this.capacity; i++) {
            this.cache[i] = null;
        }
    }

    /**
     * Move elements of array of cache to left.
     * @param indexFrom index of begin to move. */
    private void moveArrayToLeft(int indexFrom) {
        for( int i=indexFrom; i<this.capacity-1; i++ ) {
            this.cache[i] = this.cache[i+1];
        }
    }
}
