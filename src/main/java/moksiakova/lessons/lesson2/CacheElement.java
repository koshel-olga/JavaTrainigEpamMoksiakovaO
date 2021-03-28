package main.java.moksiakova.lessons.lesson2;

import java.util.Objects;

/**
 * Class element of cache. */
public class CacheElement<T> {
    /**
     * Element type of T. */
    private T element;
    /**
     * Index of element. */
    private int index;

    /**
     * Constructor. */
    CacheElement(T element, int index) {
        this.element = element;
        this.index = index;
    }

    /**
     * Getter for field element. */
    public T getElement() {
        return this.element;
    }

    /**
     * Getter for field index.*/
    public int getIndex() {
        return this.index;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        CacheElement<T> elementObj = (CacheElement<T>) obj;
        return ((index == elementObj.getIndex())) &&
                (((element != null) && (element.equals(elementObj.getElement()))) || (element == elementObj.element));
    }

    @Override
    public int hashCode(){
        return Objects.hash(element,index);
    }
}
