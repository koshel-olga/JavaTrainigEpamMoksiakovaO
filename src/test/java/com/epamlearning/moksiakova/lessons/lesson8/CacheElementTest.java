package com.epamlearning.moksiakova.lessons.lesson8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CacheElementTest {

    @Test
    public void testCreateCacheElementWithConstructorExpectException() {
        int index = -1;
        String expectedExceptionMessage = String.format("Index %d is illegal.",index);
        Throwable expectedException = assertThrows(IllegalCacheArgumentException.class,
                () -> new CacheElement<>(1,index));
        assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }

    @Test
    public void testEqualsHashCodeAndToStringMethodsCacheElement() {
        CacheElement<String> element = new CacheElement<>("element1",1);
        CacheElement<String> elementEqual = new CacheElement<>("element1",1);
        assertTrue(element.equals(elementEqual) && elementEqual.equals(element));
        assertTrue(element.hashCode() == elementEqual.hashCode());
        assertEquals(element.toString(), elementEqual.toString());
    }


}