package com.epamlearning.moksiakova.lessons.lesson8;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CacheTest {

    private Cache<String> cache;
    private Integer capacity;

    @BeforeEach
    public void setUp() {
        this.capacity = 3;
        this.cache = new Cache<>(capacity);
        this.cache.addElementToCache("1",1);
        this.cache.addElementToCache("2",2);
        this.cache.addElementToCache("3",3);
    }

    @AfterEach
    public void tearDown() {
        this.capacity = 0;
        this.cache = null;
    }

    @Test
    public void testCreateCacheWithConstructorExpectException() {
        int capacity = -1;
        String expectedExceptionMessage = String.format("Illegal argument capacity %d", capacity);
        Throwable expectedException = assertThrows(IllegalCacheArgumentException.class,
                () -> new Cache<>(capacity));
        assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }

    @Test
    public void isPresentWhenElementIsFoundReturnTrue() {
        String element = "1";

        boolean actualResult = this.cache.isPresentElement(element);

        assertTrue(actualResult);
    }

    @Test
    public void isPresentWhenElementIsFoundReturnFalse() {
        String element = "5";

        boolean actualResult = this.cache.isPresentElement(element);

        assertFalse(actualResult);
    }

    @Test
    public void isPresentWhenIndexIsFoundReturnTrue() {
        int index = 1;

        boolean actualResult = this.cache.isPresentIndex(index);

        assertTrue(actualResult);
    }

    @Test
    public void isPresentWhenIndexIsFoundReturnFalse() {
        int index = 5;

        boolean actualResult = this.cache.isPresentIndex(index);

        assertFalse(actualResult);
    }

    @Test
    public void addElementInCache() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.addElementToCache("2",2);
        expectedCache.addElementToCache("3",3);
        expectedCache.addElementToCache("4",4);

        cache.addElementToCache("4",4);

        assertEquals(expectedCache.getElementByIndex(4), cache.getElementByIndex(4));
    }

    @Test
    public void deleteElementFromCache() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.addElementToCache("1",1);
        expectedCache.addElementToCache("2",2);

        cache.deleteElementFromCache("3");

        this.getElementByIndexReturnNull(3);
    }

    @Test
    public void getElementFromCache() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.addElementToCache("1",1);
        expectedCache.addElementToCache("3",3);
        expectedCache.addElementToCache("2",2);

        String actualResult = this.cache.getElementByIndex(2);

        assertEquals("2", actualResult);

        assertEquals(expectedCache.getElementByIndex(1), cache.getElementByIndex(1));
        assertEquals(expectedCache.getElementByIndex(2), cache.getElementByIndex(2));
        assertEquals(expectedCache.getElementByIndex(3), cache.getElementByIndex(3));
    }

    @Test
    public void getElementIsNotFoundExpectException() {
        this.getElementByIndexReturnNull(5);
    }

    @Test
    public void clearCache() {
        cache.clearCache();
        this.getElementByIndexReturnNull(0);
    }

    private void getElementByIndexReturnNull(int index) {
        String expectedExceptionMessage = String.format("Not found element in cache by index %d", index);
        Throwable expectedException = assertThrows(ElementNotFoundInCacheException.class,
                () -> this.cache.getElementByIndex(index));
        assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }
}