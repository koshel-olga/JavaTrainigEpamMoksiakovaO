package moksiakova.lessons.lesson2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StorageTest {
    int capacity;
    private Cache<String> cache;
    private Storage<String> storage;

    @BeforeEach
    public void setUp() {
        this.capacity = 3;
        String[] stringList = {"1","2","3"};
        this.storage = Mockito.spy(new Storage(stringList));
        this.cache = Mockito.mock(Cache.class);
        this.storage.setCache(cache);

    }

    @AfterEach
    public void tearDown() {
        this.capacity = 0;
        this.storage = null;
        this.cache = null;
    }

    @Test
    public void addMethodTest() throws StorageIndexOutOfRange {
        capacity = (int) (3*1.5);
        String[] stringList = {"1","2","3","4"};
        Storage<String> expectedStorage = new Storage(stringList);

        this.storage.add("4");

        for( int i=0; i<capacity; i++) {
            Assertions.assertEquals(expectedStorage.get(i), storage.get(i));
        }
    }

    @Test
    public void deleteMethodWhenElementInCacheTest() {
        String[] stringList = {"1","2",null};
        Mockito.when(this.storage.getCache().isPresent(Mockito.anyString())).thenReturn(true);
        //Mockito.when(this.storage.getCache().delete( "3" )).thenReturn();

        this.storage.delete();

        for( int i=0; i<capacity; i++) {
            Assertions.assertEquals(stringList[i], storage.getStorage()[i]);
        }
        Assertions.assertEquals(1, this.storage.getLastIndex());
        //Mockito.verify(this.storage.getCache()).isPresent(Mockito.anyString());
        //Mockito.verify(this.storage.getCache()).delete(Mockito.anyString());
        //Mockito.verifyNoMoreInteractions(cache);
    }

    @Test
    public void deleteMethodWhenElementNotInCacheTest() {
        String[] stringList = {"1","2",null};
        Mockito.when(this.storage.getCache().isPresent(Mockito.anyString())).thenReturn(false);

        this.storage.delete();

        for( int i=0; i<capacity; i++) {
            Assertions.assertEquals(stringList[i], storage.getStorage()[i]);
        }
        Mockito.verify(this.storage.getCache()).isPresent(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(this.storage.getCache());
    }

    @Test
    public void clearMethodTest() {
        this.storage.clear();
        for( int i=0; i<capacity; i++) {
            Assertions.assertEquals(null, storage.getStorage()[i]);
        }
    }

    @Test
    public void getMethodTest() throws StorageIndexOutOfRange {
        String expected = "5";
        int index = 5;
        Mockito.when(this.storage.getCache().isPresent(index)).thenReturn(true);
        Mockito.when(this.storage.getCache().get(index)).thenReturn(expected);

        String actualResult = this.storage.get(index);

        Assertions.assertEquals(expected, actualResult);
    }

    @Test
    public void getMethodTestAssertThrow() throws StorageIndexOutOfRange {
        int index = 11;
        Mockito.when(this.storage.getCache().isPresent(index)).thenReturn(false);

        String expectedExceptionMessage = "index "+index+" out of range";
        Throwable expectedException = Assertions.assertThrows(StorageIndexOutOfRange.class,
                () -> this.storage.get(index));
        Assertions.assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }

}