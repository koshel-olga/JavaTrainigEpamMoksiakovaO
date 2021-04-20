package com.epamlearning.moksiakova.lessons.lesson8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epamlearning.moksiakova.lessons.lesson8.exception.IllegalCacheArgumentException;
import org.junit.jupiter.api.Test;

class CacheElementTest {

    /**
     * Test for {@link CacheElement} constructor.
     * Expected {@link IllegalCacheArgumentException} exception.
     */
    @Test
    public void testCreateCacheElementWithConstructorExpectException() {
        int index = -1;
        String expectedExceptionMessage = String.format("Index %d is illegal.",index);
        Throwable expectedException = assertThrows(IllegalCacheArgumentException.class,
                () -> new CacheElement<>(1,index));
        assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }

    /**
     * Test for {@link CacheElement<>#equals(Object)}
     * and {@link CacheElement<>#hashCode()}
     * and {@link CacheElement<>#toString()}
     */
    @Test
    public void testEqualsHashCodeAndToStringMethodsCacheElement() {
        CacheElement<String> element = new CacheElement<>("element1",1);
        CacheElement<String> elementEqual = new CacheElement<>("element1",1);
        assertTrue(element.equals(elementEqual) && elementEqual.equals(element));
        assertEquals(element.hashCode(), elementEqual.hashCode());
        assertEquals(element.toString(), elementEqual.toString());
    }


}