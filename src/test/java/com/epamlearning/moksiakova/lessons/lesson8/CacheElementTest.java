package com.epamlearning.moksiakova.lessons.lesson8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}