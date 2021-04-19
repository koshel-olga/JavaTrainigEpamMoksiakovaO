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
}
