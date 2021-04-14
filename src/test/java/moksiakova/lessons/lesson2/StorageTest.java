package moksiakova.lessons.lesson2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class StorageTest {
    int capacity;
    private Cache<String> cache;
    private Storage<String> storage;

    @BeforeEach
    public void setUp() {
        this.capacity = 3;
        String[] stringList = {"1","2","3"};
        this.storage = new Storage(stringList);
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
        Storage<String> expectedStorage = new Storage(stringList);

        this.storage.add("4");

        for( int i=0; i<capacity; i++) {
            assertEquals(expectedStorage.get(i), storage.get(i));
        }
    }

    @Test
    public void deleteElementFromStorageWhenElementInCache() {
        String[] stringList = {"1","2",null};
        when(cache.isPresent(anyString())).thenReturn(true);
        //Mockito.when(this.storage.getCache().delete( "3" )).thenReturn();

        this.storage.delete();

        for( int i=0; i<capacity; i++) {
            assertEquals(stringList[i], storage.getStorage()[i]);
        }
        assertEquals(1, this.storage.getLastIndex());
        //Mockito.verify(this.storage.getCache()).isPresent(Mockito.anyString());
        //Mockito.verify(this.storage.getCache()).delete(Mockito.anyString());
        //Mockito.verifyNoMoreInteractions(cache);
    }

    @Test
    public void deleteElementFromStorageWhenElementNotInCache() {
        String[] stringList = {"1","2",null};
        when(cache.isPresent(anyString())).thenReturn(false);

        this.storage.delete();

        for( int i=0; i<capacity; i++) {
            assertEquals(stringList[i], storage.getStorage()[i]);
        }
        verify(cache).isPresent(anyString());
        verifyNoMoreInteractions(this.storage.getCache());
    }

    @Test
    public void clearStorage() {
        this.storage.clear();
        for( int i=0; i<capacity; i++) {
            assertEquals(null, storage.getStorage()[i]);
        }
    }

    @Test
    public void getElementFromStorage() throws StorageIndexOutOfRange {
        String expected = "5";
        int index = 5;
        when(cache.isPresent(index)).thenReturn(true);
        when(cache.get(index)).thenReturn(expected);

        String actualResult = this.storage.get(index);

        assertEquals(expected, actualResult);
    }

    @Test
    public void getElementFromStorageExpectException() {
        int index = 11;
        when(cache.isPresent(index)).thenReturn(false);

        String expectedExceptionMessage = String.format("index %d out of range", index);
        Throwable expectedException = assertThrows(StorageIndexOutOfRange.class,
                () -> this.storage.get(index));
        assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }

}