package com.epamlearning.moksiakova.lessons.lesson8;

import com.epamlearning.moksiakova.lessons.lesson8.exception.ElementNotFoundInCacheException;
import com.epamlearning.moksiakova.lessons.lesson8.exception.IllegalCacheArgumentException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for {@link Cache}.
 */
class CacheTest {

    private Cache<String> cache;
    private Integer capacity;

    /**
     * Method set initial value for each test.
     */
    @BeforeEach
    public void setUp() {
        this.capacity = 3;
        this.cache = new Cache<>(capacity);
        this.cache.addElementToCache("1",1);
        this.cache.addElementToCache("2",2);
        this.cache.addElementToCache("3",3);
    }

    /**
     * Method clear value after each test.
     */
    @AfterEach
    public void tearDown() {
        this.capacity = 0;
        this.cache = null;
    }

    /**
     * Test for {@link Cache} constructor.
     * Expected {@link IllegalCacheArgumentException} exception.
     */
    @Test
    public void testCreateCacheWithConstructorExpectException() {
        int capacity = -1;
        String expectedExceptionMessage = String.format("Illegal argument capacity %d", capacity);
        Throwable expectedException = assertThrows(IllegalCacheArgumentException.class,
                () -> new Cache<>(capacity));
        assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }

    /**
     * Check {@link Cache#isPresentElement(Object)} when element is found.
     */
    @Test
    public void isPresentWhenElementIsFoundReturnTrue() {
        String element = "1";

        boolean actualResult = this.cache.isPresentElement(element);

        assertTrue(actualResult);
    }

    /**
     * Check {@link Cache#isPresentElement(Object)} when element not found.
     */
    @Test
    public void isPresentWhenElementIsFoundReturnFalse() {
        String element = "5";

        boolean actualResult = this.cache.isPresentElement(element);

        assertFalse(actualResult);
    }

    /**
     * Check {@link Cache#isPresentIndex(int)} when element whit index is found.
     */
    @Test
    public void isPresentWhenIndexIsFoundReturnTrue() {
        int index = 1;

        boolean actualResult = this.cache.isPresentIndex(index);

        assertTrue(actualResult);
    }

    /**
     * Check {@link Cache#isPresentIndex(int)} when element whit index is not found.
     */
    @Test
    public void isPresentWhenIndexIsFoundReturnFalse() {
        int index = 5;

        boolean actualResult = this.cache.isPresentIndex(index);

        assertFalse(actualResult);
    }

    /**
     * Check {@link Cache#addElementToCache(Object, int)} in the standard correct situation.
     */
    @Test
    public void addElementInCache() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.addElementToCache("2",2);
        expectedCache.addElementToCache("3",3);
        expectedCache.addElementToCache("4",4);

        cache.addElementToCache("4",4);

        assertEquals(expectedCache.getElementByIndex(4), cache.getElementByIndex(4));
    }

    /**
     * Check {@link Cache#deleteElementFromCache(Object)} in the standard correct situation.
     */
    @Test
    public void deleteElementFromCache() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.addElementToCache("1",1);
        expectedCache.addElementToCache("2",2);

        cache.deleteElementFromCache("3");

        this.getElementByIndexReturnNull(3);
    }

    /**
     * Check {@link Cache#getElementByIndex(int)} in the standard correct situation.
     */
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

    /**
     * Check {@link Cache#getElementByIndex(int)} when element not exist.
     * Expected {@link ElementNotFoundInCacheException} exception.
     */
    @Test
    public void getElementIsNotFoundExpectException() {
        this.getElementByIndexReturnNull(5);
    }

    /**
     * Check {@link Cache#clearCache()}.
     */
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