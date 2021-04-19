package com.epamlearning.moksiakova.lessons.lesson8;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Parametrized class.
 * */
@Slf4j
@NoArgsConstructor
public class Storage<T> {

    @Getter
    private Object[] storage;

    @Getter
    private int capacity = 10;

    @Getter
    private int lastIndex;

    @Getter
    @Setter
    private Cache<T> cache;

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

    /**
     * @param element element type of T. */
    public void addElement(T element) {

        if (this.lastIndex == this.capacity-1) {
            this.createLargerStorage(this.capacity, 1.5);
        }
        this.lastIndex++;
        this.storage[this.lastIndex] = element;
    }

    /**
     * Delete last element from storage and delete this element from cache.
     */
    public void deleteLastElement() {

        if (this.lastIndex > 0) {
            T lastElement = this.getLastElement();
            if (this.cache.isPresentElement(lastElement)) {
                this.cache.deleteElementFromCache(lastElement);
            }
            log.info("delete last index = {}",this.lastIndex);
            this.storage[this.lastIndex] = null;
            this.lastIndex--;
        }
    }

    /** Clear cache and storage. */
    public void clearStorage() {

        this.cache.clearCache();
        for( int i=0; i<this.capacity; i++) {
            this.storage[i] = null;
        }
        this.lastIndex = 0;
    }

    /**
     * @return element type of T. */
    @SuppressWarnings("unchecked")
    public T getLastElement() {

        return (T) this.storage[this.lastIndex];
    }

    /**
     * @param index the index of the element in the array. */
    @SuppressWarnings("unchecked")
    public T getElementByIndex(int index) throws StorageIndexOutOfRange {

        if (this.cache.isPresentIndex(index)) {
            return this.cache.getElementByIndex(index);
        }
        if (index < this.capacity) {
            this.cache.addElementToCache((T) this.storage[index], index);
            return (T) this.storage[index];
        } else {
            String exceptionMessage = String.format("Index %d out of range Storage.", index);
            log.error(exceptionMessage);
            throw new StorageIndexOutOfRange(exceptionMessage);
        }
    }

    /**
     * @param beginCapacity the number from which we increase.
     * @param param number what time increases.*/
    private void createLargerStorage(int beginCapacity, double param) {

        if (beginCapacity >= 0) {
            this.capacity = (int) (beginCapacity*param);
            Object[] newStorage = new Object[this.capacity];
            System.arraycopy(this.storage, 0, newStorage, 0, beginCapacity);
            this.storage = newStorage;
            log.info("Create larger array. New length {}.",this.capacity);
        }
    }
}
