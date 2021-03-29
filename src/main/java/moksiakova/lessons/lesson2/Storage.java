package main.java.moksiakova.lessons.lesson2;

import java.util.Objects;

/**
 * Parametrized class.
 * */
public class Storage<T> {
    private Object[] storage;
    private int capacity = 10;
    private int lastIndex;
    private Cache<T> cache;

    public Storage() {
        this.cache = new Cache<>(this.capacity);
        this.storage = new Objects[this.capacity];
    }

    public Storage(T[] storageElements) {
        if (this.capacity < storageElements.length) {
            this.capacity = storageElements.length;
        }
        this.storage = new Object[this.capacity];
        this.cache = new Cache<>(this.capacity);
        for(int i=0; i<storageElements.length; i++) {
            if (storageElements[i] == null) {
                break;
            }
            this.storage[i] = storageElements[i];
            this.lastIndex = i;
        }
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Add element in storage.
     * @param element element type of T. */
    public void add(T element) {
        if (this.lastIndex == this.capacity-1) {
            this.createLargerArray(this.capacity, 1.5);
        }
        this.lastIndex++;
        this.storage[this.lastIndex] = element;
    }

    /**
     * Delete last element from storage and delete this element from cache.
     */
    public void delete() {
        T lastElement = this.getLast();
        if (this.lastIndex > 0) {
            if (this.cache.isPresent(lastElement)) {
                this.cache.delete(lastElement);
            }
            System.out.printf("delete last index = %d\n", this.lastIndex);
            this.storage[this.lastIndex] = null;
            this.lastIndex--;
        }
    }

    /** Clear cache and storage. */
    public void clear() {
        this.cache.clear();
        this.storage = new Objects[this.capacity];
        this.lastIndex = 0;
    }

    /**
     * Get last element from storage.
     * @return element type of T. */
    @SuppressWarnings("unchecked")
    public T getLast() {
        return (T) this.storage[this.lastIndex];
    }

    /**
     * Get element from storage by index.
     * @param index the index of the element in the array. */
    @SuppressWarnings("unchecked")
    public T get(int index) throws Exception {
        if (this.cache.isPresent(index)) {
            System.out.println("get element from cache\n");
            return this.cache.get(index);
        }
        if (index < this.capacity) {
            T element = (T) this.storage[index];
            this.cache.add(element, index);
            return element;
        }
        throw new Exception("Index "+index+"out of range.");
    }

    /**
     * Create larger array.
     * @param beginCapacity the number from which we increase.
     * @param param number what time increases.*/
    private void createLargerArray(int beginCapacity, double param) {
        if (beginCapacity >= 0) {
            this.capacity = (int) (beginCapacity*param);
            Object[] newStorage = new Object[this.capacity];
            System.arraycopy(this.storage, 0, newStorage, 0, beginCapacity);
            this.storage = newStorage;
        }
    }
}
