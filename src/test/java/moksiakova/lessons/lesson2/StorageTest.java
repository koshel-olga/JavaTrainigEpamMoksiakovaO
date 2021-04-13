package moksiakova.lessons.lesson2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.NoInteractions;
import org.mockito.junit.MockitoJUnitRunner;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StorageTest {
    private Cache<String> cache;
    int capacity;
    private Storage<String> storage;

    @BeforeEach
    public void setUp() {
        this.capacity = 3;
        String[] stringList = {"1","2","3"};
        this.storage = Mockito.spy(new Storage(stringList));
        this.cache = Mockito.mock(Cache.class);
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
        Mockito.doReturn(cache).when(this.storage).getCache();
        Mockito.when(cache.isPresent(Mockito.anyString())).thenReturn(true);
        //Mockito.doNothing().when(cache).delete(Mockito.anyString());

        this.storage.delete();

        for( int i=0; i<capacity; i++) {
            Assertions.assertEquals(stringList[i], storage.getStorage()[i]);
        }
        Mockito.verifyNoMoreInteractions(cache);
    }

    @Test
    public void deleteMethodWhenElementNotInCacheTest() {
        String[] stringList = {"1","2",null};
        Mockito.doReturn(cache).when(this.storage).getCache();
        Mockito.when(cache.isPresent(Mockito.anyString())).thenReturn(false);

        this.storage.delete();

        for( int i=0; i<capacity; i++) {
            Assertions.assertEquals(stringList[i], storage.getStorage()[i]);
        }
        Mockito.verifyNoMoreInteractions(cache);
    }


}