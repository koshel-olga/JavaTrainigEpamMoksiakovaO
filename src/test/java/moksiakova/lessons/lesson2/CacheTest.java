package moksiakova.lessons.lesson2;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    public void createCacheAssertThrows() {
        int capacity = -1;
        String expectedExceptionMessage = "Недопустимый аргумент capacity "+ capacity;
        Throwable expectedException = Assertions.assertThrows(IllegalCacheArgumentException.class,
                () -> new Cache(capacity));
        Assertions.assertEquals(expectedExceptionMessage, expectedException.getMessage());
    }

    @Test
    public void isPresentElementTrue() {
        boolean expectedResult = true;
        String element = "1";

        boolean actualResult = this.cache.isPresent(element);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void isPresentElementFalse() {
        boolean expectedResult = false;
        String element = "5";

        boolean actualResult = this.cache.isPresent(element);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void isPresentIndexTrue() {
        boolean expectedResult = true;
        int index = 1;

        boolean actualResult = this.cache.isPresent(index);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void isPresentIndexFalse() {
        boolean expectedResult = false;
        int index = 5;

        boolean actualResult = this.cache.isPresent(index);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addMethod() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.add("2",2);
        expectedCache.add("3",3);
        expectedCache.add("4",4);

        cache.add("4",4);

        for( int i=0; i<capacity; i++) {
            Assertions.assertEquals(expectedCache.get(i), cache.get(i));
        }
    }

    @Test
    public void deleteMethodTest() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.add("1",1);
        expectedCache.add("2",2);

        cache.delete("3");

        for( int i=0; i<capacity; i++) {
            Assertions.assertEquals(expectedCache.get(i), cache.get(i));
        }
    }

    @Test
    public void getElementMethodTest() {
        Cache<String> expectedCache = new Cache<>(capacity);
        expectedCache.add("1",1);
        expectedCache.add("3",3);
        expectedCache.add("2",2);

        String actualResult = this.cache.get(2);

        Assertions.assertEquals("2", actualResult);
        for( int i=0; i<capacity; i++) {
            Assertions.assertEquals(expectedCache.get(i), cache.get(i));
        }
    }

    @Test
    public void getElementMethodNotFoundTest() {
        String actualResult = this.cache.get(5);
        Assertions.assertNull(actualResult);
    }

    @Test
    public void clearMethodTest() {
        Cache<String> expectedCache = new Cache<>(capacity);
        cache.clear();
        for( int i=0; i<capacity; i++) {
            Assertions.assertEquals(expectedCache.get(i), cache.get(i));
        }
    }
}