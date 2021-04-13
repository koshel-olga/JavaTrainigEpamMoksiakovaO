package moksiakova.lessons.lesson2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StorageTest {
    private Cache<String> cache;
    int capacity;
    private Storage<String> storage;

    @BeforeEach
    public void setUp() {
        this.capacity = 3;
        String[] stringList = {"1","2","3"};
        this.storage = new Storage(stringList);
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
    public void deleteMethodTest() throws StorageIndexOutOfRange {
        String[] stringList = {"1","2","null"};
        Storage<String> expectedStorage = new Storage(stringList);
        Mockito.when(cache.isPresent(Mockito.anyString())).thenReturn(true);
        Mockito.doNothing().when(cache).delete(Mockito.anyString());

        this.storage.delete();

        for( int i=0; i<capacity; i++) {
            Assertions.assertEquals(expectedStorage.get(i), storage.get(i));
        }
        Mockito.verify(cache).delete(Mockito.anyString());
    }

}