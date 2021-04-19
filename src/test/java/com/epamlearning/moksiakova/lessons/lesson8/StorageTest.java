package com.epamlearning.moksiakova.lessons.lesson8;

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

class StorageTest {

    private int capacity;
    private Cache<String> cache;
    private Storage<String> storage;

    @BeforeEach
    public void setUp() {
        this.capacity = 3;
        String[] stringList = {"1","2","3"};
        this.storage = new Storage<>(stringList);
        this.cache = mock(Cache.class);
        this.storage.setCache(cache);
    }

    @AfterEach
    public void tearDown() {
        this.capacity = 0;
        this.storage = null;
        this.cache = null;
    }

    @Test
    public void addElementInStorage() throws StorageIndexOutOfRange {
        capacity = (int) (3*1.5);
        String[] stringList = {"1","2","3","4"};
        Storage<String> expectedStorage = new Storage<>(stringList);

        this.storage.addElement("4");

        for( int i=0; i<capacity; i++) {
            assertEquals(expectedStorage.getElementByIndex(i), storage.getElementByIndex(i));
        }
    }

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

    @Test
    public void clearStorage() {
        this.storage.clearStorage();
        for( int i=0; i<capacity; i++) {
            assertNull(storage.getStorage()[i]);
        }
    }

    @Test
    public void getElementFromStorageByIndex() throws StorageIndexOutOfRange {
        String expected = "5";
        int index = 5;
        when(cache.isPresentIndex(index)).thenReturn(true);
        when(cache.getElementByIndex(index)).thenReturn(expected);

        String actualResult = this.storage.getElementByIndex(index);

        assertEquals(expected, actualResult);
    }

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