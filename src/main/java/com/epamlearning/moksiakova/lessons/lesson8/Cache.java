package com.epamlearning.moksiakova.lessons.lesson8;

import com.epamlearning.moksiakova.lessons.lesson8.exception.ElementNotFoundInCacheException;
import com.epamlearning.moksiakova.lessons.lesson8.exception.IllegalCacheArgumentException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Parametrized class Cache. */
@Slf4j
public class Cache<T> {

    /**
     * Elements in cache.*/
    @Getter
    private final CacheElement<T>[] cache;

    /** Size of array cache. */
    @Getter
    private final int capacity;

    /** @param capacity size of array cache.*/
    @SuppressWarnings("unchecked")
    public Cache(int capacity) {
        if ( capacity < 0 ) {
            log.error("Error create Cache: Illegal argument capacity {}", capacity);
            throw new IllegalCacheArgumentException(String.format("Illegal argument capacity %d", capacity));
        }
        this.capacity = capacity;
        this.cache = new CacheElement[capacity];
        log.debug("Create object Cache.");
    }

    /**
     * Check element is exist in cache.
     * @param element  element type of T {@link CacheElement<>.element}.
     * @return boolean.
     * */
    public boolean isPresentElement(T element) {
        for(int i=0; i<this.capacity; i++) {
            if ( this.cache[i] != null && this.cache[i].getElement().equals(element) ) {
                log.debug("{} element exist in cache.",element);
                return true;
            }
        }
        log.debug("{} element not exist in cache.",element);
        return false;
    }

    /**
     * Check index of element is exist in cache.
     * @param index {@link CacheElement<>.index}.
     * @return boolean.
     * */
    public boolean isPresentIndex(int index) {
        for(int i=0; i<this.capacity; i++) {
            if ( this.cache[i] != null && this.cache[i].getIndex() == index ) {
                log.debug("Element with index {} exist in cache.",index);
                return true;
            }
        }
        log.debug("Element with index {} not exist in cache.",index);
        return false;
    }

    /**
     * @param element  element type of T {@link CacheElement<>.element}.
     * @param index {@link CacheElement<>.index}.
     * */
    public void addElementToCache(T element, int index) {
        CacheElement<T> addElement = new CacheElement<>(element,index);
        for(int i=0; i<this.capacity; i++) {
            if (this.cache[i] == null) {
                this.cache[i] = addElement;
                log.info("Add element {} to cache",element.toString());
                return;
            }
        }
        this.moveElementsCacheToLeft(0);
        this.cache[this.capacity-1] = addElement;
        log.info("Add element {} to cache.", element.toString());
    }

    /**
     * @param element  element type of T {@link CacheElement<>.element}.
     * */
    public void deleteElementFromCache(T element) {
        for( int i=0; i<this.capacity; i++) {
            if ( this.cache[i].getElement().equals(element) ) {
                this.moveElementsCacheToLeft(i);
                this.cache[this.capacity-1] = null;
                log.info("Delete element {} from cache.", element);
                break;
            }
        }
    }

    /**
     * @param index {@link CacheElement<>.index}.
     * @return element  type of T {@link CacheElement<>.element}.
     * @throws ElementNotFoundInCacheException
     * */
    public T getElementByIndex(int index) throws ElementNotFoundInCacheException {
        for( int i=0; i<this.capacity; i++ ) {
            if ( this.cache[i] != null ) {
                if ( this.cache[i].getIndex() == index ) {
                    T element = this.cache[i].getElement();
                    this.deleteElementFromCache(element);
                    this.addElementToCache(element, index);
                    log.debug("Get element {} by index {} from Cache", element, index);
                    return element;
                }
            }
        }
        String exceptionMessage = String.format("Not found element in cache by index %d", index);
        log.error(exceptionMessage);
        throw new ElementNotFoundInCacheException(exceptionMessage);
    }

    /**
     * Set all elements in cache to null.
     */
    public void clearCache() {
        for( int i=0; i<this.capacity; i++ ) {
            this.cache[i] = null;
        }
        log.debug("Clear cache");
    }

    /**
     * Move elements of array of cache to left.
     * @param indexFrom index of begin to move. */
    private void moveElementsCacheToLeft(int indexFrom) {
        for( int i=indexFrom; i<this.capacity-1; i++ ) {
            this.cache[i] = this.cache[i+1];
        }
    }
}
