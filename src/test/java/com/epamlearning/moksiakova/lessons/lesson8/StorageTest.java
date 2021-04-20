package com.epamlearning.moksiakova.lessons.lesson8;

import com.epamlearning.moksiakova.lessons.lesson8.exception.StorageIndexOutOfRange;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link Storage}.
 */
class StorageTest {

    private int capacity;
    private Cache<String> cache;
    private Storage<String> storage;

    /**
     * Method set initial value for each test.
     */
    @BeforeEach
    public void setUp() {
        this.capacity = 3;
        String[] stringList = {"1","2","3"};
        this.storage = new Storage<>(stringList);
        this.cache = mock(Cache.class);
        this.storage.setCache(cache);
    }

    /**
     * Method clear value after each test.
     */
    @AfterEach
    public void tearDown() {
        this.capacity = 0;
        this.storage = null;
        this.cache = null;
    }

    /**
     * Test for {@link Storage} constructor with increasing value capacity.
     */
    @Test
    public void testCreateNewStorageWithChangeCapacity() {
        String[] stringList = {"1","2","3","4","5","6","7","8","9","10","11"};
        Storage<String> storage = new Storage<>(stringList);
        assertEquals(storage.getCapacity(),11);
    }

    /**
     * Check {@link Storage#addElement(Object)} in the standard correct situation.
     */
    @Test
    public void addElementInStorage() {
        capacity = (int) (3*1.5);
        String[] stringList = {"1","2","3","4"};
        Storage<String> expectedStorage = new Storage<>(stringList);

        this.storage.addElement("4");

        for( int i=0; i<capacity; i++) {
            assertEquals(expectedStorage.getElementByIndex(i), storage.getElementByIndex(i));
        }
    }

    /**
     * Check {@link Storage#addElement(Object)} when when a larger storage is created.
     */
    @Test
    public void addElementInStorageWithCreateLagerStorage() {
        capacity = (int) (10*1.5);

        this.storage.addElement("4");
        this.storage.addElement("5");
        this.storage.addElement("6");
        this.storage.addElement("7");
        this.storage.addElement("8");
        this.storage.addElement("9");
        this.storage.addElement("10");
        this.storage.addElement("11");

        assertEquals(storage.getCapacity(),capacity);
    }

    /**
     * Check {@link Storage#deleteLastElement()} when element exist in cache.
     */
    @Test
    public void deleteElementFromStorageWhenElementInCache() {
        String[] stringList = {"1","2",null};
        when(cache.isPresentElement(anyString())).thenReturn(true);

        this.storage.deleteLastElement();

        for( int i=0; i<capacity; i++) {
            assertEquals(stringList[i], storage.getStorage()[i]);
        }
        assertEquals(1, this.storage.getLastIndex());
    }

    /**
     * Check {@link Storage#deleteLastElement()} when element not exist in cache.
     */
    @Test
    public void deleteElementFromStorageWhenElementNotInCache() {
        String[] stringList = {"1","2",null};
        when(cache.isPresentElement(anyString())).thenReturn(false);

        this.storage.deleteLastElement();

        for( int i=0; i<capacity; i++) {
            assertEquals(stringList[i], storage.getStorage()[i]);
        }
        verify(cache).isPresentElement(anyString());
        verifyNoMoreInteractions(this.storage.getCache());
    }

    /**
     * Check {@link Storage#clearStorage()}
     */
    @Test
    public void clearStorage() {
        this.storage.clearStorage();
        for( int i=0; i<capacity; i++) {
            assertNull(storage.getStorage()[i]);
        }
    }

    /**
     * Check {@link Storage#getElementByIndex(int)} in the standard correct situation.
     */
    @Test
    public void getElementFromStorageByIndex() {
        String expected = "5";
        int index = 5;
        when(cache.isPresentIndex(index)).thenReturn(true);
        when(cache.getElementByIndex(index)).thenReturn(expected);

        String actualResult = this.storage.getElementByIndex(index);

        assertEquals(expected, actualResult);
    }

    /**
     * Check {@link Storage#getElementByIndex(int)} when index not exist in storage.
     */
    @Test
    public void getElementFromStorageExpectException() {
        int index = 11;
        when(cache.isPresentIndex(index)).thenReturn(false);

        String expectedExceptionMessage = String.format("Index %d out of range Storage.", index);
        Throwable expectedException = assertThrows(StorageIndexOutOfRange.class,
                () -> this.storage.getElementByIndex(index));
        assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }
}
