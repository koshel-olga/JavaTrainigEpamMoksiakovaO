package moksiakova.lessons.lesson2;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CacheTest {
    Cache<String> cache;
    Integer capacity;

    @BeforeEach
    public void setUp() {
        this.capacity = 3;
        this.cache = new Cache(capacity);
        this.cache.add("1",1);
        this.cache.add("2",2);
        this.cache.add("3",3);
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
                () -> new Cache(capacity));
        assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }

    @Test
    public void isPresentWhenElementIsFoundReturnTrue() {
        String element = "1";

        boolean actualResult = this.cache.isPresent(element);

        assertTrue(actualResult);
    }

    @Test
    public void isPresentWhenElementIsFoundReturnFalse() {
        String element = "5";

        boolean actualResult = this.cache.isPresent(element);

        assertFalse(actualResult);
    }

    @Test
    public void isPresentWhenIndexIsFoundReturnTrue() {
        int index = 1;

        boolean actualResult = this.cache.isPresent(index);

        assertTrue(actualResult);
    }

    @Test
    public void isPresentWhenIndexIsFoundReturnFalse() {
        int index = 5;

        boolean actualResult = this.cache.isPresent(index);

        assertFalse(actualResult);
    }

    @Test
    public void addElementInCache() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.add("2",2);
        expectedCache.add("3",3);
        expectedCache.add("4",4);

        cache.add("4",4);

        for( int i=0; i<capacity; i++) {
            assertEquals(expectedCache.get(i), cache.get(i));
        }
    }

    @Test
    public void deleteElementFromCache() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.add("1",1);
        expectedCache.add("2",2);

        cache.delete("3");

        for( int i=0; i<capacity; i++) {
            assertEquals(expectedCache.get(i), cache.get(i));
        }
    }

    @Test
    public void getElementFromCache() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.add("1",1);
        expectedCache.add("3",3);
        expectedCache.add("2",2);

        String actualResult = this.cache.get(2);

        assertEquals("2", actualResult);
        for( int i=0; i<capacity; i++) {
            assertEquals(expectedCache.get(i), cache.get(i));
        }
    }

    @Test
    public void getElementIsNotFoundReturnNull() {
        String actualResult = this.cache.get(5);
        Assertions.assertNull(actualResult);
    }

    @Test
    public void clearCache() {
        Cache<String> expectedCache = new Cache<>(capacity);
        cache.clear();
        for( int i=0; i<capacity; i++) {
            assertEquals(expectedCache.get(i), cache.get(i));
        }
    }
}