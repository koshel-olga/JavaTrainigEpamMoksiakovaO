package com.epamlearning.moksiakova.lessons.lesson8;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Class element of cache. */
@EqualsAndHashCode
@Data
public class CacheElement<T> {

    /** Element type of T. */
    @Getter
    private final T element;

    /** Index of element. */
    @Getter
    private final int index;

    public CacheElement(T element, int index) {
        if (index >= 0) {
            this.element = element;
            this.index = index;
        } else {
            String exceptionMessage = String.format("Index %d is illegal.", index);
            throw new IllegalCacheArgumentException(exceptionMessage);
        }
    }

    //public T getElement() {
    //    return this.element;
    //}

    //public int getIndex() {
    //    return this.index;
    //}

    //@Override
    /*public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        CacheElement<T> elementObj = (CacheElement<T>) obj;
        return ( index == elementObj.getIndex() ) &&
                ( ( element != null && element.equals(elementObj.getElement())) || (element == elementObj.element) );
    }*/

    //@Override
    /*public int hashCode(){
        return Objects.hash(element,index);
    }*/
}
